package com.huigou.inspector.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;


public abstract class BaseDao {
	
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * 保存实体
	 *
	 * @param entity
	 */
	public Serializable save(Object entity) {
		return getSession().save(entity);
	}

	/**
	 * 修改实体
	 *
	 * @param entity
	 *            void
	 */
	public void update(Object entity) {
		getSession().update(entity);
	}

	/**
	 * 修改保存实体
	 *
	 * @param entity
	 *            void
	 */
	public void saveOrUpdate(Object entity) {
		getSession().saveOrUpdate(entity);
	}

	public List findAll(String hql) {
		return getSession().createQuery(hql).list();
	}
	
	
	public List find(String hql,int startPage,int pageMaxCount){
		
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setMaxResults(pageMaxCount);
		query.setFirstResult(startPage * pageMaxCount - pageMaxCount);

		return query.list();
	}
	
    @SuppressWarnings("unchecked")
	public List findAll(String queryString, Map<String, Object> map) {
        return createQuery(queryString, map).list();
	}
    
    /**
     * 获取唯一一个查询结果
     * @param queryString
     * @param values
     * @return
     */
    @SuppressWarnings("unchecked")
	public Object find(String queryString,Map<String, Object> map){
    	List list=findAll(queryString,map);
    	if(list!=null&&list.size()>0){
    		return list.get(0);
    	}else{
    		return null;
    	}
    }

	/**
	 * 删除实体
	 *
	 * @param hql
	 *
	 */
	public void delete(Object entity) {
		// getTemplate().update(entityName, entity);
		getSession().delete(entity);
	}

	/**
	 * 根据id返回数据类型
	 *
	 * @param id
	 * @param entity
	 * @return
	 */
	public Object findById(int id, String entity) {
		Object instance = (Object) getSession().get(entity, id);
		return instance;

	}

	@SuppressWarnings("unchecked")
	public Object get(Class aClass, Serializable id) {
		return getSession().get(aClass, id);
	}

	/**
	 * id是String类型
	 *
	 * @param id
	 * @param entity
	 * @return
	 */
	public Object findById(String id, String entity) {
		Object instance = (Object) getSession().get(entity, id);
		return instance;

	}

	/**
	 * getHibernateTemplate的封装
	 *
	 * @return HibernateTemplate
	 */
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public Query createSQLQuery(String hsql, Map<String, Object> params) {
		// 取得当前查询总记录数
		Query q = this.getSession().createSQLQuery(
				hsql);

		String[] keys = q.getNamedParameters();
		if (keys != null && params != null) {
			for (String key : keys) {
				if (!params.containsKey(key)) {
					throw new RuntimeException("没有设置参数" + key + "的值");
				}
				Object value = params.get(key);
				int flg = getParameterType(value);
				switch (flg) {
				case 0:
					q.setParameter(key, value);
					break;
				case 1:
					q.setParameterList(key, (Collection) value);
					break;
				case 2:
					q.setParameterList(key, (Object[]) value);
					break;
				}
			}
		}
		return q;
	}

	@SuppressWarnings("unchecked")
	public Query createQuery(String hsql, Map<String, Object> params) {
		// 取得当前查询总记录数
		Query q = this.getSession().createQuery(hsql);

		String[] keys = q.getNamedParameters();
		if (keys != null && params != null) {
			for (String key : keys) {
				if (!params.containsKey(key)) {
					throw new RuntimeException("没有设置参数" + key + "的值");
				}
				Object value = params.get(key);
				int flg = getParameterType(value);
				switch (flg) {
				case 0:
					q.setParameter(key, value);
					break;
				case 1:
					q.setParameterList(key, (Collection) value);
					break;
				case 2:
					q.setParameterList(key, (Object[]) value);
					break;
				}
			}
		}
		return q;
	}

	public Query createQuery(String hsql) {
		// 取得当前查询总记录数
		Query q = this.getSession().createQuery(hsql);
		return q;
	}

	/**
	 *分页 结果
	 *
	 * @param hsql
	 * @param page
	 * @param pagesize
	 * @param maxsize
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getPage(String hsql, int page, int pagesize, HashMap<String, Object> params) {
		// 取得本次查询返回结果集
		Query q = createQuery(hsql, params);
		q.setFirstResult(page * pagesize);
		q.setMaxResults(pagesize);
		
		return q.list();
	}
	
	/**
	 * 返回共有多少条数
	 *
	 * @param hsql
	 *            hql语句
	 * @param params
	 *            查询参数
	 * @return 总条数
	 * @throws AppException
	 */
	@SuppressWarnings("unchecked")
	public Long getResultCnt(String hsql, HashMap<String, Object> params) {

		// 删除查询语句中的order by子句
		String newSql = delOrderbySQL(hsql);

		// 修改SQL语句
		int idx = newSql.toUpperCase().indexOf("FROM ");
		newSql = "select count(*) " + newSql.substring(idx);

		Query q = createQuery(newSql, params);
		List<Object> list = q.list();
		if(list != null && !list.isEmpty()){
			long num = (Long)list.get(0);
			return num;
		}
		return 0l;
	}

	/**
	 * 返回共有多少条数
	 *
	 * @param hsql
	 *            hql语句
	 * @param params
	 *            查询参数
	 * @return 总条数
	 * @throws AppException
	 */
	private Long getResultCntJDBC(String sql, Map<String, Object> params) {
		Boolean havegroup = false;
		if (params != null) {
			if (params.containsKey("havegroup")
					&& params.get("havegroup") != null) {
				havegroup = (Boolean) params.get("havegroup");
			}
		}
		if (havegroup) {
			Query q = createSQLQuery(sql, params);
			return new Long(q.list().size());
		} else {
			// 删除查询语句中的order by子句
			String newSql = delOrderbySQL(sql);
			
			newSql = modGroupBySql(newSql);
			
			// 修改SQL语句
			int idx = newSql.toUpperCase().indexOf("FROM ");
			
			newSql = "select count(*) " + newSql.substring(idx);

			Query q = createSQLQuery(newSql, params);

			return ((BigDecimal) q.uniqueResult()).toBigInteger().longValue();
		}
	}


	/**
	 * 删除查询条件中的order by子句
	 *
	 * @param queryString
	 *            查询SQL语句
	 * @return 删除查询语句中的order by子句后的查询语句
	 */
	private String delOrderbySQL(String queryString) {

		String result = queryString;

		int idx = queryString.indexOf("order by");

		if (idx > 0) {
			result = queryString.substring(0, idx);
		}
		return result;
	}
	
	/**
	 * yangqin 12-27
	 * 
     * 修改查询条件中的group by子句   
     *
     * @param queryString
     *            查询SQL语句
     * @return 删除查询语句中的order by子句后的查询语句
     */
	private String modGroupBySql(String queryString){
	    String result = queryString;
	    int idx = queryString.indexOf("group by");
	    if(idx > 0){
	        result = "select * from (" + queryString +")";
	    }
	    return result;
	}

	@SuppressWarnings("unchecked")
	private int getParameterType(Object param) {
		if (param == null)
			return 0;
		if (param.getClass().isArray())
			return 2;
		Class[] clss = param.getClass().getInterfaces();
		if (clss == null)
			return 0;
		for (Class cls : clss) {
			if (cls == Collection.class)
				return 1;
		}
		return 0;
	}
}
package com.huigou.inspector.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	// get current time
	public static String getDate(Date d) {
		String s = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		s = sdf.format(d);
		return s;
	}

	// get time(one hour,one day,one week)
	public static String getPastTime(int n) {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String dt = df.format(date);
		date = new Date(System.currentTimeMillis() - n * 60 * 60 * 1000);
		dt = df.format(date);
		return dt;
	}

	/**
	 * function get the days of one year
	 * 
	 * @return days
	 */
	public static boolean getLeapYear() {
		boolean tag = false;
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String dt = df.format(date);
		int pastYear = Integer.parseInt(dt) - 1;
		if (pastYear % 4 == 0 && (pastYear % 100 != 0 || pastYear % 400 == 0))
			tag = true;
		return tag;
	}

	/**
	 * function get days's date
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static String decDays(int days) {
		String dateStr = null;
		Date date = new Date();
		Date dt = add(date, days, Calendar.DATE);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		dateStr = df.format(dt);
		return dateStr;
	}

	/**
	 * function get pastyear date
	 * 
	 * @return date
	 */
	public static String getPastYearDate() {
		String dateStr = null;
		if (getLeapYear()) {
			dateStr = decDays(-366);
		} else {
			dateStr = decDays(-365);
		}
		return dateStr;
	}

	/**
	 * function get assign time
	 * 
	 * @param date
	 * @param amount
	 * @param field
	 * @return
	 */
	private static Date add(Date date, int amount, int field) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * function format time
	 * 
	 * @param str
	 * @return
	 */
	public static String getFormatDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		String dateStr = null;
		try {
			date = sdf.parse(str);
			dateStr = sdf.format(date).replaceAll("-", "");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dateStr;
	}

	/**
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static long getDateLong(String dateString) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		return date.getTime();
	}

	/**
	 * 
	 * @param java
	 *            .sql.Timestamp str
	 * @return
	 */
	public static Date getDate(String s) {
		Date date = new Date();
		try {
			date = java.sql.Timestamp.valueOf(s);
		} catch (Exception e) {
		}

		return date;
	}
	
	public static String getYYYYMMDDHHMMSSFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String parsedDate = sdf.format(date);
		return parsedDate;
	}
	

	public static void main(String[] args) throws ParseException {
		// System.out.println(getTime(365*24*60*60));

		// System.out.println(decDays(-366));

		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" ); Date
		 * date = sdf.parse("2010-1-8"); String str =
		 * sdf.format(date).replaceAll("-","");
		 * System.out.println(getFormatDate("2010-1-8"));
		 */
		/*
		 * int no = 5; int n1 = 50; int n2 = 0; n2 = n1/no; int n4 = n1%no; //
		 * System.out.println(n2); String str = null; StringBuffer buffer = new
		 * StringBuffer(); String tr = "<tr>"; String tr2 = "</tr>";
		 * if(n2>1||n2==1){ buffer.append(tr); for(int i =
		 * 1;i<n2*5||i==n2*5;i++){ buffer.append(i); if(i%5==0&&i!=n1){
		 * buffer.append(tr2+tr); } if(i%5==0&&i==n1){ buffer.append(tr2); } }
		 * for(int j = n2*5+1;j<n1||j==n1;j++){ buffer.append(j); if(j==n1){
		 * buffer.append(tr2); }
		 * 
		 * } }
		 * 
		 * else if(n2==0||n2==1){ buffer.append(tr); for(int i = 0;i<n1;i++){
		 * buffer.append(i);
		 * 
		 * 
		 * } buffer.append(tr2);
		 * 
		 * } else { } System.out.println(buffer.toString()); //
		 * System.out.println(n2);
		 */
		// System.out.println(GetDate.getDate());

		/*
		 * Date date = new Date(System.currentTimeMillis()); SimpleDateFormat df
		 * = new SimpleDateFormat("yyyyMMddHHmmss"); String dt =
		 * df.format(date); // date = new
		 * Date(System.currentTimeMillis()-24*60*60 *1000);
		 */// System.out.println(date.getTime());

		String str = "1338181324980";

		// String s2 = "20120528101010";

		// Date date1 = new Date(s2);
		//		
		// System.out.println(date1);

		Date date = new Date(Long.parseLong(str));
		System.out.println(date);
		//		
		//		
		// date.getTime();

		// 1337670372958
		// 1337756796829

	}
}

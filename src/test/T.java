package test;



import java.io.File;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class T {

	public static int getDaySpaceNoAbs( Date d2,Date d1){
		Boolean flag = false;
		if(d2.before(d1)){
			//需要取负数
			Date d3= d1;
			d1 = d2;
			d2 = d3;
			flag = true;
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		//long类型的日期也支持
		cal1.setTime(d1);
		cal2.setTime(d2);
		//获取日期在一年(月、星期)中的第多少天
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);//第335天
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);//第365天
		//获取当前日期所在的年份
		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		//如果两个日期的是在同一年，则只需要计算两个日期在一年的天数差；
		//不在同一年，还要加上相差年数对应的天数，闰年有366天
		if(year1 != year2) //不同年
		{
			int timeDistance = 0 ;
			for(int i = year1 ; i < year2 ; i ++) {
				if(i%4==0 && i%100!=0 || i%400==0) //闰年
				{
					timeDistance += 366;
				} else //不是闰年
				{
					timeDistance += 365;
				}
			}
			if(flag){
				return -(timeDistance + (day2-day1));
			}else{
				return timeDistance + (day2-day1);
			}
		} else { //同年
			if(flag){
				return -(day2-day1);
			}else {
				return day2-day1;
			}

		}
	}

	public static int getDaySpace(Date d1, Date d2){
		if(d2.before(d1)){
			//需要取负数
			Date d3= d1;
			d1 = d2;
			d2 = d3;
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		//long类型的日期也支持
		cal1.setTime(d1);
		cal2.setTime(d2);
		//获取日期在一年(月、星期)中的第多少天
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);//第335天
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);//第365天
		//获取当前日期所在的年份
		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		//如果两个日期的是在同一年，则只需要计算两个日期在一年的天数差；
		//不在同一年，还要加上相差年数对应的天数，闰年有366天
		if(year1 != year2) //不同年
		{
			int timeDistance = 0 ;
			for(int i = year1 ; i < year2 ; i ++) {
				if(i%4==0 && i%100!=0 || i%400==0) //闰年
				{
					timeDistance += 366;
				} else //不是闰年
				{
					timeDistance += 365;
				}
			}
			return Math.abs(timeDistance + (day2-day1));
		} else //同年
		{
			return Math.abs(day2-day1);
		}
	}

	public static void main(String[] args) {
        /*Date date = new Date();
		// 创建一个Calendar实例
		Calendar calendar = Calendar.getInstance();

		// 设置指定日期，例如2022年1月1日
		calendar.set(2023, Calendar.JANUARY, 1);

		// 获取指定日期的Date对象
		Date date1 = calendar.getTime();

		System.out.println(getDaySpace(date1,date));
		System.out.println(getDaySpaceNoAbs(date1,date));
		System.out.println(date1.compareTo(date));
		System.out.println(date1.compareTo(null));*/

        /*BigDecimal money = BigDecimal.valueOf(100);
		Integer interval = 4;
		System.out.println(money.multiply(BigDecimal.valueOf(interval)));*/

        /*BigDecimal a = BigDecimal.valueOf(300);
		System.out.println(a);
		BigDecimal b = new BigDecimal("300.00");
		System.out.println(b);
		System.out.println(a.equals(b));*/


		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("a",1);
		hashMap.put("a",2);
		System.out.println(hashMap.get("a"));

	}
}

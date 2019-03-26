package com.demo.javase;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author wxw
 * @Description:XX
 * @date 2019/3/26
 */
public class testClass {
	public static String changeTimeToTerm(Date date){
		String term ="";
		if(null==date){return term;}
		//转成1.8日期
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Date special =DateUtil.convertDate("2019-09-01");
		if(date.before(special)){//特殊处理
			term = "2019春夏学期";
		}else{
			if(localDate.getMonthValue()>=3&&localDate.getMonthValue()<=8){
				term = localDate.getYear()+"春夏学期";
			}else if(localDate.getMonthValue()>=8){
				term = localDate.getYear()+"秋冬学期";
			}else{
				term = localDate.minusYears(1)+"秋冬学期";
			}
		}
		return term;
	}

	public static String confirmTerm(String severStart,String severEnd){
		String p ="0";
		if(Utils.isEmpty(severStart)||Utils.isEmpty(severEnd)){
			return p;
		}
		String now =changeTimeToTerm(new Date());
		//去掉可能存在的年
		String start =severStart.replace("年","");
		String end =severEnd.replace("年","");
		Integer startYear =Integer.valueOf(severStart.substring(0,4));
		Integer endYear =Integer.valueOf(severEnd.substring(0,4));
		Integer realYear =Integer.valueOf(now.substring(0,4));
		int termNum= 0;
		int passNum= 0;
		if(startYear>realYear){//还没开始
			p ="0";
		}else if(endYear<realYear){
			p ="1";
		}else{
			boolean startResult = start.contains("春夏");
			boolean endResult = end.contains("春夏");
			boolean nowResult = now.contains("春夏");
			termNum =(endYear-startYear+1)*2;
			passNum =(realYear-startYear+1)*2;
			if(startResult&&endResult){
				termNum-=1;
			}else if(!startResult&&!endResult){
				termNum-=1;
			}
			if(startResult&&nowResult){
				passNum-=1;
			}else if(!startResult&&!nowResult){
				passNum-=1;
			}
			p=Utils.txfloat(passNum,termNum);
		}
		return p;
	}

	public static void main(String[] args) {
		Date date = DateUtil.convertDateByType("2020-03-09",DateUtil.FMT_DATE);
		String title = changeTimeToTerm(date);
		String i= confirmTerm("2016年春夏学期","2018年秋冬学期");
		System.out.println("title="+title);
		System.out.println("i="+i);
	}
}

package com.demo.javase.designmode.strategy;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public abstract class AbstractCalculator {
	public int[] split(String exp,String opt){
		String arry[]=exp.split(opt);
		int arrayInt[]=new int[2];
		arrayInt[0]=Integer.parseInt(arry[0]);
		arrayInt[1]=Integer.parseInt(arry[1]);
		return arrayInt;
	}
}

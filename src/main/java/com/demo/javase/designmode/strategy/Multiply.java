package com.demo.javase.designmode.strategy;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/23
 */
public class Multiply extends AbstractCalculator implements ICalculator{

	@Override
	public int calculate(String exp) {
		int arrayInt[]=split(exp,"\\*");
		return arrayInt[0]*arrayInt[1];
	}

	public static void main(String[] args) {
		String exp="2+8";
		ICalculator cal=new Plus();
		int result=cal.calculate(exp);
		System.out.println(result);
	}
}

package com.demo.javase.designmode.templatemethod;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/23
 */
public class Plus extends AbstractCalculator{
	@Override
	int calculate(int num1, int num2) {
		return num1+num2;
	}

	public static void main(String[] args){
		String exp ="8+8";
		AbstractCalculator cal=new Plus();
		int result=cal.calculate(exp,"\\+");
		System.out.println(result);
	}
}

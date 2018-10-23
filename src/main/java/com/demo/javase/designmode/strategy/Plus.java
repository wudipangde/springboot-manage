package com.demo.javase.designmode.strategy;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/23
 */
public class Plus extends AbstractCalculator implements ICalculator{

	@Override
	public int calculate(String exp) {
		int arrayInt[]=split(exp,"\\+");
		return arrayInt[0]+arrayInt[1];
	}
}

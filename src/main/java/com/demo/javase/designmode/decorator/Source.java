package com.demo.javase.designmode.decorator;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class Source implements Sourceable {
	@Override
	public void method() {
		System.out.println("the original method!");
	}
}

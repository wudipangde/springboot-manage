package com.demo.javase.designmode.proxy;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class Source implements Sourceable{
	@Override
	public void mehtod() {
		System.out.println("this original method!");
	}
}

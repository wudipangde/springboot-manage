package com.demo.javase.designmode.bridge;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class SourceSub1 implements Sourceable {
	@Override
	public void method() {
		System.out.println("the first sub");
	}
}

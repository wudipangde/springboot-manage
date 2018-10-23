package com.demo.javase.designmode.observer;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/23
 */
public class Observer1 implements Observer{
	@Override
	public void update() {
		System.out.println("observer1 has received!");
	}
}

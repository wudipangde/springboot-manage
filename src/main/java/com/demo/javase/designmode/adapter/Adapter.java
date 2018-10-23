package com.demo.javase.designmode.adapter;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class Adapter extends Source implements Targetable {
	@Override
	public void method2() {
		System.out.println("this is targetable method!");
	}

	public static void main(String[] args) {
		//类的适配器模式
		Targetable target=new Adapter();
		target.method1();
		target.method2();
	}
}

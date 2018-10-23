package com.demo.javase.designmode.singleton;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class Singleton1 {
	private Singleton1(){}
	private static class SingletonFactory{
		private static Singleton1 instance=new Singleton1();
	}
	public static Singleton1 getInstacne(){
		return SingletonFactory.instance;
	}
	public Object readResolve(){
		return getInstacne();
	}
}

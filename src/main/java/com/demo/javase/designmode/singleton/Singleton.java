package com.demo.javase.designmode.singleton;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class Singleton {
	//简单单例-懒加载-单线程
	private static Singleton instance = null;

	private Singleton() {
	}

	/* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
	public Object readResolve() {
		return instance;
	}

	public static Singleton getInstance() {
		if (instance == null) {
//			instance == new Singleton();
		}
		return instance;
	}

	//getInstacne加锁，性能有问题
	public static synchronized Singleton getInstance1(){
		if(instance == null){
			instance = new Singleton();
		}
		return instance;
	}
	//创建对象加锁--问题，开辟内存但是没有初始化
	public static Singleton getInstace(){
		if(instance == null){
			synchronized (instance){
				if(instance == null){
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}
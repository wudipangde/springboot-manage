package com.demo.javase.designmode.adapter;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class Wrapper implements Targetable{
	private Source source;
	public Wrapper(Source source){
		super();
		this.source=source;
	}
	@Override
	public void method1() {
		System.out.println("this is the targetable method!");
	}

	@Override
	public void method2() {
		source.method1();
	}

	public static void main(String[] args) {
		//对象的适配器模式
		Source source=new Source();
		Targetable target=new Wrapper(source);
		target.method1();
		target.method2();
	}
}

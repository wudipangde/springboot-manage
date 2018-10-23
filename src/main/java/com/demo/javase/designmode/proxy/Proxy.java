package com.demo.javase.designmode.proxy;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class Proxy implements Sourceable{
	private Source source;
	public Proxy(){
		super();
		this.source=new Source();
	}
	@Override
	public void mehtod() {
		before();
		source.mehtod();
		after();
	}
	private void after(){
		System.out.println("after proxy!");
	}
	private void before(){
		System.out.println("before proxy!");
	}

	public static void main(String[] args) {
		Sourceable sourceable=new Proxy();
		sourceable.mehtod();
	}
}

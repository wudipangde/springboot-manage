package com.demo.javase.designmode.decorator;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class Decorator implements Sourceable {
	private Sourceable sourceable;
	public Decorator(Sourceable sourceable){
		super();
		this.sourceable=sourceable;
	}
	@Override
	public void method() {
		System.out.println("before decorator");
		sourceable.method();
		System.out.println("after decorator");
	}

	public static void main(String[] args) {
		Sourceable sourceable=new Source();
		Sourceable obj=new Decorator(sourceable);
		obj.method();
	}
}

package com.demo.javase.designmode.chainofresponsibility;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
public class MyHandler extends AbstractHandler implements Handler{
	private String name;
	public MyHandler(String name){
		this.name=name;
	}
	@Override
	public void operator() {
		System.out.println(name+" deal!");
		if(getHandler()!=null){
			getHandler().operator();
		}
	}

	public static void main(String[] args) {
		MyHandler h1=new MyHandler("h1");
		MyHandler h2=new MyHandler("h2");
		MyHandler h3=new MyHandler("h3");

		h1.setHandler(h2);
		h2.setHandler(h3);
		h1.operator();
	}
}

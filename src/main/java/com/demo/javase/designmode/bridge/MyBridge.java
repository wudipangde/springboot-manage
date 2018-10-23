package com.demo.javase.designmode.bridge;

import sun.corba.Bridge;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class MyBridge extends AbstractBridge {
	@Override
	public void method(){
		getSourceable().method();
	}

	public static void main(String[] args) {
		AbstractBridge bridge=new MyBridge();
		Sourceable source1=new SourceSub1();
		bridge.setSourceable(source1);
		bridge.method();

		Sourceable source2=new SourceSub2();
		bridge.setSourceable(source2);
		bridge.method();
	}
}

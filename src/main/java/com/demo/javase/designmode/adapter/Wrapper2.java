package com.demo.javase.designmode.adapter;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public abstract class Wrapper2 implements Sourceable{
	@Override
	public void method1(){}
	@Override
	public void mehtod2(){}

	public static void main(String[] args) {
		Sourceable source1=new SourceSub1();
		Sourceable source2=new SourceSub2();

		source1.method1();
		source1.mehtod2();
		source2.method1();
		source2.mehtod2();
	}
}

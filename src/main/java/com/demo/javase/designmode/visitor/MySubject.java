package com.demo.javase.designmode.visitor;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
public class MySubject implements Subject {
	@Override
	public void accept(Visitor visitor) {
//		visitor.visit(this);
	}

	@Override
	public String getSubject() {
		return "love";
	}
}

package com.demo.javase.designmode.visitor;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
public interface Subject {
	public void accept(Visitor visitor);
	public String getSubject();
}

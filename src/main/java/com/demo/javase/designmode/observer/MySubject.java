package com.demo.javase.designmode.observer;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/23
 */
public class MySubject extends AbstractSubject{
	@Override
	public void operation() {
		System.out.println("udateSelf");
		notifyObservers();
	}

	public static void main(String[] args) {
		Subject subject=new MySubject();
		subject.add(new Observer1());
		subject.add(new Observer2());
		subject.operation();
	}
}

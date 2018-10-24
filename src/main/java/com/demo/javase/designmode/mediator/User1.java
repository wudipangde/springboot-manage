package com.demo.javase.designmode.mediator;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
public class User1 extends AbstractUser {
	public User1(Mediator mediator){
		super(mediator);
	}
	@Override
	public void work() {
		System.out.println("user1 exe!");
	}
}

package com.demo.javase.designmode.mediator;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
public class User2 extends AbstractUser {
	public User2(Mediator mediator){
		super(mediator);
	}
	@Override
	public void work() {
		System.out.println("user2 exe!");
	}
}

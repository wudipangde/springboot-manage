package com.demo.javase.designmode.mediator;

import lombok.Getter;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
@Getter
public class MyMediator implements Mediator{
	private AbstractUser user1;
	private AbstractUser user2;
	@Override
	public void createMediator() {
		user1 = new User1(this);
		user2 = new User2(this);
	}

	@Override
	public void workAll() {
		user1.work();
		user2.work();
	}

	public static void main(String[] args) {
		Mediator mediator=new MyMediator();
		mediator.createMediator();
		mediator.workAll();
	}
}

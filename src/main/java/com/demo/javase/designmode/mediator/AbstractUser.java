package com.demo.javase.designmode.mediator;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
@Setter@Getter
public abstract class AbstractUser {
	private Mediator mediator;

	public AbstractUser(Mediator mediator) {
		this.mediator=mediator;
	}

	public abstract void work();
}

package com.demo.javase.designmode.bridge;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
@Getter@Setter
public abstract class AbstractBridge {
	private Sourceable sourceable;
	public void method(){
		sourceable.method();
	}
}

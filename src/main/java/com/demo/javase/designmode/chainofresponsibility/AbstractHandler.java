package com.demo.javase.designmode.chainofresponsibility;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
@Getter@Setter
public abstract class AbstractHandler {
	private Handler handler;
}

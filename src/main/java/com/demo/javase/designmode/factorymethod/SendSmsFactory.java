package com.demo.javase.designmode.factorymethod;

import com.demo.javase.designmode.Sender;
import com.demo.javase.designmode.SmsSender;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class SendSmsFactory implements Provider{
	@Override
	public Sender produce() {
		return new SmsSender();
	}
}

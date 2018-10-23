package com.demo.javase.designmode;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class MailSender implements Sender{
	@Override
	public void Send() {
		System.out.println("this is mailSender!");
	}
}

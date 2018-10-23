package com.demo.javase.designmode.factorymethod;


import com.demo.javase.designmode.MailSender;
import com.demo.javase.designmode.Sender;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class SendMailFactory implements Provider {

	@Override
	public Sender produce() {
		return new MailSender();
	}

	public static void main(String[] args) {
		Provider provider =new SendMailFactory();
		Sender sender=provider.produce();
		sender.Send();
	}
}

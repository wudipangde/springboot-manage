package com.demo.javase.designmode.simpFactory;


import com.demo.javase.designmode.MailSender;
import com.demo.javase.designmode.Sender;
import com.demo.javase.designmode.SmsSender;

/**
 * @author wxw
 * @Description:XX
 * 总体来说，工厂模式适合：凡是出现了大量的产品需要创建，并且具有共同的接口时，可以通过工厂方法模式进行创建。
 * 在以上的三种模式中，第一种如果传入的字符串有误，不能正确创建对象，第三种相对于第二种，不需要实例化工厂类，
 * 所以，大多数情况下，我们会选用第三种——静态工厂方法模式。
 * @date 2018/10/22
 */
public class SendFactory {
	//简单工厂模式-普通
	public Sender produce(String type){
		if("mail".equals(type)){
			return new MailSender();
		}else if("sms".equals(type)){
			return new SmsSender();
		}else{
			System.out.println("输入正确的类型");
			return null;
		}
	}

	//简单工厂模式-多个方法
	public Sender produceMail(){
		return new MailSender();
	}
	public Sender produceSms(){
		return new SmsSender();
	}
	//简单工厂模式-多个静态方法
	public static Sender produceMailS(){
		return new MailSender();
	}
	public static Sender produceSmsS(){
		return new SmsSender();
	}


	public static void main(String[] args) {
		//简单工厂模式-普通
		SendFactory factory =new SendFactory();
		Sender sender =factory.produce("sms");
		sender.Send();
		//简单工厂模式-多个方法
		Sender sender1=factory.produceMail();
		sender1.Send();
		//简单工厂模式-多个静态方法
		Sender sender2=SendFactory.produceMailS();
		sender2.Send();
	}
}

package com.demo.rabbitmq.direct;

import com.demo.rabbitmq.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/26
 */
public class DirectCustomer1 {
	public final static String EXCHANGE_NAME ="springboot_manage_demo_directExchange";  //交换机

	public static void main(String[] args) throws IOException, TimeoutException {
		//1、获取链接
		Connection connection = ConnectionUtil.getConnection();
		//2、创建通道
		Channel channel=connection.createChannel();
		//3、申明交换机
		channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.DIRECT);
		//4、申明一个临时队列
		String queueName =channel.queueDeclare().getQueue();

		//绑定路由，统一队列可以绑定多个值
		channel.queueBind(queueName,EXCHANGE_NAME,"demo");
		//消费消息
		channel.basicQos(1);

//		channel.basicConsume(queueName,false,new DefaultConsumer(channel){
//
//		})
	}
}

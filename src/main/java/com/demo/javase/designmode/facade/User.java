package com.demo.javase.designmode.facade;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class User {
	public static void main(String[] args){
		Computer computer=new Computer();
		computer.startup();
		computer.shutdown();
	}
}

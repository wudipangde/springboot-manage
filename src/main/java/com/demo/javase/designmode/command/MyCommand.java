package com.demo.javase.designmode.command;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
public class MyCommand implements Command{
	private Receiver receiver;

	public MyCommand(Receiver receiver){
		this.receiver=receiver;
	}
	@Override
	public void exe() {
		receiver.action();
	}
}

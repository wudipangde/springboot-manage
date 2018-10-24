package com.demo.javase.designmode.command;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
public class Invoker {
	private Command command;
	public Invoker(Command command){
		this.command=command;
	}
	public void action(){
		command.exe();
	}

	public static void main(String[] args) {
		Receiver receiver=new Receiver();
		Command command=new MyCommand(receiver);
		Invoker invoker=new Invoker(command);
		invoker.action();
	}
}

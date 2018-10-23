package com.demo.javase.designmode.facade;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class Computer {
	private Cpu cpu;
	private Memory memory;

	public Computer(){
		cpu=new Cpu();
		memory=new Memory();
	}

	public void startup(){
		System.out.println("start the computer!");
		cpu.startUp();
		memory.startup();
		System.out.println("start computer finished");
	}

	public void shutdown(){
		System.out.println("begin to close the computer!");
		cpu.shutdown();
		memory.shutdown();
		System.out.println("computer closed!");
	}
}

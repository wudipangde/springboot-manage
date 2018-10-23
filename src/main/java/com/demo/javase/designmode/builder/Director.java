package com.demo.javase.designmode.builder;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class Director {
	public Human createHumanByDirecotr(IBuildHuman bh){
		bh.buildBody();
		bh.buildFoot();
		bh.buildHand();
		bh.buildHead();
		return bh.createHuman();
	}

	public static void main(String[] args) {
		Director director=new Director();
//		Human createHm=new Human("120智商的大脑","强健的身体","勤劳的双手","飞毛腿");
		Human human=director.createHumanByDirecotr(new SmartManBuilder());
		System.out.println(human.getHead());
		System.out.println(human.getBody());
		System.out.println(human.getHand());
		System.out.println(human.getFoot());
	}
}

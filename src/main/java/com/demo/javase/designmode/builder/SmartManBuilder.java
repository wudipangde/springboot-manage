package com.demo.javase.designmode.builder;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class SmartManBuilder implements IBuildHuman{
	Human human;
	public SmartManBuilder(){
		human=new Human();
	}
//	public SmartManBuilder(Human human){
//		human=this.human;
//	}
	@Override
	public void buildHead() {
		human.setHead("智商180的头脑");
	}

	@Override
	public void buildBody() {
		human.setBody("新的身份");
	}

	@Override
	public void buildHand() {
		human.setHead("新的手");
	}

	@Override
	public void buildFoot() {
		human.setFoot("新的脚");
	}

	@Override
	public Human createHuman() {
		return human;
	}
}

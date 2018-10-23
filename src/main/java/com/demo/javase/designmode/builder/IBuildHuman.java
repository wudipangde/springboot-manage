package com.demo.javase.designmode.builder;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public interface IBuildHuman {
	public void buildHead();
	public void buildBody();
	public void buildHand();
	public void buildFoot();
	public Human createHuman();
}

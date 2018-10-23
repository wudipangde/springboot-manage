package com.demo.javase.designmode.factorymethod;


import com.demo.javase.designmode.Sender;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public interface Provider {
	Sender produce();
}

package com.demo.javase.designmode.state;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
@Getter@Setter
public class State {
	private String value;
	public void method1(){
		System.out.println("execute the first opt!");
	}
	public void mehtod2(){
		System.out.println("execute the second opt!");
	}
}

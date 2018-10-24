package com.demo.javase.designmode.memento;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
@Setter@Getter
public class Memento {
	private String value;
	public Memento(String value){
		this.value=value;
	}
}

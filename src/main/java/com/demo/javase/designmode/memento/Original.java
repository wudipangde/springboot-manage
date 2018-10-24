package com.demo.javase.designmode.memento;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
@Setter@Getter
public class Original {
	public String value;
	public Original(String value){
		this.value=value;
	}
	public Memento createMemento(){
		return new Memento(value);
	}
	public void restoreMemento(Memento memento){
		this.value=memento.getValue();
	}
}

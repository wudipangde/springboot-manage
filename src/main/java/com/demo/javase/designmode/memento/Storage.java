package com.demo.javase.designmode.memento;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
@Getter@Setter
public class Storage {
	private Memento memento;
	public Storage(Memento memento){
		this.memento=memento;
	}

	public static void main(String[] args) {
		Original original=new Original("egg");
		Storage storage=new Storage(original.createMemento());
		System.out.println("初始状态"+original.getValue());
		original.setValue("niu");
		System.out.println("修改后的状态为：" + original.getValue());

		// 回复原始类的状态
		original.restoreMemento(storage.getMemento());
		System.out.println("恢复后的状态为：" + original.getValue());
	}
}

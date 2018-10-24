package com.demo.javase.designmode.iterator;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/23
 */
public interface Collection {
	public Iterator iterator();
	public Object get(int i);
	public int size();
}

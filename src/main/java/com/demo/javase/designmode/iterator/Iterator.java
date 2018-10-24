package com.demo.javase.designmode.iterator;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
public interface Iterator {
	public Object previous();
	public Object next();
	public boolean hasNext();
	public Object first();
}

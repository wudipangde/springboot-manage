package com.demo.javase.designmode.iterator;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
public class MyCollection implements Collection{
	public String string[]={"A","B","C","D","E","F","G"};
	@Override
	public Iterator iterator() {
		return new MyIterator(this);
	}

	@Override
	public Object get(int i) {
		return string[i];
	}

	@Override
	public int size() {
		return string.length;
	}
}

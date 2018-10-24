package com.demo.javase.designmode.iterator;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/24
 */
public class MyIterator implements Iterator {
	private Collection collection;
	private int pos =-1;

	public MyIterator(Collection collection){
		this.collection=collection;
	}

	@Override
	public Object previous() {
		if(pos>0){
			pos--;
		}
		return collection.get(pos);
	}

	@Override
	public Object next() {
		if(pos<collection.size()-1){
			pos++;
		}
		return collection.get(pos);
	}

	@Override
	public boolean hasNext() {
		return pos < collection.size() - 1;
	}

	@Override
	public Object first() {
		pos=0;
		return collection.get(pos);
	}

	public static void main(String[] args) {
		Collection collection=new MyCollection();
		Iterator it=collection.iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
	}
}

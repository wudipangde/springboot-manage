package com.demo.javase.structure;

/**
 * @author wxw
 * @Description:XX
 * @date 2019/3/29
 */
public class MyQueue {
	private Object[] queArray;
	private int maxSize;
	private int front;
	private int rear;
	private int nItems;

	public MyQueue(int s){
		maxSize =s;
		queArray =new Object[maxSize];
		front =0;
		rear= -1;
		nItems =0;
	}
	public void insert(int value){
		if(isFull()){
			System.out.println("队列已经满了");
		}else{
			if(rear==maxSize-1){
				rear =  -1;
			}
			queArray[++rear] =value;
			nItems++;
		}
	}
	public Object remove(){
		Object removeValue =null;
		if(!isEmpty()){
			removeValue =queArray[front];
			queArray[front]=null;
			front++;
			if(front==maxSize){
				front=0;
			}
			nItems--;
			return removeValue;
		}
		return removeValue;

	}

	public Object peekFront(){
		return queArray[front];
	}

	public boolean isFull(){
		return (nItems==maxSize);
	}

	public boolean isEmpty(){
		return (nItems==0);
	}

	public int getSize(){
		return nItems;
	}


}

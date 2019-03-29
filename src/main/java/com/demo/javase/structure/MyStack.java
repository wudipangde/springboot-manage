package com.demo.javase.structure;

/**
 * @author wxw
 * @Description:XX
 * @date 2019/3/29
 */
public class MyStack {
	private int[] array;
	private int maxSize;
	private int top;

	public MyStack(int size){
		this.maxSize=size;
		array =new int[size];
		top =-1;
	}

	public void push(int value){
		if(top<maxSize-1){
			array[++top]=value;
		}
	}

	public int pop(){
		return array[top--];
	}

	public int peek(){
		return array[top];
	}

	public boolean isEmpty(){
		return (top == maxSize-1);
	}

	public boolean isFull(){
		return (top == maxSize-1);
	}

	public static void main(String[] args) {
		MyStack stack=new MyStack(3);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.peek());
		while (!stack.isEmpty()){
			System.out.println(stack.pop());
		}
	}
}

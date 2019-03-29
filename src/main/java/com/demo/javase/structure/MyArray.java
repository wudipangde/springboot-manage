package com.demo.javase.structure;

/**
 * @author wxw
 * @Description:XX
 * @date 2019/3/29
 */
public class MyArray {
	//定义一个数组
	private int[] intArray;
	//定义数组的实际有效长度
	private int elems;
	//定义数组的最大长度
	private int length;

	public MyArray(){
		elems =0;
		length =50;
		intArray=new int[length];
	}

	public MyArray(int length){
		elems =0 ;
		this.length=length;
		intArray=new int[length];
	}

	public int getSize(){
		return elems;
	}

	public void display(){
		for(int i=0;i<elems;i++){
			System.out.println(intArray[i]+ " ");
		}
		System.out.println();
	}

	public boolean add(int value){
		if(elems == length){
			return false;
		}else{
			intArray[elems] =value;
			elems++;
		}
		return true;
	}

	public int get(int i){
		if(i<0 || i>elems){
			System.out.println("访问下标越界");
		}
		return intArray[i];
	}

	public int find(int searchValue){
		int i;
		for(i=0;i<elems;i++){
			if(intArray[i]==searchValue){
				break;
			}
		}
		if(i==elems){
			return -1;
		}
		return i;
	}

	public boolean delete(int value){
		int k =find(value);
		if(k==-1){
			return false;
		}else{
			if(k==elems-1){
				elems--;
			}else{
				System.arraycopy(intArray, k + 1, intArray, k, elems - 1 - k);
				elems--;
			}
		}
		return true;
	}

	public boolean modify(int oldValue,int newValue){
		int i =find(oldValue);
		if(i==-1){
			System.out.println("修改的数据不存在");
			return false;
		}else{
			intArray[i] =newValue;
			return true;
		}
	}

	public static void main(String[] args) {
		MyArray array =new MyArray(4);
		array.add(1);
		array.add(2);
		array.add(3);
		array.add(4);
		array.display();
		int i =array.get(0);
		System.out.println(i);
		array.delete(4);
		array.modify(3,33);
		array.display();
	}
}

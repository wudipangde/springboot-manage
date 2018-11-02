package com.demo.javase.arithmetic.sort;

/**
 * @author wxw
 * @Description:XX
 * 1、比较相邻的元素，如果前一个比后一个大，就把它们两个调换位置。
 * 2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * 3、针对所有的元素重复以上的步骤，除了最后一个。
 * 4、持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 * @date 2018/10/29
 */
public class BubbleSort {
	public static void bubbleSort(int a[]){
		int len=a.length;
		for(int i=0;i<len;i++){
			for(int j=0;j<len-1-i;j++){
				if(a[j]>a[j+1]){
					int temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] a=new int[]{10,9,8,7,11,15,13};
		bubbleSort(a);
		for(int b:a){System.out.print(b+",");}
	}
}

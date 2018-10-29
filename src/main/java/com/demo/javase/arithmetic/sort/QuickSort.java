package com.demo.javase.arithmetic.sort;

/**
 * @author wxw
 * @Description:XX
Algorithm	Average Time	Worst Time	Space
冒泡排序		n^2				n^2			1
选择排序		n^2				n^2			1
Counting Sort	n+k			n+k			n+k
Insertion sort	n^2			n^2
Quick sort		n log(n)	n^2
Merge sort		n log(n)	n log(n)	depends
 * 步骤
 * 1、从数列中挑出一个元素，称为 “基准”（pivot），
 * 2、重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。
 *    这个称为分区（partition）操作。
 * 3、递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 *
 * 参考博客：https://www.cnblogs.com/0201zcr/p/4763806.html
 * 			http://blog.jobbole.com/11745/
 * 			https://www.cnblogs.com/10158wsj/p/6782124.html
 * @date 2018/10/29
 */
public class QuickSort {
	/*
     * @param a 带查找数组
     * @param start   开始位置
     * @param end  结束位置
	*/
	public static void quickSort(int[] a,int start,int end){
		if(start<end){
			int baseNum=a[start];//基准值
			int midNum;//中间值
			int i=start;
			int j=end;
			do{
				while ((a[i]<baseNum)&&i<end){
					i++;
				}
				while((a[j]>baseNum)&&j>start){
					j--;
				}
				if(i<=j){
					midNum=a[i];
					a[i]=a[j];
					a[j]=midNum;
					i++;
					j--;
				}
			}while(i<=j);
			if (start < j) {
				quickSort(a,start,j);
			}
			if (end >i){
				quickSort(a,i,end);
			}
		}
	}

	public static void main(String[] args) {
		int[] a =new int[]{1,2,99,95,94,6,7,10,80,90};
		quickSort(a,a[0],a.length-1);
		for(int m:a){
			System.out.print(m+",");
		}
	}

}

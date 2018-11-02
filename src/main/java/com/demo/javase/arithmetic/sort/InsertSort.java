package com.demo.javase.arithmetic.sort;

/**
 * @author wxw
 * @Description:XX
 * 首先设定插入次数，即循环次数，for(int i=1;i<length;i++)，1个数的那次不用插入。
 * 设定插入数和得到已经排好序列的最后一个数的位数。insertNum和j=i-1。
 * 从最后一个数开始向前循环，如果插入数小于当前数，就将当前数向后移动一位。
 * 将当前数放置到空着的位置，即j+1。
 * @date 2018/10/29
 */
public class InsertSort {
	public static void insertSort(int[] a){
		int len=a.length; //单独把数组长度拿出来
		int insertNum;
		for(int i=1;i<len;i++){
			insertNum=a[i];
			int j=i-1;
			while(j>=0&&a[j]>insertNum){
				a[j+1]=a[j];
				j--;
			}
			a[j+1]=insertNum;
		}
	}
}

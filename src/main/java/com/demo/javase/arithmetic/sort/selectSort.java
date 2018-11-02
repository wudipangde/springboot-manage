package com.demo.javase.arithmetic.sort;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/29
 */
public class selectSort {
	public static void selectSort(int a[]){
		int len=a.length;
		for(int i=0;i<len;i++){
			int value= a[i];
			int position=i;
			for(int j=i+1;j<len;j++){
				if(a[j]<value){
					value=a[j];
					position=j;
				}
			}
			a[position]=a[i];
			a[i]=value;
		}
	}
}

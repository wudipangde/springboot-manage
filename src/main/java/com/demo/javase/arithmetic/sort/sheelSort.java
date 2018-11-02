package com.demo.javase.arithmetic.sort;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/29
 */
public class sheelSort {
	public static void sheelSort(int[] a){
		int len=a.length;
		while (len!=0){
			len=len/2;
			for(int i=0;i<len;i++){
				for(int j=i+len;j<a.length;j++){
					int k=j-len;
					int temp=a[j];
					while(k>=0&&temp<a[k]){
						a[k+len]=a[k];
						k-=len;
					}
					a[k+len]=temp;
				}
			}
		}
	}
}

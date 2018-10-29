package com.demo.javase.arithmetic.map;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/29
 */
public class GraphNode {
	int val;
	GraphNode next;
	GraphNode[] neighbors;
	boolean visited;
	GraphNode(int x){
		val=x;
	}
	GraphNode(int x,GraphNode[] n){
		val =x;
		neighbors =n ;
	}
	@Override
	public String toString(){
		return "value:"+ this.val;
	}
}

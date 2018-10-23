package com.demo.javase.designmode.composite;

import lombok.Getter;
import lombok.Setter;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
@Getter@Setter
public class TreeNode {
	private String name;
	private TreeNode parent;
	private Vector<TreeNode> children=new Vector<TreeNode>();

	public TreeNode(String name){
		this.name = name;
	}
	//添加孩子节点
	public void add(TreeNode node){
		children.add(node);
	}
	public void remove(TreeNode node){
		children.remove(node);
	}
	public Enumeration<TreeNode> getChildren(){
		return children.elements();
	}
}

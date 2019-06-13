package com.demo.javase.arithmetic.binarytree;

import java.util.LinkedList;

/**
 * @author wxw
 * @Description:XX
 * @date 2019/6/9
 */
public class BinaryTreeSort {
	public void preOrder(BinaryTreeNode root){
		if(null!=root){
			System.out.println(root.getData());
			preOrder(root.getLeft());
			preOrder(root.getRight());
		}
	}

	public void preOrderNorec(BinaryTreeNode root){
		LinkedList<BinaryTreeNode> list =new LinkedList<>();
		list.push(root);
		BinaryTreeNode node =null;
		while (!list.isEmpty()){
			node =list.poll();
			assert node != null;
			System.out.println(node.getData()+"");
			if(node.getRight()!=null){
				list.push(node.getRight());
			}
			if(node.getLeft()!=null){
				list.push(node.getLeft());
			}
		}
	}

	public static void main(String[] args) {
		BinaryTreeNode node10=new BinaryTreeNode(10,null,null);
		BinaryTreeNode node8=new BinaryTreeNode(8,null,null);
		BinaryTreeNode node9=new BinaryTreeNode(9,null,node10);
		BinaryTreeNode node4=new BinaryTreeNode(4,null,null);
		BinaryTreeNode node5=new BinaryTreeNode(5,node8,node9);
		BinaryTreeNode node6=new BinaryTreeNode(6,null,null);
		BinaryTreeNode node7=new BinaryTreeNode(7,null,null);
		BinaryTreeNode node2=new BinaryTreeNode(2,node4,node5);
		BinaryTreeNode node3=new BinaryTreeNode(3,node6,node7);
		BinaryTreeNode node1=new BinaryTreeNode(1,node2,node3);

		BinaryTreeSort tree=new BinaryTreeSort();
		//采用递归的方式进行遍历
		System.out.println("-----前序遍历------");
//		tree.preOrder(node1);
		System.out.println();
		//采用非递归的方式遍历
//		tree.preOrderNonRecursive(node1);
		System.out.println();

		tree.preOrderNorec(node1);


		//采用递归的方式进行遍历
		System.out.println("-----中序遍历------");
//		tree.inOrder(node1);
		System.out.println();
		//采用非递归的方式遍历
//		tree.inOrderNonRecursive(node1);
		System.out.println();

		//采用递归的方式进行遍历
		System.out.println("-----后序遍历------");
//		tree.postOrder(node1);
		System.out.println();
		//采用非递归的方式遍历
//		tree.postOrderNonRecursive(node1);
		System.out.println();

		//采用递归的方式进行遍历
		System.out.println("-----层序遍历------");
//		tree.levelOrder(node1);
		System.out.println();
	}
}

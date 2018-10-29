package com.demo.javase.arithmetic.binarytree;

/**
 * @author wxw
 * @Description:
 * 下面是与树相关的一些概念：
 * 1）平衡 vs. 非平衡：平衡二叉树中，每个节点的左右子树的深度相差至多为1（1或0）。
 * 2）满二叉树（Full Binary Tree）：除叶子节点以为的每个节点都有两个孩子。
 * 3）完美二叉树（Perfect Binary Tree）：是具有下列性质的满二叉树：所有的叶子节点都有相同的深度或处在同一层次，且每个父节点都必须有两个孩子。
 * 4）完全二叉树（Complete Binary Tree）：二叉树中，可能除了最后一个，每一层都被完全填满，且所有节点都必须尽可能想左靠。
 * 译者注：完美二叉树也隐约称为完全二叉树。完美二叉树的一个例子是一个人在给定深度的祖先图，因为每个人都一定有两个生父母。
 * 完全二叉树可以看成是可以有若干额外向左靠的叶子节点的完美二叉树。疑问：完美二叉树和满二叉树的区别？（
 * @date 2018/10/29
 */
public class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;
}

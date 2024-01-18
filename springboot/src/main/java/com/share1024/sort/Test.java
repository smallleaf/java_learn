package com.share1024.sort;

/**
 * @Description
 * @Date 2024年01月10日
 * @Created by yesheng
 */
public class Test {

    public static class TreeNode {

        public TreeNode(int value) {
            this.value = value;
        }

        public int value;
        public TreeNode leftNode;
        public TreeNode rightNode;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode root7 = new TreeNode(7);
        TreeNode root8 = new TreeNode(8);
        TreeNode root9 = new TreeNode(9);
        TreeNode root4 = new TreeNode(4);
        root.rightNode = root8;
        root8.leftNode = root7;
        root8.rightNode = root9;
        root.leftNode = root4;
        int result = getMaxValue(root,4);
        System.out.println(result);

    }

    public static int index = 0;

    public static int getMaxValue(TreeNode node ,int k) {
        if(node == null) {
            return 0;
        }
        int value = node.value;
        int result = getMaxValue(node.rightNode,k);
        if(result > 0) {
            return result;
        }
        index++;
        if(index == k) {
            return value;
        }
        getMaxValue(node.leftNode,k);

        return 0;
    }

}

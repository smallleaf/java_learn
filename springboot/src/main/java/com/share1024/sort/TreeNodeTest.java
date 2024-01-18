package com.share1024.sort;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * @Description
 * @Date 2024年01月13日
 * @Created by yesheng
 */
public class TreeNodeTest {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    @Test
    public void test() {
        TreeNodeTest treeNodeTest = new TreeNodeTest();
        TreeNode root = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        root.left = root2;
        root.right = root3;
        int sum = treeNodeTest.sumNumbers(root);
        System.out.println(sum);
    }

    List<Integer> result = new ArrayList();
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) {
            return result;
        }
        result.add(root.val);
        rightSideView(root.right);
        return result;
    }

    @Test
    public void test2() {
        TreeNodeTest treeNodeTest = new TreeNodeTest();
        TreeNode root = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        root.right = root2;
        treeNodeTest.rightSideView(root);
        System.out.println("+==");
    }


    private List<List<Integer>> savePathList = new ArrayList();
    private Stack<Integer> pathNodes = new Stack();
    public int sumNumbers(TreeNode root) {
        if(root == null) {
            return 0;
        }
        getPath(root);
        //计算和
        int sum = 0;
        for(List<Integer> path : savePathList) {
            sum+=getSum(path);
        }
        return sum;
    }
    public void incOne(List<Integer> list) {
        for(Integer i : list) {
            i++;
        }
    }

    public int sum = 0;
    public Stack<Integer> path = new Stack();
    public int pseudoPalindromicPaths (TreeNode root) {
        if(root == null) {
            return 0;
        }
        path.push(root.val);
        pseudoPalindromicPaths(root.left);
        pseudoPalindromicPaths(root.right);
        //校验当前路径是否为伪回文
        check();
        path.pop();
        return sum;
    }

    @Test
    public void test5() {
        TreeNodeTest treeNodeTest = new TreeNodeTest();
        TreeNode root = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root31 = new TreeNode(3);
        TreeNode root1 = new TreeNode(1);
        TreeNode root11 = new TreeNode(1);
        TreeNode root12 = new TreeNode(1);
        root.left = root3;
        root.right = root1;

        root3.left = root31;
        root3.right = root11;

        root1.right = root12;

        int result = treeNodeTest.pseudoPalindromicPaths(root);
        System.out.println(result);
    }

    @Test
    public void testCreate() {
        LinkedList<String> nodes = new LinkedList<>();
        nodes.add("1");
        nodes.add("2");
        nodes.add(empty);
        nodes.add(empty);
        nodes.add("3");
        nodes.add("4");
        nodes.add(empty);
        nodes.add(empty);
        nodes.add("5");
        nodes.add(empty);
        nodes.add(empty);

        TreeNode treeNode = create(nodes);
        System.out.println("===");
    }

    public String empty = "#";

    @Test
    public void testPathSum() {
        LinkedList<String> nodes = new LinkedList<>();
        nodes.add("10");
        nodes.add("5");
        nodes.add("3");
        nodes.add("3");
        nodes.add(empty);
        nodes.add(empty);
        nodes.add("-2");
        nodes.add(empty);
        nodes.add(empty);
        nodes.add("2");
        nodes.add(empty);
        nodes.add("1");
        nodes.add(empty);
        nodes.add(empty);
        nodes.add("-3");
        nodes.add(empty);
        nodes.add("11");
        nodes.add(empty);
        nodes.add(empty);

        TreeNode treeNode = create(nodes);
        int result = pathSum(treeNode,8);
        System.out.println("===");
    }

    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<Long, Integer>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = 0;
        curr += root.val;

        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }

    @Test
    public void testCreate2() {
        TreeNode node = createV2(Lists.newArrayList(9,3,4,1,2,6),Lists.newArrayList(4,3,1,9,2,6),0,6);
        PriorityQueue<TreeNode> queue = new PriorityQueue<>((a,b)->a.val-b.val);
        System.out.println("===");
    }


    @Test
   public void createV2ShouldReturnCorrectTreeStructure() {
        TreeNodeTest treeNodeTest = new TreeNodeTest();
        List<Integer> preList = Arrays.asList(1, 2, 4, 5, 3, 6, 7);
        List<Integer> midList = Arrays.asList(4, 2, 5, 1, 6, 3, 7);
        TreeNode root = treeNodeTest.createV2(preList, midList, 0, 7);
        assertEquals(1, root.val);
        assertEquals(2, root.left.val);
        assertEquals(3, root.right.val);
        assertEquals(4, root.left.left.val);
        assertEquals(5, root.left.right.val);
        assertEquals(6, root.right.left.val);
        assertEquals(7, root.right.right.val);
    }

    public TreeNode createV2(List<Integer> preList,List<Integer> midList,int start,int end) {
        if(start >= end) {
            return null;
        }
        //第一个节点就是中间节点
        int firstValue = findFirstValue(preList,midList.subList(start,end));
        int index = midList.indexOf(firstValue);
        TreeNode root = new TreeNode(firstValue);
        root.left = createV2(preList,midList,start,index);
        root.right = createV2(preList,midList,index +1 ,end);
        return root;
    }

    private int findFirstValue(List<Integer> preList,List<Integer> midList) {
        for (int i = 0; i < preList.size(); i++) {
            if(midList.contains(preList.get(i))) {
                return preList.get(i);
            }
        }
        return 0;
    }


    public TreeNode create(LinkedList<String> nodes) {
        if(nodes.isEmpty()) {
            return null;
        }
        String data = nodes.removeFirst();
        if(data.equals(empty)) {
            return null;
        }
        TreeNode treeNode = new TreeNode(Integer.valueOf(data));
        treeNode.left = create(nodes);
        treeNode.right = create(nodes);
        return treeNode;
    }


    public void check() {
        List<Integer> nums = new ArrayList(path);
        Map<Integer,Integer> numCountMap = new HashMap();
        for(int i = 0 ;i<nums.size();i++) {
            int num = nums.get(i);
            numCountMap.put(num,numCountMap.getOrDefault(num,0) + 1);
        }
        //如果是奇数个数,除了中间，其他的为2个相同的
        int jishuNum = 0;
        int oddNum = 0;
        //如果是偶数个数，都是成对出现
        for(Map.Entry<Integer,Integer> entry:numCountMap.entrySet()) {
            if(entry.getValue() % 2 == 0 ){
                oddNum++;
            }else {
                jishuNum++;
            }
        }
        if(nums.size() % 2  == 0 && jishuNum == 0) {
            sum++;
        }
        if(nums.size() % 2  != 0 && jishuNum == 1) {
            sum++;
        }
    }


    public int getSum(List<Integer> path) {
        if(path.isEmpty()) {
            return 0;
        }
        int sum = 0;
        int base = 1;
        for(int i=0;i<path.size();i++) {
            sum+=path.get(path.size()-i-1) * base;
            base*=10;
        }
        return sum;
    }

    public void getPath(TreeNode root) {
        if(root == null) {
            return;
        }
        //记录数据
        pathNodes.add(root.val);
        //遍历左节点
        getPath(root.left);
        //遍历右节点
        getPath(root.right);
        //如果没有左右节点了
        savePathList.add(new ArrayList(pathNodes));
        pathNodes.pop();
    }
}

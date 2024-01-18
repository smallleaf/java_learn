package com.share1024.sort;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @Description
 * @Date 2024年01月08日
 * @Created by yesheng
 */
public class ListNodeTest {


    @Test
    public void merge() {
        ListNode node1 = createNode(Lists.newArrayList(1,1,4,6,8));
        ListNode node2 = createNode(Lists.newArrayList(3,5,7));

        ListNode result = mergeTow(node1,node2);
        printValue(result);
    }

    @Test
    public void test2() {
        Stack<Integer> pathNodes = new Stack();
        pathNodes.add(1);
        pathNodes.add(2);


        List<Integer> datas = new ArrayList<>(pathNodes);
        pathNodes.pop();
        System.out.println("===");
    }

    @Test
    public void merget() {
        ListNode node1 = createNode(Lists.newArrayList(1,1,4,6,8));
        ListNode node2 = createNode(Lists.newArrayList(3,5,6,7));

        ListNode result = new ListNode();
        ListNode tmp = result;
        while(node1 != null && node2 != null) {
            if(node1.value < node2.value) {
                tmp.next = node1;
                node1 = node1.next;
                tmp = tmp.next;
            }else {
                tmp.next = node2;
                node2 = node2.next;
                tmp = tmp.next;
            }
        }
        while(node1 != null) {
            tmp.next = node1;
            node1 = node1.next;
            tmp = tmp.next;
        }

        while(node2 != null) {
            tmp.next = node2;
            node2 = node2.next;
            tmp = tmp.next;
        }

        printValue(result.next);
    }

    public ListNode mergeTow(ListNode node1,ListNode node2) {
        if(node1 == null) {
            return  node2;
        }
        if(node2 == null) {
            return node1;
        }

        if(node1.value < node2.value) {
            node1.next = mergeTow(node1.next,node2);
            return node1;
        }else {
            node2.next = mergeTow(node1,node2.getNext());
            return node2;
        }
    }


    @Test

    public void change() {
        ListNode head = createNode(Lists.newArrayList(2,1,4,6,8));
        //2,8,1,6,4,5
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        //找到中间位置
        while(slow != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
            if(fast.next != null) {
                fast = fast.next;
            }
        }
        ListNode tmpSlow = slow.next;
        slow.next = null;
        //first为中间位置
        //反转尾部的链表
        tmpSlow = reverse(tmpSlow);
        ListNode result = dummy;
        while(head != null && tmpSlow != null) {
            ListNode tmp = head.next;
            result.next = head;
            head.next = tmpSlow;
            result = tmpSlow;

            head = tmp;
            tmpSlow = tmpSlow.next;
        }

        if(head != null) {
            result.next = head;
        }
        printValue(dummy.next);

    }


    public ListNode reverse(ListNode nodes) {
        //新的头结点
        ListNode pre = null;
        //当前节点
        ListNode cur = nodes;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    @Test
    public void twoPoint() {
        ListNode nodes = createNode(Lists.newArrayList(2,1,4,5,6,8));

        //删除导数第k个节点
        ListNode listNode = deletek(nodes,2);
        printValue(listNode);
    }

    public void printValue(ListNode node) {
        while(node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }



    public ListNode deletek(ListNode nodes,int k) {
        if(k <= 0) {
            return nodes;
        }
        ListNode dummy = new ListNode();
        dummy.next = nodes;
        ListNode firstPoint = dummy;
        ListNode secondPoint = nodes;
        //先走k步
        for(int i = 0;i<k;i++) {
            secondPoint = secondPoint.next;
        }
        //找到k的位置
        while(secondPoint!= null) {
            firstPoint = firstPoint.next;
            secondPoint = secondPoint.next;
        }
        firstPoint.next = firstPoint.next.next;
        return dummy.next;
    }


    public ListNode createNode(List<Integer> values) {
        ListNode head = null;
        ListNode next = null;
        for (Integer value : values) {
            if(head == null) {
                head = new ListNode();
                head.setValue(value);
                next = head;
            }else {
                ListNode node = new ListNode();
                node.setValue(value);
                next.setNext(node);
                next = next.next;
            }
        }
        return head;
    }

    public static class ListNode {
        private int value;

        private ListNode next;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }
}

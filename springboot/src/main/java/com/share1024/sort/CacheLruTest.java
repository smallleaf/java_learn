package com.share1024.sort;

import java.util.Map;

/**
 * @Description
 * @Date 2024年01月09日
 * @Created by yesheng
 */
public class CacheLruTest<K,V> {

    private ListNode tail;
    private ListNode head;

    private Map<K,ListNode> map;

    private int capacity;

    public static class ListNode<K,V> {
        private K key;
        private V value;
        private ListNodeTest.ListNode next;
        private ListNodeTest.ListNode pre;

        public ListNode(K key,V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public ListNodeTest.ListNode getNext() {
            return next;
        }

        public void setNext(ListNodeTest.ListNode next) {
            this.next = next;
        }

        public ListNodeTest.ListNode getPre() {
            return pre;
        }

        public void setPre(ListNodeTest.ListNode pre) {
            this.pre = pre;
        }
    }
}

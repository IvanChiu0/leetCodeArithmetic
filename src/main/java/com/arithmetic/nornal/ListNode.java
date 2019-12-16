package com.arithmetic.nornal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 */
public class ListNode {

    private int value;

    private ListNode next;

    public ListNode(int x) {
        this.value = x;
    }

    /**
     * 两数相加
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> list1 = transformToList(l1);
        List<Integer> list2 = transformToList(l2);
        List<Integer> newList = new ArrayList<Integer>();
        if (list1.size() >= list2.size()) {
            newList = addTwoNumbersList(list1,list2);
        }else {
            newList = addTwoNumbersList(list2,list1);
        }
        ListNode listNode = new ListNode(newList.get(0));
        newList.remove(0);
        get(listNode,newList);
        return listNode;
    }

    /**
     * 递归返回next
     * @param listNode
     * @param x
     * @return
     */
    public static ListNode get(ListNode listNode , List<Integer> x) {
        if (x.isEmpty()) {
            listNode.next = null;
            return listNode;
        }
        ListNode next = new ListNode(x.get(0));
        x.remove(0);
        listNode.next =get(next,x);
        return listNode;
    }

    /**
     * 把ListNode转成lsit
     * @param listNode
     * @return
     */
    public static List<Integer> transformToList(ListNode listNode) {
        List<Integer> list = new ArrayList<Integer>();
        while (listNode != null) {
            list.add(listNode.value);
            listNode = listNode.next;
        }
        return list;
    }

    /**
     * 把两个list按相同索引相加，满10进1
     * @param longer
     * @param shorter
     * @return
     */
    public static List<Integer> addTwoNumbersList(List<Integer> longer,List<Integer> shorter) {
        List<Integer> newList = new ArrayList<Integer>();
        for (int i = 0;i < longer.size(); i++) {
            try {
                int n1 = longer.get(i);
                int n2 = shorter.get(i);
                int sum = n1 + n2;
                int h = sum / 10;
                //满10进1
                if (h != 0 ){
                    if((i+1) < longer.size()) {
                        longer.set(i+1,longer.get(i+1)+h);
                    }else {
                        longer.add(h);
                    }
                }
                int l = sum % 10;
                newList.add(l);
            }catch (Exception e){
                int n1 = longer.get(i);
                int h = n1 / 10;
                int l = n1 % 10;
                if (h != 0) {
                    if ((i+1) < longer.size()) {
                        longer.set(i+1,longer.get(i+1)+h);
                    }else {
                        longer.add(h);
                    }
                }
                newList.add(l);
            }
        }
        return newList;
    }



    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(5);
        ListNode l = addTwoNumbers(l1,l2);
        System.out.println(l.toString());
    }

}

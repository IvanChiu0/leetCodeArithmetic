package com.arithmetic.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 */
public class Median {

    /**
     * 两个有效数组的归并排序
     * @param a
     * @param b
     * @return
     */
    public static int[] merger(int[] a , int[] b) {
        if (a.length == 0 && b.length == 0) {
            return null;
        }else if (a.length == 0) {
            return b;
        }else if (b.length == 0) {
            return a;
        }else {
            int i , j;
            int x = a.length;
            int y = b.length;
            List<Integer> list = new ArrayList<Integer>();
            //必须把其中一个遍历完才结束
            for (i =0, j = 0 ; i < x && j < y;) {
                if (a[i] < b[j]) {
                    list.add(a[i]);
                    i++;
                }else {
                    list.add(b[j]);
                    j++;
                }
            }
            //若上面的循环还没遍历完a或b，则继续
            while (i < x) {
                list.add(a[i]);
                i++;
            }
            while (j < y) {
                list.add(b[j]);
                j++;
            }
            int[] c = new int[list.size()];
            for (int idx = 0 ; idx < list.size(); idx++) {
                c[idx] = list.get(idx);
            }
            return c;
        }
    }

    /**
     * 获取两个有序数组的中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] c = merger(nums1,nums2);
        if (c == null) {
            return 0.0;
        }
        int k = c.length;
        if (k % 2 == 0) {
            return (c[k/2 - 1] + c[(k/2)]) / 2.0;
        }else {
            if (k == 1) {
                return c[k-1];
            }
            return (c[(k-1)/2]);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{2};
        int[] b = new int[]{};
        int[] c = merger(a,b);
        for (int i : c) {
            System.out.println(i);
        }
        double median = findMedianSortedArrays(a,b);
        System.out.println(median);
    }
}

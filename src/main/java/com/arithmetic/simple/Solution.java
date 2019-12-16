package com.arithmetic.simple;

import java.util.*;

/**
 * 给定一个整数数组 nums 和一个目标值 target，
 * 请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标
 */
public class Solution {

    public static Map<Integer,String> twoSum1(int[] nums , int target) {
        int count = 0;
        Map<Integer,String> index = new HashMap<Integer, String>();
        for (int i = 0 ; i < nums.length ; i++) {
            int n1 = nums[i];
            int n2 = target - n1;
            for (int j = i+1 ; j < nums.length;j++) {
                int n = nums[j];
                if (n2 == n) {
                    String idx = String.valueOf(i) + "-" + String.valueOf(j);
                    index.put(count,idx);
                    count++;
                }
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,7,2,3,4,5,6,7,7};
        int target = 11;
        Map<Integer,String> index = twoSum1(nums,target);
        System.out.println(index);
    }
}

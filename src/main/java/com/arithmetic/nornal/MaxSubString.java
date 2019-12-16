package com.arithmetic.nornal;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 思路：
 * 1、获取第一个字符，
 * 2、循环之后的字符做比较，若遇到不相同则放入list中，遇到相同则得到当前字符往后的最大长度，并跳出循环
 * 3、继续第一步直到结束
 */
public class MaxSubString {

    /**
     * 方法1：暴力解法
     * @param str
     * @return
     */
    public static int lengthOfLongestSubstring(String str) {
        char[] strs = str.toCharArray();
        if (strs.length == 0) {
            return 0;
        }
        int longest = 1;
        stand1 : for (int i = 0 ; i < strs.length ; i++) {
            List<String> start = new ArrayList<String>();
            start.add(String.valueOf(strs[i]));
            stand2 : for (int j = i + 1 ; j < strs.length ; j++) {
                String next = String.valueOf(strs[j]);
                if (!start.contains(next)) {
                    start.add(next);
                }else {
                    break stand2;
                }
            }
            int now = start.size();
            if (now > longest) {
                longest = now;
            }
        }
        return longest;
    }

    /**
     * 方法2：滑动窗口
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        char[] strs = s.toCharArray();
        if (strs.length == 0) {
            return 0;
        }
        List<String> list = new ArrayList<String>();
        int longest = 1;
        for (char str : strs) {
            if (!list.contains(String.valueOf(str))) {
                list.add(String.valueOf(str));
            }else {
                list.add(String.valueOf(str));
                int index = list.indexOf(String.valueOf(str));
                for (int i = index ; i >= 0 ; i--) {
                    list.remove(i);
                }
            }
            int now = list.size();
            if (now > longest) {
                longest = now;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        String str = "ohomm";
//        int longest = lengthOfLongestSubstring(str);
        int longest = lengthOfLongestSubstring2(str);
        System.out.println(longest);
    }
}

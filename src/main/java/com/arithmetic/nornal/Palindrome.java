package com.arithmetic.nornal;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串
 */
public class Palindrome {

    /**
     * 思路：暴力解法，超时
     * 1、若是回文字符串，那么首尾肯定相同，所以从开头找出首尾相同的字符串
     * 2、按照索引位判断其是否回文
     * @param s
     * @return
     */
    public static String longestPalindrome1(String s) {
        Map<Integer,String> longestString = new HashMap<Integer, String>();
        int longest = 0;
        if (s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        longestString.put(longest,String.valueOf(chars[0]));
        stand1 : for (int i = 0 ; i < chars.length ; i++) {
            char start = chars[i];
            stand2 : for (int j = i + 1 ; j < chars.length; j++) {
                char end = chars[j];
                if (String.valueOf(start).equals(String.valueOf(end))) {
                    String str = s.substring(i,j+1);
                    boolean isRome = judge(str);
                    if (isRome) {
                        if (str.length() > longest) {
                            longest = str.length();
                            longestString.put(longest,str);
                        }
                    }
                }
            }
        }
        return longestString.get(longest);
    }

    public static boolean judge(String str) {
        String builder = new StringBuilder(str).reverse().toString();
        if (str.equals(builder)) {
            return true;
        }
        return false;
    }

    /**
     * 中心扩展
     * 思路：
     * 1、分奇偶
     * 由中心点向外扩展，对比得出
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        Map<Integer,String> longestString = new HashMap<Integer, String>();
        int longest =0;
        if (s.length() == 0) {
            return s;
        }
        int start = 0,end =0;
        for (int i = 0 ; i< s.length(); i++) {
            int[] lr1 = expandAroundCenter(s,i,i);
            int[] lr2 = expandAroundCenter(s,i,i+1);
            int len1 = lr1[1] - lr1[0] - 1;
            int len2 = lr2[1] - lr2[0] - 1;
            int len = Math.max(len1,len2);
            if (len > (end - start+1)) {
                if (len1 > len2) {
                    start = lr1[0]+1;
                    end = lr1[1]-1;
                }else {
                    start = lr2[0]+1;
                    end = lr2[1]-1;
                }
            }
        }
        return s.substring(start,end+1);
    }

    public static int[] expandAroundCenter(String s , int left,int right) {
        int L = left,R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return new int[]{L,R};
    }

    public static void main(String[] args) {
        String s = "babad";
        String palindrome = longestPalindrome2(s);
        System.out.println("palindrome:" + palindrome);
    }
}

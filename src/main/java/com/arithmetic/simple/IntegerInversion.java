package com.arithmetic.simple;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转
 */
public class IntegerInversion {

    public static int reverse(int x) {
        if (x == 0) {
            return x;
        }
        String xStr = String.valueOf(x);
        if (x < 0) {
            //假设-100
            //截取正数的部分：100
            String posStr = xStr.substring(1);
            //反转后001
            String newInversion = "-"+handle(posStr);
            try {
                int xInversion = Integer.valueOf(newInversion);
                return xInversion;
            }catch (NumberFormatException e) {
                return 0;
            }
        }else {
            String newInversion = handle(xStr);
            try {
                int xInversion = Integer.valueOf(newInversion);
                return xInversion;
            }catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    public static String handle(String posStr) {
        String inversion = new StringBuilder(posStr).reverse().toString();
        int index = 0;
        for (int i = 0 ; i < inversion.length(); i++) {
            String start = String.valueOf(inversion.charAt(i));
            if (start.equals("0")) {
                continue;
            }else {
                index = i;
                break;
            }
        }
        return inversion.substring(index);
    }

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }
}

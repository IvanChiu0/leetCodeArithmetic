package com.arithmetic.nornal;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 */
public class ZConvert {

    /**
     * 思路：
     * 先排序一下看看规律：0123456789ABCDEFGH
     * 0    8    G
     * 1   79   FH
     * 2  6 A  E
     * 3 5  B D
     * 4    C
     * 由上面可见（假如行数=r）
     * 1、每一个周期都是个2r-2元素的长度
     * 2、首行和末行是等差数列，A=10...H=17，那么这两行的差值=2r-2
     * 3、难就难在其他行的规律，现在把上面的二维数组变一下，两边压缩一下，得出下面：
     * 0 8 G
     * 179FH
     * 26AE
     * 35BD
     * 4 C
     * 可以看得处理其他行也是等差数列，并且等差值依然是2r-2，例如第二行的1和9，7和F
     * 如此解法就得出来了
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        int length = s.length();
        if (length <= numRows || numRows == 1) {
            return s;
        }

        //两行的情况
        if (numRows == 2) {
            int cycleNum = numRows;
            int lines = length % numRows == 0 ? length / cycleNum : (length / cycleNum) + 1;
            StringBuilder builder = new StringBuilder();
            for (int r = 0; r < numRows; r++) {
                int count = 0;
                for (int l = 0; l < lines; l++) {
                    if (r + count * cycleNum >= length) {
                        continue;
                    }
                    builder.append(s.charAt(r + count * cycleNum));
                    count++;
                }
            }
            return builder.toString();
        }

        //以下是当成3行以上的情况
        //奇数行的情况,周期性列数必然是numRows-1
        //周期的字符个数
        int cycleNum = numRows + numRows - 2;
        //列数就可以得知
        //假如没满一个周期
        int lines = 0;
        if (cycleNum > length) {
            lines = 2;
        }else if (length % cycleNum == 0){
            lines = (length / cycleNum) * 2;
        }else if (length % cycleNum <= numRows) {
            lines = (length / cycleNum) * 2 + 1;
        }else {
            lines = (length / cycleNum) * 2 + 2;
        }
        StringBuilder builder = new StringBuilder();
        for (int r = 0; r < numRows; r++) {
            for (int l = 0; l < lines; l++) {
                if (r == 0 || r == (numRows - 1)) {
                    //首行和末行的情况
                    if (l % 2 == 0) {
                        int idx = r + (l / 2) * cycleNum;
                        if (idx >= length) {
                            continue;
                        }
                        builder.append(s.charAt(idx));
                    }
                } else {
                    //中间的行
                    //其实就是间隔列成等差数列
                    if (l % 2 == 0) {
                        int idx = r + (l / 2) * cycleNum;
                        if (idx >= length) {
                            continue;
                        }
                        builder.append(s.charAt(idx));
                    } else {
                        int idx = (cycleNum - r) + ((l - 1) / 2) * cycleNum;
                        if (idx >= length) {
                            continue;
                        }
                        builder.append(s.charAt(idx));
                    }
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        String convert = convert(s, 5);
        System.out.println(convert);
    }
}

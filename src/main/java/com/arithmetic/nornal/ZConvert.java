package com.arithmetic.nornal;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 */
public class ZConvert {

    public static String convert(String s, int numRows) {
        int length = s.length();
        if (length <= numRows) {
            return s;
        }

        //两行的情况
        if (numRows == 2) {
            int cycleNum = numRows;
            int lines = length % numRows == 0 ? length / cycleNum : (length / cycleNum) + 1;
            StringBuilder builder = new StringBuilder();
            for (int r = 0 ; r < numRows ; r++) {
                int count = 0;
                for (int l = 0 ; l < lines ; l++) {
                    if (r + count * cycleNum >= length) {
                        continue;
                    }
                    builder.append(s.charAt(r+count*cycleNum));
                    count++;
                }
            }
            return builder.toString();
        }

        //以下是当成3行以上的情况
        if (numRows % 2 != 0) {
            //奇数行的情况
            //周期的字符个数
            int cycleNum = numRows + 1;
            //中间行
            int middleRow = (numRows - 1) / 2;
            //列数就可以得知
            int lines = length % cycleNum == 0 ? (length / cycleNum) * 2 : (length / cycleNum) * 2 + 1;
            //对于中间行的周期字符个数
            int midCycleNum = numRows - middleRow;
            StringBuilder builder = new StringBuilder();
            for (int r = 0; r < numRows; r++) {
                int count = 0;
                for (int l = 0; l < lines; l+=2) {
                    if (r != middleRow) {
                        //假如长度超出字符个数则跳过
                        if (r + count * cycleNum >= length) {
                            continue;
                        }
                        builder.append(s.charAt(r + count * cycleNum));
                        count++;
                    } else {
                        //假如长度超出字符个数则跳过
                        if (r + count * cycleNum >= length) {
                            continue;
                        }
                        if ((r + midCycleNum + count * cycleNum) >= length) {
                            builder.append(s.charAt(r + count * cycleNum));
                        }else {
                            builder.append(s.charAt(r + count * cycleNum))
                                    .append(s.charAt(r + midCycleNum + count * cycleNum));
                        }
                        count++;
                    }
                }
            }
            return builder.toString();
        } else {
            //周期字符个数
            int cycleNum = numRows + 2;
            //中间两行的下行
            int downMidRow = numRows / 2;
            //中间两行的上行
            int upMidRow = numRows / 2 - 1;
            //上行的周期字符个数
            int upMidRowCycleNum = numRows - upMidRow + 1;
            //下行的周期字符个数
            int downMidRowCycleNum = numRows - downMidRow;
            //列数
            int lines = 0;
            if (length % cycleNum == 0) {
                lines = (length / cycleNum) * 3;
            }else if (length % cycleNum > numRows) {
                lines = (length /cycleNum) * 3 + 2;
            }else {
                lines = (length / cycleNum) * 3 + 1;
            }
            StringBuilder builder = new StringBuilder();
            for (int r = 0; r < numRows; r++) {
                int count = 0;
                for (int l = 0; l < lines; l += 3) {
                    if (r != downMidRow && r != upMidRow) {
                        if ((r + count * cycleNum) > length) {
                            continue;
                        }
                        builder.append(s.charAt(r + count * cycleNum));
                        count++;
                    } else if (r == upMidRow) {
                        if ((r + count * cycleNum) >= length) {
                            continue;
                        }
                        if ((r + upMidRowCycleNum + count * cycleNum) >= length) {
                            builder.append(s.charAt(r + count * cycleNum));
                        }else {
                            builder.append(s.charAt(r + count * cycleNum))
                                    .append(s.charAt(r + upMidRowCycleNum + count * cycleNum));
                        }
                        count++;
                    } else if (r == downMidRow) {
                        if ((r + count * cycleNum) >= length) {
                            continue;
                        }
                        if ((r + downMidRowCycleNum + count * cycleNum) >= length) {
                            builder.append(s.charAt(r + count * cycleNum));
                        }else {
                            builder.append(s.charAt(r + count * cycleNum))
                                    .append(s.charAt(r + downMidRowCycleNum + count * cycleNum));
                        }
                        count++;
                    }
                }
            }
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        String s = "ABCDEF";
        String convert = convert(s, 5);
        System.out.println(convert);
    }
}

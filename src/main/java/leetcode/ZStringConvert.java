package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZStringConvert {
    public static String convert(String s, int numRows) {
        int maxLength = s.length(), row = 0, maxRows = Math.min(numRows, maxLength);
        Map<Integer, StringBuilder> map = new HashMap<>(maxRows);
        boolean downDirect = true;
        for (char c : s.toCharArray()) {
            StringBuilder builder = map.get(row);
            if (builder == null) {
                builder = new StringBuilder();
            }
            builder.append(c);
            map.put(row, builder);
            if (row == 0) {
                downDirect = true;
            } else if (row == numRows - 1) {
                downDirect = false;
            }
            row = downDirect ? row + 1 : row - 1;
        }
        StringBuilder result = new StringBuilder(numRows);
        for (int i = 0; i < maxRows; ++i) {
            result.append(map.get(i));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 3));
        System.out.println(convert("LEETCODEISHIRING", 4));
        System.out.println(convert("LEET", 4));
        System.out.println(convert("LEET", 5));
    }
}

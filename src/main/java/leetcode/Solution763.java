package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution763 {
    public static List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        int from = 0, end = from, sum = 0;
        char[] chars = S.toCharArray();
        while (end < chars.length) {
            do {
                int newEnd = S.lastIndexOf(chars[from]);
                end = Math.max(end, newEnd);
                if (end == chars.length - 1 || end == from) {
                    break;
                }
                ++from;
            } while (from < end);
            if (end == chars.length - 1) {
                result.add(end + 1 - sum);
                break;
            }
            ++from;
            end = from;
            result.add(end - sum);
            sum = end;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(Arrays.toString(partitionLabels(s).toArray()));

        s = "caedbdedda";
        System.out.println(Arrays.toString(partitionLabels(s).toArray()));

        s = "ababcbacadefegdehijhklij";
        System.out.println(Arrays.toString(partitionLabels(s).toArray()));
    }
}

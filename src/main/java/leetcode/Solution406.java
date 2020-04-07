package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solution406 {
    public static int[][] reconstructQueue(int[][] people) {
        int[][] ans = new int[people.length][2];

        int[][] temp = new int[people.length][2];
        for (int i = 0; i < people.length; ++i) {
            temp[i][0] = people[i][0];
            temp[i][1] = people[i][1];
        }

        for (int ai = 0; ai < people.length; ++ai) {
            int minW = Integer.MAX_VALUE, minIndex = 0;
            for (int i = 0; i < temp.length; ++i) {
                if (temp[i][1] == 0) {
                    if (temp[i][0] < minW) {
                        minW = temp[i][0];
                        minIndex = i;
                    }
                }
            }

            for (int i = 0; i < temp.length; ++i) {
                if (temp[i][0] <= minW) {
                    temp[i][1]--;
                }
            }

            ans[ai][0] = people[minIndex][0];
            ans[ai][1] = people[minIndex][1];
        }

        return ans;
    }

    public static int[][] reconstructQueue2(int[][] people) {
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o2[0] - o1[0];
                }
            }
        };
        Arrays.sort(people, comparator);
        List<int[]> output = new LinkedList<>();
        for (int[] p : people) {
            output.add(p[1], p);
        }
        return output.toArray(new int[people.length][2]);
    }

    public static void main(String[] args) {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] sorted = reconstructQueue(people);
        for (int[] array : sorted) {
            System.out.print(Arrays.toString(array) + " ");
        }
        System.out.println();
        int[][] sorted2 = reconstructQueue2(people);
        for (int[] array : sorted2) {
            System.out.print(Arrays.toString(array) + " ");
        }
    }
}

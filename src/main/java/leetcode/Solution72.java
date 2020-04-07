package leetcode;

public class Solution72 {
    public static int minDistance(String word1, String word2) {
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int n1 = chars1.length + 1, n2 = chars2.length + 1;
        int[][] matrix = new int[n1][n2];
        for (int i = 0; i < n1; ++i) {
            matrix[i][0] = i;
        }
        for (int i = 0; i < n2; ++i) {
            matrix[0][i] = i;
        }
        for (int i = 1; i < n1; ++i) {
            for (int j = 1; j < n2; ++j) {
                int tmp = Math.min(matrix[i][j - 1] + 1, matrix[i - 1][j] + 1);
                if (chars1[i - 1] == chars2[j - 1]) {
                    matrix[i][j] = Math.min(tmp, matrix[i - 1][j - 1]);
                } else {
                    matrix[i][j] = Math.min(tmp, matrix[i - 1][j - 1] + 1);
                }
            }
        }
        return matrix[chars1.length][chars2.length];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
        System.out.println(minDistance("intention", "execution"));
    }
}

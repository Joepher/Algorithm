package leetcode;

public class Solution547 {
    public static int findCircleNum(int[][] M) {
        if (M.length == 1) {
            return 1;
        }
        UnionFind unionFind = new UnionFind(M.length);
        for (int i = 1; i < M.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (M[i][j] == 0) {
                    continue;
                }
                unionFind.union(i, j);
            }
        }
        return unionFind.getCount();
    }

    public static void main(String[] args) {
        int[][] M = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum(M));
    }
}

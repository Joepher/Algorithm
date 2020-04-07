package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution997 {
    public static int findJudge(int N, int[][] trust) {
        if (N == 1 && trust.length == 0) {
            return 1;
        }
        Map<Integer, Integer> judgeMap = new HashMap<>();
        int[] judges = new int[N + 1];

        for (int[] trustPair : trust) {
            Integer follows = judgeMap.get(trustPair[1]);
            judgeMap.put(trustPair[1], follows == null ? 1 : ++follows);
            judges[trustPair[0]] = 1;
        }
        int maxFollower = N - 1;
        for (Integer judge : judgeMap.keySet()) {
            Integer follows = judgeMap.get(judge);
            if (judges[judge] == 0 && follows != null && follows == maxFollower) {
                return judge;
            }
        }
        return -1;
    }

    public static int findJudge2(int N, int[][] trust) {
        int[] follows = new int[N + 1];
        int[] beFollows = new int[N + 1];
        for (int[] pair : trust) {
            int follow = pair[0];
            int judge = pair[1];
            ++follows[follow];
            ++beFollows[judge];
        }
        int max = N - 1;
        for (int i = 1; i <= N; ++i) {
            if (follows[i] == 0 && beFollows[i] == max) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[][] trust = {{1, 2}};
        System.out.println(findJudge(2, trust));
        System.out.println(findJudge2(2, trust));

        trust = new int[][]{{1, 3}, {2, 3}};
        System.out.println(findJudge(3, trust));
        System.out.println(findJudge2(3, trust));

        trust = new int[][]{{1, 3}, {2, 3}, {3, 1}};
        System.out.println(findJudge(3, trust));
        System.out.println(findJudge2(3, trust));

        trust = new int[][]{{1, 2}, {2, 3}};
        System.out.println(findJudge(3, trust));
        System.out.println(findJudge2(3, trust));

        trust = new int[][]{{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}};
        System.out.println(findJudge(4, trust));
        System.out.println(findJudge2(4, trust));

        trust = new int[][]{};
        System.out.println(findJudge(1, trust));
        System.out.println(findJudge2(1, trust));
    }
}

package leetcode;

import java.util.*;

public class Solution1169 {
    public static List<String> invalidTransactions(String[] transactions) {
        List<String> invalidList = new ArrayList<>();
        Map<String, Map<String, List<Integer>>> userMap = new HashMap<>();
        for (String transaction : transactions) {
            String[] array = transaction.split(",");
            if (Integer.parseInt(array[2]) > 1000) {
                invalidList.add(transaction);
                continue;
            }
            int curTime = Integer.parseInt(array[1]);
            Map<String, List<Integer>> detailMap = userMap.get(array[0]);
            if (detailMap == null) {
                detailMap = new HashMap<>();
                List<Integer> timeList = new ArrayList<>();
                timeList.add(curTime);
                detailMap.put(array[3], timeList);
                continue;
            }
            for (Map.Entry<String, List<Integer>> entry : detailMap.entrySet()) {
                if (array[3].equals(entry.getKey())) {
                    continue;
                }
                for (Integer time : entry.getValue()) {
                    if (curTime - time <= 60 || time - curTime <= 60) {
                        invalidList.add(transaction);
                        break;
                    }
                }
            }
        }

        return invalidList;
    }

    public static void main(String[] args) {
        String[] transactions = {"alice,20,800,mtv", "alice,50,100,beijing"};
        System.out.println(invalidTransactions(transactions));
    }
}

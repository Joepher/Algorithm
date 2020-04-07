package leetcode;

import java.util.*;

public class Solution22 {
    private static class Node {
        private String data;
        private int left;
        private int right;

        private Node(String data, int left, int right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        build(list, "", 0, 0, n);

        System.out.println(Arrays.toString(list.toArray()));
        return list;
    }

    private static void build(List<String> list, String str, int left, int right, int n) {
        if (left == n && right == n) {
            list.add(str);
            return;
        }
        if (left < n) {
            if (left > right) {
                build(list, str + "(", left + 1, right, n);
                build(list, str + ")", left, right + 1, n);
            }
            if (left == right) {
                build(list, str + "(", left + 1, right, n);
            }
        }
        if (left == n) {
            build(list, str + ")", left, right + 1, n);
        }
    }

    public static List<String> generateParenthesisDfs(int n) {
        List<String> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(new Node("", 0, 0));
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (current.left == n && current.right == n) {
                list.add(current.data);
                continue;
            }
            if (current.left < n) {
                if (current.left > current.right) {
                    stack.push(new Node(current.data + "(", current.left + 1, current.right));
                    stack.push(new Node(current.data + ")", current.left, current.right + 1));
                }
                if (current.left == current.right) {
                    stack.push(new Node(current.data + "(", current.left + 1, current.right));
                }
            }
            if (current.left == n) {
                stack.push(new Node(current.data + ")", current.left, current.right + 1));
            }
        }

        System.out.println(Arrays.toString(list.toArray()));
        return list;
    }

    public static List<String> generateParenthesisBfs(int n) {
        List<String> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", 0, 0));
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.left == n && current.right == n) {
                list.add(current.data);
                continue;
            }
            if (current.left < n) {
                if (current.left > current.right) {
                    queue.offer(new Node(current.data + "(", current.left + 1, current.right));
                    queue.offer(new Node(current.data + ")", current.left, current.right + 1));
                }
                if (current.left == current.right) {
                    queue.offer(new Node(current.data + "(", current.left + 1, current.right));
                }
            }
            if (current.left == n) {
                queue.offer(new Node(current.data + ")", current.left, current.right + 1));
            }
        }

        System.out.println(Arrays.toString(list.toArray()));
        return list;
    }

    public static List<String> generateParenthesisDP(int n) {
        List<List<String>> dpList = new ArrayList<>();
        List<String> dp0 = new ArrayList<>();
        dp0.add("");
        dpList.add(dp0);

        for (int i = 1; i <= n; ++i) {
            List<String> current = new ArrayList<>();
            for (int j = 0; j < i; ++j) {
                List<String> str1 = dpList.get(j);
                List<String> str2 = dpList.get(i - 1 - j);
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        current.add("(" + s1 + ")" + s2);
                    }
                }
            }
            dpList.add(current);
        }

        System.out.println(Arrays.toString(dpList.get(n).toArray()));
        return dpList.get(n);
    }

    public static void main(String[] args) {
        generateParenthesis(3);
        generateParenthesis(0);

        generateParenthesisBfs(3);
        generateParenthesisBfs(0);

        generateParenthesisDfs(3);
        generateParenthesisDfs(0);
    }
}

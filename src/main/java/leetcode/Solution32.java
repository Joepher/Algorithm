package leetcode;

import java.util.Stack;

public class Solution32 {
    public static int longestValidParentheses(String s) {
        int maxLength = 0, left = 0, right = 0;
        char[] array = s.toCharArray();
        for (char c : array) {
            if (c == '(') {
                ++left;
            } else {
                ++right;
            }
            if (left < right) {
                maxLength = Math.max(maxLength, left + right - 1);
                left = right = 0;
            }
        }

        left = right = 0;
        for (int i = array.length - 1; i >= 0; --i) {
            if (array[i] == ')') {
                ++right;
            } else {
                ++left;
            }
            if (right < left) {
                maxLength = Math.max(maxLength, left + right - 1);
                left = right = 0;
            }
        }

        return maxLength;
    }

    public static int longestValidParenthesesStack(String s) {
        int maxLength = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()"));
        System.out.println(longestValidParenthesesStack("(()"));

        System.out.println(longestValidParentheses(")()())"));
        System.out.println(longestValidParenthesesStack(")()())"));
    }
}

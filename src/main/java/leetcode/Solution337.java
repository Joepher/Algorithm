package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution337 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int money = root.val;
        if (root.left != null) {
            money += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            money += rob(root.right.left) + rob(root.right.right);
        }

        return Math.max(money, rob(root.left) + rob(root.right));
    }

    public static int rob2(TreeNode root) {
        Map<TreeNode, Integer> moneyMap = new HashMap<>();
        return rob2(root, moneyMap);
    }

    private static int rob2(TreeNode root, Map<TreeNode, Integer> moneyMap) {
        if (root == null) {
            return 0;
        }
        int money = root.val;
        if (root.left != null) {
            money += rob2(root.left.left, moneyMap) + rob2(root.left.right, moneyMap);
        }
        if (root.right != null) {
            money += rob2(root.right.left, moneyMap) + rob2(root.right.right, moneyMap);
        }
        int value = Math.max(money, rob2(root.left, moneyMap) + rob2(root.right, moneyMap));
        moneyMap.put(root, value);

        return value;
    }

    public static int rob3(TreeNode root) {
        int[] result = rob3Internal(root);
        return Math.max(result[0], result[1]);
    }

    private static int[] rob3Internal(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] left = rob3Internal(root.left);
        int[] right = rob3Internal(root.right);
        int[] result = new int[2];
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = buildTree();
        TreeNode treeNode2 = buildTree2();

        System.out.println(rob(treeNode1));
        System.out.println(rob2(treeNode1));
        System.out.println(rob3(treeNode1));

        System.out.println(rob(treeNode2));
        System.out.println(rob2(treeNode2));
        System.out.println(rob3(treeNode2));
    }

    private static TreeNode buildTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        return root;
    }

    private static TreeNode buildTree2() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(1);

        return root;
    }
}

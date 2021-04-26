package com.example.study.demo.algorithms;

import java.util.*;
import java.util.concurrent.Future;

public class BinarySearch {
    long pre = Long.MIN_VALUE;

    public static void main(String[] args) {

//        int[] a = {1, 2, 3, 8, 4, 6, 5};
//        Arrays.sort(a);
//        int key = 4;
//        System.out.print(search(key, a) > 0);
//
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(1);
        node1.left = node2;
        node1.right = node4;
        node2.left = node7;
        node2.right = node3;
        node4.left = node5;
        node4.right = node6;
        TreeNode node = increasingBST(node1);
        System.out.println(node);
//
//        findSumPath(node1, 11);
//
//        Integer aaa = Integer.MIN_VALUE;
//        Integer c = 128;
//        Integer d = 128;
//        Integer bbb = Integer.MIN_VALUE;
//
//        System.out.print(aaa.equals(bbb));
//        System.out.print(c.equals(d));
//        byte[] place = new byte[64 * 1024 * 1024];
//        int aaaaa = 0;
//        System.gc();
        Integer i = new Integer(1);
        System.out.println(1 == i);
        Integer aa = Integer.valueOf(128);
        Integer bb = Integer.valueOf(128);
        System.out.println(bb.equals(aa));

        Integer cc = new Integer(128);
        Integer dd = new Integer(128);
        System.out.println(cc.equals(dd));

        int[] prices = {1, 2, 8, 3, 5, 7};
        maxProfit(prices);

        System.out.println(shipwithdays(prices, 4));
    }

    public static int search(int key, int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < arr[mid]) {
                hi = mid - 1;
            } else if (key > arr[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }


    /**
     * 判断二叉树是否是二叉搜索树
     * 中序遍历法
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }

    public static void findSumPath(TreeNode root, int sum) {
        if (root == null) {
            return;
        }

        List<List<Integer>> result = new ArrayList<>(16);
        dfs(result, new ArrayList<>(16), root, sum);
        System.out.print(result);
    }

    public static void dfs(List<List<Integer>> result, List<Integer> temp, TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        //判断是否为叶子节点
        if (root.left == null && root.right == null) {
            if(sum - root.val == 0) {
                temp.add(root.val);
                result.add(new ArrayList<>(temp));//引用问题
                temp.remove(temp.size() - 1);//删除最后一个节点，以便遍历另一字树
            }
            return;
        }

        temp.add(root.val);
        dfs(result, temp, root.left,sum - root.val);
        dfs(result, temp, root.right,sum - root.val);
        temp.remove(temp.size() - 1);
    }

    public static int maxProfit (int[] prices) {
        // write code here
        List<Integer> sum = new ArrayList<>();
        int len = prices.length;
        int i = 0;
        while(i < len) {
            int j = i + 1;
            while(j < len && prices[j] <= prices[i]) {
                j ++;
            }

            if (j < len){
                sum.add(prices[j] - prices[i]);
                i = j + 1;
            } else {
                i = len;
            }
        }

        System.out.println(sum);
        return 0;
    }

    public static TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> stack = new LinkedList<>();

        pushStack(stack, root);
        TreeNode p = stack.poll();
        TreeNode res = p;
        while (!stack.isEmpty()) {
            TreeNode temp = stack.poll();
            temp.left = null;
            temp.right = null;
            res.right = temp;
            res = res.right;
        }

        return p;
    }

    public static void pushStack(Queue<TreeNode> stack, TreeNode node) {
        if (null != node) {
            pushStack(stack, node.left);
            stack.offer(node);
            pushStack(stack, node.right);
        }
    }

    public static int shipwithdays(int[] ships, int days) {
        int len = ships.length;
        int first = 0;
        int[] indexs = new int[days - 1];
        for (int i = 0; i < days - 1; i++) {
            indexs[i] = (len / 4) * i;
        }
        System.out.println(indexs);

        return 0;
    }
}

package com.example.study.demo.algorithms;

import java.util.*;

public class NumberUtils {
    public static void main(String[] args) {
        int[] nums = {12, 23, 89, 8, 9, 10, 2, 0};
        System.out.println(largestSortedNums(nums));

        System.out.println(reverse(1463847412));

        int[] height = {8, 5, 1, 2, 3, 2, 1};
        System.out.println(maxArea(height));

        int[] prices = {1, 5, 1, 2, 3, 2, 6};
        System.out.println(maxEarnings(prices));

        int[] wins = {1, 3, 3, -1, 3, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(maxSlidingWindow2(wins, 3)));
    }

    /**
     * 给定一个整型数组，求重新排列后最大的数字，要求返回字符串
     * 思路：比较 S1 + S2 与 S2 + S1
     *
     * @param nums
     * @return
     */
    public static String largestSortedNums(int[] nums) {
        int n = nums.length;
        if (n == 1) return String.valueOf(nums[0]);
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(arr, (a, b) -> {
            return (b + a).compareTo(a + b);
        });

        if (arr[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]);
        }

        return sb.toString();
    }

    /**
     * 数字反转
     * 思路：注意反转过程中会溢出，因此需要找出判断溢出的方式
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            // 判断是否存在溢出
            if ((ans * 10) / 10 != ans) {
                return 0;
            }

            // 取最后的个位数
            ans = ans * 10 + x % 10;
            // 将原数据除10
            x = x / 10;
        }
        return ans;

//        判断long转int后的值与原值是否相等来出来溢出的情况
//        long n = 0;
//        while(x != 0) {
//            n = n*10 + x%10;
//            x = x/10;
//        }
//        return (int)n==n? (int)n:0;
    }

    /**
     * 盛水最多的容器
     * 思路：双指针，left贺right指针，所指值小的一端移动，边界条件 left<right
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int area = 0;
        int n = height.length;
        int left = 0, right = n - 1;

        while (left < right) {
            area = height[left] <= height[right] ? Math.max(area, (right - left) * height[left++]) : Math.max(area, (right - left) * height[right--]);
        }

        return area;
    }

    /**
     * 身高差排序
     * 思路：身高差相同的存储到同一个key的value中
     *
     * @param source
     * @param target
     * @return
     */
    public static int[] absoluteHeightSorted(int source, int[] target) {
        if (target.length <= 1) {
            return target;
        }

        int len = target.length;
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            temp[i] = target[i] - source;
        }

        return null;
    }

    public static List<List<Integer>> basketball(int[] scores) {
        List<List<Integer>> result = new ArrayList<>();

        int len = scores.length;
        int left = len - 1, right = len - 2;
        Arrays.sort(scores);
        List<Integer> red = new ArrayList<>();
        List<Integer> blue = new ArrayList<>();
        int redSum = 0, blueSum = 0;
        while (left >= 0 && right >= 0) {
            redSum += scores[left];
            blueSum += scores[right];
            if (redSum < blueSum) {
                left--;
            } else {
                right--;
            }
        }

        return null;
    }

    /**
     * 买卖股票，根据给出的价格数组，计算最大收益
     *
     * @param prices
     * @return
     */
    public static int maxEarnings(int[] prices) {
        int earnings = 0;
        for (int i = 1; i < prices.length; i++) {
            earnings += prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0;
        }
        return earnings;
    }

    /**
     * 斐波那契数列
     * 思路：不能用递归，会溢出
     * 直接循环，用变量
     *
     * @param n
     * @return
     */
    public int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }

        int a = 1, b = 1, c = 1;
        for (int i = 2; i <= n; i++) {
            b = c;
            a = c - a;
            c = a + b;
        }
        return c;
    }

    /**
     * 反转链表
     * 思路：双指针，遍历链表时通过改变next指针所指节点
     *
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode tmp = cur.next; // 暂存后继节点 cur.next
            cur.next = pre;          // 修改 next 引用指向
            pre = cur;               // pre 暂存 cur
            cur = tmp;               // cur 访问下一节点
        }
        return pre;
    }

    /**
     * 反转链表
     * 思路：
     * 终止条件：当 cur 为空，则返回尾节点 pre （即反转链表的头节点）；
     * 递归后继节点，记录返回值（即反转链表的头节点）为 res ；
     * 修改当前节点 cur 引用指向前驱节点 pre ；
     * 返回反转链表的头节点 res ；
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        return recur(head, null);    // 调用递归并返回
    }

    private static ListNode recur(ListNode cur, ListNode pre) {
        if (cur == null) return pre; // 终止条件
        ListNode res = recur(cur.next, cur);  // 递归后继节点
        cur.next = pre;              // 修改节点引用指向
        return res;                  // 返回反转链表的头节点
    }

    /**
     * 复制链表
     * 思路：哈希表，目的是为了能够构建节点的random指针
     *
     * @param head
     * @return
     */
    public ListNode copyRandomList1(ListNode head) {
        if (head == null) return null;
        ListNode cur = head;
        Map<ListNode, ListNode> map = new HashMap<>();
        // 3. 复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
        while (cur != null) {
            map.put(cur, new ListNode(cur.val));
            cur = cur.next;
        }
        cur = head;
        // 4. 构建新链表的 next 和 random 指向，null 对应的是 null
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        // 5. 返回新链表的头节点
        return map.get(head);
    }

    /**
     * 复制链表
     * 思路：
     * 1.复制各节点，构建拼接链表:
     * 设原链表为 node1 -> node2 →⋯ ，构建的拼接链表如下所示：
     * node1 -> node1_{new} -> node2 -> node2_{new} -> ...
     * <p>
     * 2.构建新链表各节点的 random 指向：
     * 当访问原节点 cur 的随机指向节点 cur.random 时，对应新节点 cur.next 的随机指向节点为 cur.random.next 。
     * <p>
     * 3.拆分原 / 新链表：
     * 设置 pre / cur 分别指向原 / 新链表头节点，遍历执行 pre.next = pre.next.next 和 cur.next = cur.next.next 将两链表拆分开。
     * 返回新链表的头节点 res 即可。
     *
     * @param head
     * @return
     */
    public ListNode copyRandomList2(ListNode head) {
        if (head == null) return null;
        ListNode cur = head;
        // 1. 复制各节点，并构建拼接链表
        while (cur != null) {
            ListNode tmp = new ListNode(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }
        // 2. 构建各新节点的 random 指向
        cur = head;
        while (cur != null) {
            if (cur.random != null)
                //cue节点的random不为空时设置 cur.next.random = cur.random.next
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 3. 拆分两链表
        cur = head.next;
        ListNode pre = head, res = head.next;
        while (cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null; // 单独处理原链表尾节点
        return res;      // 返回新链表头节点
    }

    /**
     * 滑动窗口最大值
     * 思路：双向队列deque
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int j = 0, i = 1 - k; j < nums.length; i++, j++) {
            // 删除 deque 中对应的 nums[i-1]，原因窗口向后移动时需要删除最前面的元素
            if (i > 0 && deque.peekFirst() == nums[i - 1])
                deque.removeFirst();
            // 保持 deque 递减，其实就是保持队列中的元素最大
            while (!deque.isEmpty() && deque.peekLast() < nums[j])
                deque.removeLast();
            deque.addLast(nums[j]);
            // 记录窗口最大值，i>=0时已形成窗口
            if (i >= 0)
                res[i] = deque.peekFirst();
        }
        return res;
    }

    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        // 未形成窗口
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
        }
        // 形成第一个窗口后得到第一个最大值
        res[0] = deque.peekFirst();
        // 形成窗口后
        for (int i = k; i < nums.length; i++) {
            if (deque.peekFirst() == nums[i - k])
                deque.removeFirst();
            while (!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }
}

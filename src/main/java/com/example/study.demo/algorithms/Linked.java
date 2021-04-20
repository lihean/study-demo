package com.example.study.demo.algorithms;

public class Linked {

    /**
     * 删除有序链表中的重复节点
     * [1,2,3,3,4,4,5] --> [1,2,5]
     * 要求：空间复杂度为O(1)
     * 思路：双指针法，在链表头部添加一个空节点p，left和right指针分别执行p.next
     * 判断right.next与left的关系，若值相等则重新给right赋值（right=right.next）【该步骤为循环】
     * 判断left和right是否相等，若是则代表未遇到重复的节点（执行p = p.next），否则删除right节点（执行p.next = right.next）
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode p = new ListNode(0);
        p.next = head;
        head = p;
        ListNode left, right;
        while (p.next != null) {
            left = p.next;
            right = left;
            while (right.next != null && right.next.val == left.val) {
                right = right.next;
            }

            if (right == left) {
                p = p.next;
            } else {
                p.next = right.next;//该步骤删除了留存的重复节点
            }
        }


        return head.next;
    }

}

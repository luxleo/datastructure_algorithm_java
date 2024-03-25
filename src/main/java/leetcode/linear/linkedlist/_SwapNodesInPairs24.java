package leetcode.linear.linkedlist;

import leetcode.linear.util.ListNode;

public class _SwapNodesInPairs24 {
    public ListNode swapPairs(ListNode head) {
        return swapNodeRecur(head);
    }

    public ListNode swapOnlyValue(ListNode head) {
        ListNode node = head;
        while (node != null && node.next != null) {
            int tmp = node.val;
            node.val = node.next.val;
            node.next.val = tmp;
            node = node.next.next;
        }
        return head;
    }

    public ListNode swapNodeInLoop(ListNode head) {
        ListNode node = new ListNode(0);
        ListNode root = node;

        node.next = head;
        while (node.next != null && node.next.next != null) {
            ListNode a = node.next;
            ListNode b = node.next.next;

            a.next = b.next;
            node.next = b;
            node.next.next = a;
            node = node.next.next;
        }
        return root.next;
    }

    public ListNode swapNodeRecur(ListNode head) {
        if (head != null && head.next != null) {
            ListNode p = head.next;
            head.next = swapNodeRecur(head.next.next);
            p.next = head;
            return p;
        }
        return head;
    }
}

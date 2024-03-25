package leetcode.linear.linkedlist;

import leetcode.linear.util.ListNode;

public class ReverseLinkedList206 {
    ListNode answer;
    public ListNode reverseList(ListNode head) {
        reverse(head, null);
        return answer;
    }

    public void reverse(ListNode node, ListNode prev) {
        if (node == null) {
            answer = prev;
            return;
        }
        ListNode next = node.next;
        node.next = prev;
        reverse(next, node);
    }
    public ListNode reverseListLoop(ListNode head) {
        ListNode node = head, prev = null;

        while (node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }
}

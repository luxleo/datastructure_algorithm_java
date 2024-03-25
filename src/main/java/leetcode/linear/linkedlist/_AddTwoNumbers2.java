package leetcode.linear.linkedlist;

import leetcode.linear.util.ListNode;

public class _AddTwoNumbers2 {
    /**
     * 전가산기를 이용하여 계산한다.
     * @param l1 : 역순 연결리스트로 된 더할 수 1
     * @param l2 : 역순 연결리스트로 된 더할 수 1
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // node 갱신을 위한 임시노드
        ListNode node = new ListNode(0);
        ListNode root = node;
        int sum, carry = 0, remainder;

        while (l1 != null || l2 != null || carry != 0) {
            sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            remainder = (sum + carry) % 10;
            carry = (sum + carry) / 10;
            node.next = new ListNode(remainder);
            node = node.next;
        }
        return root.next;
    }
}

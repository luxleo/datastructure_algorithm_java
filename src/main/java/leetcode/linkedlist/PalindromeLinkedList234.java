package leetcode.linkedlist;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class PalindromeLinkedList234 {
    /**
     * 러너를 이용한 풀이이다.
     * 러너기법은 연결리스트에서 자주 사용되는 기법이다.
     * 1. 연결리스트의 길이를 구할때
     * 2. 연결리스트의 병합지점을 구할때
     * 3. 연결리스트의 중앙값을 구할때 자주 이용된다.
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        /* 전체 리스트의 크기가 홀수인 경우이다.
            이때 중간값은 palindrome에 영향을 미치지 않으므로
            slow를 한칸 전진 시켜준다.
         */
        if (fast != null) {
            slow = slow.next;
        }
        ListNode rev = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = rev;
            rev = slow;
            slow = next;
        }
        while (rev != null) {
            if(rev.val != head.val)
                return false;
            rev = rev.next;
            head = head.next;
        }
        return true;
    }
    /**
     * stack을 이용하여 풀이한다.
     * 가장 효율이 좋지 못하다
     * @param head
     * @return
     */
    public boolean isPalindromeV1(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode node = head;
        // stack에 링크드리스트의 값을 집어 넣는다.
        while (node != null) {
            stack.add(node.val);
            node = node.next;
        }
        while (head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * deque를 활용하여 풀이한다.
     * @param head
     * @return
     */
    public boolean isPalindromeV2(ListNode head) {
        Deque<Integer> deque = new LinkedList<>();
        ListNode node = head;

        while (node != null) {
            deque.add(node.val);
            node = node.next;
        }
        while (!deque.isEmpty() && deque.size() > 1) {
            if (deque.pollFirst() != deque.pollLast()) {
                return false;
            }
        }
        return true;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

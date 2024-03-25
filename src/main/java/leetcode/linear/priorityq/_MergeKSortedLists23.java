package leetcode.linear.priorityq;

import leetcode.linear.util.ListNode;

import java.util.PriorityQueue;

public class _MergeKSortedLists23 {
    public ListNode mergeKLists(ListNode[] lists) {
        // 중요한점: pq의 정렬 순서를 익명 클래스로 정의 하는 거 보다 람다식으로 구현시 더 빠르다.
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.val - o2.val;
        });
        for (ListNode node :
                lists) {
            if(node != null)
                pq.add(node);
        }
        ListNode root = new ListNode(0);
        ListNode tail = root;

        while (!pq.isEmpty()) {
            tail.next = pq.poll();
            tail = tail.next;

            if (tail.next != null) {
                pq.add(tail.next);
            }
        }
        return root.next;
    }
}

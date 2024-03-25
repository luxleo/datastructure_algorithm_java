package leetcode.linear.linkedlist;

import leetcode.linear.util.ListNode;

public class MergeTwoSortedList21 {
    ListNode leftNode, rightNode,answer;

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        else if (list2== null) return list1;

        if (list1.val >= list2.val) {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        } else {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
    }
    public ListNode mergeTwoListsLoop(ListNode list1, ListNode list2) {
        answer = new ListNode();
        ListNode answerHead = answer;
        leftNode = list1;
        rightNode = list2;

        while (leftNode != null && rightNode != null) {
            appendNode(leftNode,rightNode);
        }
        if (leftNode == null)
            answer.next = rightNode;
        else
            answer.next = leftNode;
        return answerHead.next;
    }

    public void appendNode(ListNode lNode, ListNode rNode) {
        if (lNode.val >= rNode.val) {
            answer.next = new ListNode(rNode.val);
            rightNode = rightNode.next;
        } else {
            answer.next = new ListNode(lNode.val);
            leftNode = leftNode.next;
        }
        answer = answer.next;
    }
}


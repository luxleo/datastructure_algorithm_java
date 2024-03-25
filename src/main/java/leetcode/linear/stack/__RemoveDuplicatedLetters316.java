package leetcode.linear.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 해결 못함 => 다시 볼것 
 */
public class __RemoveDuplicatedLetters316 {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 3; i++) {
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pollLast());
        }
    }
}

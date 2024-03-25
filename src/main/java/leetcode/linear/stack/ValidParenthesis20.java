package leetcode.linear.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * new Stack<>()을 이용하면 멀티 코어 CPU에 최적화된
 * 기능을 수행하지 못하므로
 * Deque = new ArrayDeque 이용하기
 */
public class ValidParenthesis20 {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        // 분기문으로 처리하면 성능은 올라가나 사람이 해석하기에는 이게 적합해 보인다.
        Map<Character, Character> table = new HashMap<>();

        table.put(')', '(');
        table.put('}', '{');
        table.put(']', '[');

        for (char c : s.toCharArray()) {
            // 괄호의 끝으로 비교해야할 대상이면 유효한지 검증한다.
            if (table.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != table.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        if(stack.isEmpty())
            return true;
        return false;
    }
}

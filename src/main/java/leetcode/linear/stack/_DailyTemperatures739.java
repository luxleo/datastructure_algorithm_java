package leetcode.linear.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class _DailyTemperatures739 {
    /**
     * 인덱스를 스택에 저장하며 현재 인덱스의 온도 보다 낮다면 갱신해준다.
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < temperatures.length; i++) {
            if (stack.peek() != null && temperatures[stack.peek()] < temperatures[i]) {
                int last;
                do {
                    last = stack.pop();
                    result[last] = i - last;
                } while (stack.peek() != null && temperatures[stack.peek()] < temperatures[i]);
            }
            stack.push(i);
        }
        return result;
    }
}

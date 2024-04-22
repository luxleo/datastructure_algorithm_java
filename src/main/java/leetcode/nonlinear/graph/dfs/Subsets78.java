package leetcode.nonlinear.graph.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Subsets78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        dfs(answer, nums, 0, nums.length, new ArrayDeque<>());
        return answer;
    }

    public void dfs(List<List<Integer>> answer,int[] nums, int index, int limit, Deque<Integer> acc) {
        if(index == limit) {
            answer.add(new ArrayList<>(acc));
            return;
        }
        acc.add(nums[index]);
        dfs(answer, nums, index + 1, limit, acc);
        acc.removeLast();
        dfs(answer, nums, index + 1, limit, acc);
    }
}

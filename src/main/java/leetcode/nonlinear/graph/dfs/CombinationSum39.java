package leetcode.nonlinear.graph.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CombinationSum39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, candidates, target, 0, new ArrayDeque<>());
        return result;
    }

    public void dfs(List<List<Integer>> result, int[] candidates, int target, int index, Deque<Integer> path) {
        if(target <0) return;
        if(target ==0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(result, candidates, target - candidates[i], i, path);
            path.removeLast();
        }
    }
}

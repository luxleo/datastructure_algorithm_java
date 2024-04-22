package leetcode.nonlinear.graph.dfs;

import java.util.ArrayList;
import java.util.List;

public class Combinations77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        answer = new ArrayList<>();
        List<Integer> acc = new ArrayList<>();
        dfs(0, k,n,acc,0,answer);
        return answer;
    }

    private void dfs(int level,int limit, int size , List<Integer> acc, int idx,List<List<Integer>> answer) {
        if (level == limit) {
            answer.add(acc.stream().toList());
            return;
        }
        for (int i = idx; i < size; i++) {
            acc.add(i + 1);
            dfs(level+1, limit, size,acc, i+1,answer);
            acc.remove(level);
        }
    }
}

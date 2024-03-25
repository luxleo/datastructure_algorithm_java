package leetcode.nonlinear.graph.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _Permutations46 {
    List<List<Integer>> answer = new ArrayList<>();
    boolean[] vst = new boolean[21];
    int[] data;
    public List<List<Integer>> permute(int[] nums) {
        data = nums;
        dfs(new int[nums.length], 0, nums.length);
        return answer;
    }

    public void dfs(int[] buffer, int level, int limit) {
        if (level == limit) {
            answer.add(Arrays.stream(buffer)
                    .boxed()
                    .collect(Collectors.toList()));
            return;
        }
        for (int num : data) {
            int index = num + 10;
            if (!vst[index]) {
                buffer[level] = num;
                vst[index] = true;
                dfs(buffer, level + 1, limit);
                vst[index] = false;
            }
        }
    }
}

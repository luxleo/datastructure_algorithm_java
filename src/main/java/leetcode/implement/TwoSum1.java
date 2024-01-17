package leetcode.implement;

import java.util.HashMap;
import java.util.Map;

public class TwoSum1 {
    public int[] twoSum(int[] nums, int target) {
        return improvedUseKeySolution(nums, target);
    }

    public int[] bruteSolution(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length-1; i++) {
            for (int j = i+1; j < length; j++) {
                if (nums[i] + nums[j] == target)
                    return new int[]{i,j};
            }
        }
        return null;
    }

    public int[] useKeySolution(int[] nums, int target) {
        Map<Integer, Integer> dict = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            dict.put(nums[i], i);
        }
        int[] answer = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int targetKey = target - nums[i];
            if (dict.containsKey(targetKey) && dict.get(targetKey) != i) {
                answer[0] = i;
                answer[1] = dict.get(targetKey);
                return answer;
            }
        }

        return answer;
    }

    public int[] improvedUseKeySolution(int[] nums, int target) {
        Map<Integer, Integer> dict = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int targetKey = target - nums[i];
            if(dict.containsKey(targetKey))
                return new int[]{i, dict.get(targetKey)};
            dict.put(nums[i], i);
        }
        return null;
    }
}

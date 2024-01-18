package leetcode.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum15 {
    /**
     * brute force 이용하여 풀이한다.
     * 시간 제한에 걸리게 된다.
     * 시간 복잡도: O(n^^3)
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumBruteForce(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
        int size = nums.length;
        for (int i = 0; i < size-2; i++) {
            if(i>0 && nums[i] == nums[i-1])
                continue;
            if(nums[i] > 0)
                break;

            for (int j = i+1; j < size-1; j++) {
                if(j>i+1 && nums[j] == nums[j-1])
                    continue;
                if(nums[i] + nums[j] > 0)
                    break;

                for (int k = j+1; k < size; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    }
                    int currentSum = nums[i] + nums[j] + nums[k];
                    if(currentSum > 0)
                        break;
                    if(currentSum == 0)
                        answer.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }
            }
        }
        return answer;
    }

    /**
     * 투포인터를 이용하여 풀이한다.
     * 시간 복잡도: O(n^^2)
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        int size = nums.length;
        int left,right;
        Arrays.sort(nums);

        for (int i = 0; i < size - 2; i++) {
            if(i>0 && nums[i] == nums[i-1])
                continue;
            left = i+1;
            right = size-1;

            while (left < right) {
                int curSum = nums[i] + nums[left] + nums[right];
                if(curSum >0)
                    right-=1;
                else if (curSum < 0) {
                    left+=1;
                }else{
                    answer.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 중복 되는 값을 건너 뛴다.
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // 정답은 중복 없이 나오므로 left, right인덱스를 갱신한다.
                    left++;
                    right--;
                }
            }
        }
        return answer;
    }

}

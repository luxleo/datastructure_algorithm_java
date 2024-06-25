package leetcode.linear.array;

/**
 * kadane's algorithm으로 풀이 udemy 참조
 */
public class _MaximumSubArray53 {
    public int maxSubArray(int[] nums) {
        int maxValue = Integer.MIN_VALUE;
        int acc = 0;

        for (int i = 0; i < nums.length; i++) {
            acc += nums[i];
            maxValue = Math.max(maxValue, acc);
            if(acc < 0){
                acc = 0;
                continue;
            }
        }

        return maxValue;
    }

    public int teacherCode(int[] nums) {
        int maxNumber = nums[0];
        int acc = 0;

        for (int i = 0; i < nums.length; i++) {
            if (acc < 0) {
                acc = 0;
            }
            acc += nums[i];
            if( maxNumber < acc) maxNumber = acc;
        }
        return maxNumber;
    }
}

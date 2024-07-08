package leetcode.linear.array;

/**
 * @see <a href='https://leetcode.com/problems/maximum-product-subarray/'>문제 링크</a>
 * 현재 순회중인 원소로 다시 시작할지, 기존의 걸로 갱신해 나갈지 두 가지 논리로 전개한다.
 */
public class MaximumProductSubArray152 {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int result = nums[0];
        int maxProduct = nums[0];
        int minProduct = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int currentNum = nums[i];
            if (currentNum >= 0) {
                maxProduct = Math.max(currentNum, maxProduct * currentNum);
                minProduct = Math.min(currentNum, minProduct * currentNum);
            } else {
                int temp = maxProduct;
                maxProduct = Math.max(currentNum, minProduct * currentNum);
                minProduct = Math.min(currentNum, temp * currentNum);
            }
            result = Math.max(result, maxProduct);
        }
        return result;
    }
}

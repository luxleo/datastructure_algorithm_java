package leetcode.implement;

public class ProductExceptSelf238 {
    public int[] productExceptSelf(int[] nums) {
        int totalProducts = 1;
        int size = nums.length;
        int zeroCnt = 0;

        for (int i = 0; i < size; i++) {
            if (nums[i] == 0) {
                zeroCnt++;
                continue;
            }
            totalProducts*= nums[i];
        }

        int[] answer = new int[size];
        for (int i = 0; i < size; i++) {
            if(zeroCnt>1) return answer;

            if (zeroCnt == 1 && nums[i] != 0) {
                answer[i] = 0;
            } else if (zeroCnt == 1 && nums[i] == 0) {
                answer[i] = totalProducts;
            } else
                answer[i] = totalProducts / nums[i];
        }

        return answer;
    }
}

package leetcode.implement;

public class ProductExceptSelf238 {
    /**
     * 문제 조건에 어긋나는 풀이이다. -> without division operator
     * @param nums
     * @return
     */
    public int[] productExceptSelfDeprecated(int[] nums) {
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

    /**
     * 해당 원소를 제외한 왼쪽, 오른쪽의 곱들을 곱한다.
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int size = nums.length;
        int[] left = new int[size];
        int[] right = new int[size];
        int[] output = new int[size];

        left[0] = 1;
        right[size - 1] = 1;

        for (int i = 1; i < size; i++) {
            left[i] = left[i - 1] * nums[i-1];
        }
        for (int i = size-2; i >=0 ; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < size; i++) {
            output[i] = left[i] * right[i];
        }
        return output;
    }
}

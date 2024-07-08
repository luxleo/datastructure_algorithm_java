package leetcode.linear.array;

/**
 * 그냥 발상의 전환인데, 뒤집어서 이동시킨다.
 */
public class RotateArray189 {
    public void rotate(int[] nums, int k) {
        int size = nums.length;
        k %= size;
        reverse(nums, 0, size - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, size - 1);
    }

    public void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}

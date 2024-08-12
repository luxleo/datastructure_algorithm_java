package leetcode.binarySearch;

public class BinarySearch704 {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int mid;

        while (left < right) {
            mid = (left + right) >> 1;
            int cur = nums[mid];
            if (cur < target) {
                left = mid+1;
            } else if (cur > target) {
                right = mid-1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

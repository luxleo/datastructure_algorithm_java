package leetcode.twopointer;

public class TrappingRainWater42 {
    int volume, left, right, leftMax, rightMax;
    public int trap(int[] height) {
        left = 0;
        right = height.length-1;

        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax <= rightMax) {
                volume += leftMax - height[left++];
            }else {
                volume += rightMax - height[right--];
            }
        }
        return volume;
    }
}

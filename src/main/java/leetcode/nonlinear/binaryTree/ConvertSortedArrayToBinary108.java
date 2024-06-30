package leetcode.nonlinear.binaryTree;

import leetcode.nonlinear.tree.TreeNode;

/**
 * 균형이진 트리를 이용하여 탐색시간을 O(logN)으로 조회가능하다.
 * 루트 노드의 왼편은 모두 루트노드 보다 작고
 * 루트 노드의 오른편은 모두 루트노드 값보다 크다.
 */
public class ConvertSortedArrayToBinary108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sort(nums, 0, nums.length - 1);
    }

    public TreeNode sort(int[] nums, int lo, int hi) {
        if(lo > hi) return null;
        int mid = (lo + hi) >> 1;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sort(nums, lo, mid - 1);
        node.right = sort(nums, mid + 1, hi);

        return node;
    }
}

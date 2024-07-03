package leetcode.nonlinear.binaryTree;

import leetcode.nonlinear.tree.TreeNode;

/**
 * 굉장히 어려웠다. 두 노드간 최소값을 구하기 위해서 BST의 특성을 고려하는것이 가장 중요했다.
 * 루트 노드로 부터 가로거리가 가장 짧은 녀석이 후보가 될 수 있다.
 */
public class MinimumDistanceBetweenBSTNodes783 {
    private int minDiff = Integer.MAX_VALUE;

    //노드의 크기가 10^5보다 작으므로
    private int prev = Integer.MIN_VALUE + 100_000;
    public int minDiffInBST(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    private void inorder(TreeNode root) {
        if (isValid(root.left)) {
            minDiffInBST(root.left);
        }
        minDiff = Math.min(minDiff, root.val - prev);
        prev = root.val;

        if(isValid(root.right)) minDiffInBST(root.right);
    }

    private static boolean isValid(TreeNode root) {
        return root != null;
    }
}

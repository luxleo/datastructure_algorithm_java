package leetcode.nonlinear.binaryTree;

import leetcode.nonlinear.tree.TreeNode;

/**
 * 중위 순회 등 트리의 순회를 보자.
 */
public class BinarySearchTreeToGreaterSumTree1038 {
    int val = 0;
    public TreeNode bstToGst(TreeNode root) {
        if (root != null) {
            bstToGst(root.right);
            this.val += root.val;
            root.val = this.val;
            bstToGst(root.left);
        }
        return root;
    }
}

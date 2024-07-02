package leetcode.nonlinear.binaryTree;

import leetcode.nonlinear.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class RangeSumOfBST938 {
    private int result = 0;
    private int low, high;
    public int rangeSumBST(TreeNode root, int low, int high) {
        this.low = low;
        this.high = high;
//        dfs(root);
        iterDFS(root);
        return result;
    }

    public void dfs(TreeNode root) {
        if(root == null) return;
        if (root.val >= low) {
            dfs(root.left);
        }
        if (root.val <= high) {
            dfs(root.right);
        }
        if(root.val >=low && root.val <= high) result += root.val;
    }

    public void iterDFS(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if(node.val >= low){
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
            if(node.val <= high){
                if (node.right != null) {
                    stack.push(node.right);
                }
            }
            if(node.val <= high && node.val >= low) result += node.val;
        }
    }

    public void iterBFS(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if(node.val >= low){
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
            if(node.val <= high){
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if(node.val <= high && node.val >= low) result += node.val;

        }
    }
}

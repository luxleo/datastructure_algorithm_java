package leetcode.nonlinear.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 순회하며 내려가는 구조를 재귀 또는 반복문으로 구현
 * @see <a href='https://leetcode.com/problems/invert-binary-tree/'>문제 링크</a>
 */
public class _InvertBinaryTree226 {
    /**
     * 재귀적으로 left와 right를 바꾸며 호출
     * @param root
     * @return
     */
    public TreeNode invertTreeV1(TreeNode root) {
        if (root != null) {
            TreeNode temp = root.right;
            root.right = root.left;
            root.left = temp;

            invertTreeV1(root.left);
            invertTreeV1(root.right);
        }
        return root;
    }

    /**
     * 트리를 먼저 순회한후에 말단 노드에서부터 바꾸어준다.
     * @param root
     * @return
     */
    public TreeNode invertTreeV2(TreeNode root) {
        if (root != null) {
            invertTreeV2(root.left);
            invertTreeV2(root.right);

            TreeNode temp = root.right;
            root.right = root.left;
            root.left = temp;
        }
        return root;
    }

    /**
     * bfs를 이용하여 트리를 순회하며 교환해준다.
     * 가장 성능이 좋다.
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (curNode != null) {
                TreeNode temp = curNode.right;
                curNode.right = curNode.left;
                curNode.left = temp;

                queue.offer(curNode.left);
                queue.offer(curNode.right);
            }
        }
        return root;
    }

}

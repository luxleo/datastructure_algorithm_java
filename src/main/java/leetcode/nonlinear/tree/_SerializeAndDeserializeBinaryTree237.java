package leetcode.nonlinear.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 이진트리의 경우 루트 노드 부터 순회하면
 * 그 순번은 차례대로
 * 1 ->
 * 2 -> 3 ->
 * 4-> 5-> 6-> 7-> 등 왼쪽 노드는 그 루트 노드의 2배 오른쪽 노드는 2배 더하기1 의 규칙을 가진다.
 * 이를 이용하여 BFS를 이용하여 순회한다.
 */
public class _SerializeAndDeserializeBinaryTree237 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        queue.add(root);
        sb.append(String.valueOf("#," + root.val));

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                sb.append(String.valueOf("," + node.left.val));
                queue.add(node.left);
            } else {
                sb.append(",#");
            }

            if (node.right != null) {
                sb.append("," + node.right.val);
                queue.add(node.right);
            } else {
                sb.append(",#");
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("")) return null;
        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[1]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int idx = 2;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!nodes[idx].equals("#")) {
                node.left = new TreeNode(Integer.parseInt(nodes[idx]));
                queue.add(node.left);
            }
            idx++;

            if (!nodes[idx].equals("#")) {
                node.right = new TreeNode(Integer.parseInt(nodes[idx]));
                queue.add(node.right);
            }
            idx++;
        }

        return root;
    }
}

package leetcode.nonlinear.tree;

/**
 * 트리의 직경을 구하는 문제이다.
 */
public class _DiameterOfBinaryTree543 {
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return this.max;
    }

    public int dfs(TreeNode node) {
        if(node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);

        // 직경(트리의 임의의 두 노드사이의 최대 거리)는 루트노드를 통해서만 발생하지 않는다.
        // 따라서 매 노드마다 점검을 해주어야한다.
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
}

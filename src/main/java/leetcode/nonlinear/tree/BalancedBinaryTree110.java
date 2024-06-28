package leetcode.nonlinear.tree;

/** 24.06.27
 *  재귀 함수의 콜스택이 반환되는 성질을 이용하여 (divide and conquer느낌)
 *  조회를 종단 노드까지 진행하고 계층별로 조회의 가장 끝(종단 노드)로 부터
 *  응답을 쌓아간다.
 */
public class BalancedBinaryTree110 {
    public int dfs(TreeNode node) {
        if(node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);

        // 현재 노드의 서브 노드간 높이 차가 1 이상이면 계속해서 실패응답(-1)을 반환한다.
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }

        // 현재 가장 깊은 뎁스 + 1 (현재 서브 노드 높이 갱신)
        return Math.max(left, right) + 1;
    }
    public boolean isBalanced(TreeNode root) {
        if(dfs(root) == -1) return false;

        return true;
    }
}

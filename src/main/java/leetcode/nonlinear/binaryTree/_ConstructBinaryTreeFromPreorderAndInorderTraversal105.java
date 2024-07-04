package leetcode.nonlinear.binaryTree;

import leetcode.nonlinear.tree.TreeNode;

/**
 * 전위, 중위, 후위 순회중 2개만 있으면 트리를 복구할 수 있다.
 */
public class _ConstructBinaryTreeFromPreorderAndInorderTraversal105 {
    private int[] preorder, inorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

        return dfs(0, 0, preorder.length-1);
    }

    private TreeNode dfs(int preIdx, int start, int end) {
        if(preIdx > preorder.length-1 || start > end) return null;
        int inIdx = 0; // inIdx : 중위 순회 배열의 인덱스이다.

        // 전위 순회가 중위 순회의 어느 부분에 오는지 본다.
        for (int i = start; i <= end; i++) {
            if (inorder[i] == preorder[preIdx]) {
                inIdx = i;
                break;
            }
        }

        TreeNode newNode = new TreeNode(preorder[preIdx]);

        newNode.left = dfs(preIdx+1, start, inIdx - 1);

        // preIdx 갱신이 preIdx + (inIdx - start +1) 인 이유: 중위 순회의 지표를
        // 전위 순회에서 구하면 먼저 node.left를 순회해야 하므로 (node.left)의 서브 트리 노드 개수를 더함.
        newNode.right = dfs(preIdx + (inIdx - start + 1), inIdx + 1, end);

        return newNode;
    }
}

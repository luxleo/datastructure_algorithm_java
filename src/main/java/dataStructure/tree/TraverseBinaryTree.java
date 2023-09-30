package dataStructure.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.전위 순회: 현재 - 왼쪽 서브트리 -오른쪽 서브트리
 * 1.중위 순회: 왼쪽 서브트리- 현재  -오른쪽 서브트리
 * 1.후위 순회: 왼쪽 서브트리 -오른쪽 서브트리 - 현재
 */
public class TraverseBinaryTree {
    private static List<BinaryTree> nodes = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        initTraversalService(); // 이진 트리를 세팅 해준다.

        System.out.println("=========전위 순회 시작=========");
        preorder(nodes.get(0));
        System.out.println(sb.toString());

        System.out.println("=========중위 순회 시작=========");
        sb = new StringBuilder();
        inorder(nodes.get(0));
        System.out.println(sb.toString());

        System.out.println("=========후위 순회 시작=========");
        sb = new StringBuilder();
        postorder(nodes.get(0));
        System.out.println(sb.toString());
    }

    /**
     * 이진 트리 전위 순회(preorder traversal)는 현재노드 -> 왼쪽 서브트리 -> 오른쪽 서브 트리로 순회한다.
     */
    public static void preorder(BinaryTree cur) {
        if (cur == null) {
            return;
        }
        //현재 노드 방문 처리
        sb.append(String.format("%d-",cur.getData()));

        // 왼쪽 서브 트리로 이동
        preorder(cur.getLeft());

        // 오른쪽 서브 트리로 이동
        preorder(cur.getRight());
    }

    public static void inorder(BinaryTree cur) {
        if(cur == null) return;

        inorder(cur.getLeft());
        sb.append(String.format("%d-",cur.getData()));
        inorder(cur.getRight());
    }

    public static void postorder(BinaryTree cur) {
        if(cur == null) return;

        postorder(cur.getLeft());
        postorder(cur.getRight());
        sb.append(String.format("%d-",cur.getData()));
    }

    /**
     * 이진 트리를 노드 갯수 입력 받고 만들어준다,
     */
    public static void initTraversalService() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("노드의 갯수를 입력하세요 : ");
        int nNode = Integer.parseInt(br.readLine()); // tree노드의 총 갯수
        if(nNode <=2) {
            System.out.println("노드 갯수는 2보다 커야한다.");
            return;
        }

        for (int i = 0; i < nNode; i++) {
            nodes.add(new BinaryTree(i + 1));
        }

        int parentNodeIdx = 0;// 자식 노드를 할당받는 부모노드
        int childNodeIdx = 1; // 부모노드에 할당되는 자식노드
        int leftOrRight = 0;  // 왼쪽 오른쪽 결정자

        // 이진 트리 세팅
        while (childNodeIdx < nNode) {
            BinaryTree curParent = nodes.get(parentNodeIdx);
            BinaryTree curChild = nodes.get(childNodeIdx);
            if (leftOrRight == 0) {
                curParent.setLeft(curChild);
                childNodeIdx++;
                leftOrRight++;
            } else {
                curParent.setRight(curChild);
                childNodeIdx++;
                parentNodeIdx++; // 우측 자식 노드 설정후 부모노드 인덱스 증가 시켜주기
                leftOrRight = 0;
            }
        }
    }
}

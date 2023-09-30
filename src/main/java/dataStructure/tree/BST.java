package dataStructure.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * Binary Search Tree는 입력, 조회, 삭제를 O(logn)에 가능하게 하지만
 * 한쪽 방향으로만 자식노드를 가지는 편향 그래프를 가지면 모두 O(n)이 되게된다. -> 결국 연결리스트와 같은 꼴이 된다.
 * parent node를 기준으로 모든 left sub tree는 작고, right sub tree는 큰 값을 가진다.
 */
@Getter @Setter
public class BST {
    private BSTNode root;

    public void preorder_traverse(Worker worker, BSTNode cur) {
        if(cur == null) return;

        worker.work(cur);
        preorder_traverse(worker, cur.getLeft());
        preorder_traverse(worker, cur.getRight());
    }
    public void inorder_traverse(Worker worker, BSTNode cur) {
        if(cur == null) return;

        inorder_traverse(worker, cur.getLeft());
        worker.work(cur);
        inorder_traverse(worker, cur.getRight());
    }
    public void insert(Integer key) {
        BSTNode newNode = new BSTNode(key);

        BSTNode cur = this.getRoot();
        if (cur == null) {
            this.setRoot(newNode);
            cur = newNode;
            return;
        }

        BSTNode parent;
        while (true) {
            parent = cur;
            if (key < cur.getKey()) {
                cur = cur.getLeft();
                // 종단 노드일때 새로운 노드 할당한다.
                if (cur == null) {
                    makeLeft(parent,newNode);
                    return;
                }
            }else{
                cur = cur.getRight();
                if (cur == null) {
                    makeRight(parent, newNode);
                    return;
                }
            }
        }
    }

    public String search(Integer target) {
        BSTNode cur = this.getRoot();
        while (cur != null) {
            if (cur.getKey() == target) {
                return cur.toString();
            } else if (cur.getKey() < target) {
                cur = cur.getRight();
            } else {
                cur = cur.getLeft();
            }
        }

        //탐색 결과가 없는 경우
        return String.format("%d is not exist in BST", target);
    }

    /**
     * 핵심은 찾고자 하는 노드의 부모노드와의 연결을 끊는다.즉, 부모 노드의 참조노드를(parent.left, parent.right)
     * 타겟 노드에서 타겟 노드의 자식이나, 자식이 없다면 null로 설정한다.
     * 이떄 가장 까다로운 사항은 삭제하고자 하는 노드의 left,right자식 노드가 모두 있는 경우인데
     * left 부분 트리의 max노드, right부분 트리의 min노드로 대체해준다.
     */
    public BSTNode delete_recursively(BSTNode cur, Integer target) {
        if(cur == null) return null;
        else if (cur.getKey() > target) {
            BSTNode newLeft = this.delete_recursively(cur.getLeft(), target);
            this.makeLeft(cur, newLeft);
        } else if (cur.getKey() < target) {
            BSTNode newRight = this.delete_recursively(cur.getRight(), target);
            this.makeRight(cur, newRight);
        }else{
            // target 과 cur이 같은 경우
            if (cur.getLeft() == null && cur.getRight() == null) {
                cur = null; // 리프노드가 없으면 참조를 없애준다.
            } else if (cur.getLeft() == null) {
                cur = cur.getRight(); // 오른쪽 노드만 있는 경우로 현재 노드 참조를 없애고 오른쪽 노드를 할당
            } else if (cur.getRight() == null) {
                cur = cur.getLeft();
            }else{ // 오른쪽 왼쪽의 자식을 모두 가지고 있는 경우 (왼쪽 서브 트리중 가장 큰 녀석을 할당)
                BSTNode replace = cur.getLeft();
                replace = this.maxFromTree(replace); // 왼쪽 서브 트리중 가장 큰 값
                Integer temp = cur.getKey();

                // 삭제하려는 노드(cur)과 왼쪽 서브트리중 가장 큰 값의 키 교환
                cur.setKey(replace.getKey()); replace.setKey(temp);
                //만일 데이터가 있다면 복사하여 옮긴다.
                cur.setData(replace.getData());
                BSTNode newLeft = this.delete_recursively(cur.getLeft(), replace.getKey());
                cur.setLeft(newLeft);
            }
        }
        return cur;
    }

    /**
     * root로 부터 타겟을 검색해 들어간다.
     */
    public void delete(Integer target) {
        BSTNode newRoot = this.delete_recursively(this.getRoot(), target);
        this.setRoot(newRoot);
    }
    private BSTNode maxFromTree(BSTNode cur) {
        while (cur.getRight() != null) {
            cur = cur.getRight();
        }
        return cur;
    }
    private void makeLeft(BSTNode cur, BSTNode left) {
        cur.setLeft(left);
        if (left != null) {
            left.setParent(cur);
        }
    }
    private void makeRight(BSTNode cur, BSTNode right) {
        cur.setRight(right);
        if (right != null) {
            right.setParent(cur);
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();

        bst.insert(6);
        bst.insert(3);
        bst.insert(2);
        bst.insert(4);
        bst.insert(5);
        bst.insert(8);
        bst.insert(10);
        bst.insert(9);
        bst.insert(11);

        bst.inorder_traverse((node)-> System.out.printf("%d-",node.getKey()),bst.getRoot());
        System.out.println();

        bst.delete(6);
        bst.preorder_traverse((node)-> System.out.printf("%d-",node.getKey()),bst.getRoot());
    }
}

/**
 * 함수 인자로 넘기기 위한 함수 정의(이진 탐색 트리 순회하며 진행할 작업을 명시한다.
 */
interface Worker{
    public void work(BSTNode cur);
}

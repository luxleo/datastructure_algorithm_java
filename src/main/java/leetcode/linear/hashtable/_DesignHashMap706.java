package leetcode.linear.hashtable;

/**
 * 개별 체이닝 방식으로 해시 구현
 */
public class _DesignHashMap706 {
    final Node[] nodes = new Node[100_000];

    public void put(int key, int val) {
        int hashedKey = getHashedKey(key);
        Node node = nodes[hashedKey];
        if (node == null) {
            nodes[hashedKey] = new Node(key, val);
            return;
        }
        while (node != null) {
            if (node.key == key) {
                node.val = val;
                return;
            }
            if(node.next == null)
                break;
            node = node.next;
        }
        node.next = new Node(key, val);
    }

    public int get(int key) {
        int hashedKey = getHashedKey(key);
        Node node = nodes[hashedKey];
        if (node == null) {
            return -1;
        }
        while (node != null) {
            if (node.key == key) {
                return node.val;
            }
            if (node.next == null) {
                return -1;
            }
            node = node.next;
        }
        return -1;
    }

    public void remove(int key) {
        int hashedKey = getHashedKey(key);
        Node node = nodes[hashedKey];
        if(node == null)
            return;
        if (node.key == key) {
            if(node.next == null)
                nodes[hashedKey] = null;
            else
                nodes[hashedKey] = node.next;
            return;
        }
        Node prev = node;
        while (node != null) {
            if (node.key == key) {
                prev.next = node.next;
                return;
            }
            prev = node;
            node = node.next;
        }

    }

    private int getHashedKey(int key) {
        return key % nodes.length;
    }
    static class Node{
        int key, val;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

}

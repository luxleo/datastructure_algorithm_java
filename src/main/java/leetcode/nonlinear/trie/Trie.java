package leetcode.nonlinear.trie;

/**
 * 208번 implement trie
 */
public class Trie {
    private final TrieNode root = new TrieNode();
    public Trie() {

    }

    public void insert(String word) {
        TrieNode cur = root;
        for (final char c : word.toCharArray()) {
            final int idx = c - 'a';
            if (cur.getMap()[idx] == null) {
                cur.getMap()[idx] = new TrieNode();
            }
            cur = cur.getMap()[idx];
        }
        cur.makeWordEnd();
    }

    public boolean search(String word) {
        TrieNode cur = root;
        for (final char c :
                word.toCharArray()) {
            final int idx = c - 'a';
            if(cur.getMap()[idx] == null) return false;
            cur = cur.getMap()[idx];
        }
        return cur.getIsEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (final char c :
                prefix.toCharArray()) {
            final int idx = c - 'a';
            if(cur.getMap()[idx] == null) return false;
            cur = cur.getMap()[idx];
        }
        return true;
    }
}

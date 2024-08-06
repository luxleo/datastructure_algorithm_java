package leetcode.nonlinear.trie;

public class TrieNode {
    private boolean isEnd;
    private TrieNode[] map;

    public TrieNode() {
        this.isEnd = false;
        this.map = new TrieNode[26];
    }
    public TrieNode[] getMap() {
        return this.map;
    }
    public boolean getIsEnd() {
        return this.isEnd;
    }

    public void makeWordEnd() {
        this.isEnd = true;
    }
}

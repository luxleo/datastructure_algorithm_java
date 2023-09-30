package dataStructure.tree;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(of = {"key"})
public class BSTNode {
    private Integer key;
    private BSTNode left;
    private BSTNode right;
    private BSTNode parent;
    private String data;

    public BSTNode(Integer key) {
        this.key = key;
    }
}

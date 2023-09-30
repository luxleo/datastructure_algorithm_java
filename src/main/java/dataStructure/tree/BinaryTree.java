package dataStructure.tree;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BinaryTree {
    private Integer data;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(Integer data) {
        this.data = data;
    }
}

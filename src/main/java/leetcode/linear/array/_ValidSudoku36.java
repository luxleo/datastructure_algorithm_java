package leetcode.linear.array;

import java.util.HashSet;
import java.util.Set;

public class _ValidSudoku36 {
    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] rowVst = new HashSet[9];
        Set<Character>[] colVst = new HashSet[9];
        Set<Character>[] gridVst = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rowVst[i] = new HashSet<>();
            colVst[i] = new HashSet<>();
            gridVst[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char currentCharacter = board[i][j];
                if(currentCharacter != '.'){
                    // gridIdx 를 구하는 부분이 하이라이트임.
                    int gridIdx = (i / 3) * 3 + (j / 3);
                    boolean isRowInvalid = rowVst[i].contains(currentCharacter);
                    boolean isColInvalid = colVst[j].contains(currentCharacter);
                    boolean isGridInvalid = gridVst[gridIdx].contains(currentCharacter);

                    if(isRowInvalid || isColInvalid || isGridInvalid) return false;
                    rowVst[i].add(currentCharacter);
                    colVst[j].add(currentCharacter);
                    gridVst[gridIdx].add(currentCharacter);
                }
            }
        }
        return true;
    }
}

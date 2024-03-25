package boj.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _BOJ2508SudoKu {
    static int[][] map = new int[9][9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //맵 초기화
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0,0);
    }

    static void dfs(int row, int col) {
        if (col == 9) {
            dfs(row + 1, 0);
            return;
        }
        if (row == 9) {
            drawMap();
            return;
        }

        /**
         * 아무 값도 할당 되지 않은값인 경우 1-9까지 가능한 수를 넣어본다.
         */
        if (map[row][col] == 0) {
            for (int i = 1; i < 10; i++) {
                if (isFillAble(row, col, i)) {
                    map[row][col] = i;
                    dfs(row, col + 1);
                }
            }
            map[row][col] = 0;
        } else {
            dfs(row, col + 1);
        }
    }

    private static boolean isFillAble(int row, int col, int val) {
        // check colum
        for (int i = 0; i < 9; i++) {
            if(map[row][i] == val)
                return false;
        }

        //check row
        for (int i = 0; i < 9; i++) {
            if(map[i][col] == val)
                return false;
        }

        // check 3*3 frame
        int standardRow = (row / 3) * 3;
        int standardCol = (col / 3) * 3;

        for (int i = standardRow; i < standardRow+3; i++) {
            for (int j = standardCol; j < standardCol + 3; j++) {
                if(map[i][j] == val)
                    return false;
            }
        }
        return true;
    }

    static void drawMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(String.format("%d ", map[i][j]));
            }
            if(i != 8)
                sb.append("\n");
        }
        System.out.println(sb.toString());
        System.exit(0);
    }
}

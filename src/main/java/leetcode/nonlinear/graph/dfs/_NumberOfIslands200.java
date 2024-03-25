package leetcode.nonlinear.graph.dfs;

import java.util.LinkedList;
import java.util.Queue;

public class _NumberOfIslands200 {
    int row, col;
    int cnt;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    char[][] map;
    public int numIslands(char[][] grid) {
        row = grid.length;
        col = grid[0].length;
        map = grid;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (isValidPoint(i, j)) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.println(map[i][j]);
            }
        }
        return cnt;
    }

    public void dfs(int r, int c) {
        map[r][c] +=1;
        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];
            if (isValidPoint(nr, nc)) {
                dfs(nr, nc);
            }
        }
    }

    public void bfs(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(new int[]{r, c}));
        map[r][c] += 1;

        while (!q.isEmpty()) {
            Point curPoint = q.poll();
            int[] point = curPoint.point;
            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];
                if (isValidPoint(nr, nc)) {
                    q.offer(new Point(new int[]{nr, nc}));
                    map[nr][nc] += 1;
                }
            }
        }
    }

    public boolean isValidPoint(int x, int y) {
        boolean ans = false;
        if (-1 < x && x < row && -1 < y && y < col) {
            if(map[x][y] == '1')
                ans = true;
        }
        return ans;
    }

    static class Point {
        int[] point;

        public Point(int[] point) {
            this.point = point;
        }
    }
}


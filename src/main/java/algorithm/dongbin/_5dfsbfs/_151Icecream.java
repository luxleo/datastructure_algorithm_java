package algorithm.dongbin._5dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _151Icecream {
    static String[] graph;
    static boolean[][] vst;
    static int rNum, cNum;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {-0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rNum = Integer.parseInt(st.nextToken());
        cNum = Integer.parseInt(st.nextToken());

        vst = new boolean[rNum][cNum];
        graph = new String[rNum];
        for (int i = 0; i < rNum; i++) {
            graph[i] = br.readLine().trim();
        }

        int result = 0;
        for (int i = 0; i < rNum; i++) {
            for (int j = 0; j < cNum; j++) {
                if (dfs(i, j)) {
                    result++;
                }
            }
        }
        System.out.println("result = " + result);

        //bfs
        int resultBfs = 0;
        vst = new boolean[rNum][cNum];
        for (int i = 0; i < rNum; i++) {
            for (int j = 0; j < cNum; j++) {
                if (bfs(i, j)) {
                    resultBfs++;
                }
            }
        }
        System.out.println("resultBfs = " + resultBfs);
    }

    private static boolean dfs(int x, int y) {
        if (!(-1 < x && x < rNum && -1 < y && y < cNum)) {
            return false; // 만약 범위 벗어나면 리턴
        }
        if ((graph[x].charAt(y) == '0') && !vst[x][y]) {
            vst[x][y] = true;
            dfs(x - 1, y);
            dfs(x + 1, y);
            dfs(x , y-1);
            dfs(x , y+1);
            return true;
        }
        return false;
    }

    private static boolean bfs(int x, int y) {
        if (!(-1 < x && x < rNum && -1 < y && y < cNum)) {
            return false; // 만약 범위 벗어나면 리턴
        }
        if (isValidCord(x,y)) {
            Queue<Cord> q = new LinkedList<>();
            vst[x][y] = true;
            q.offer(new Cord(x, y));

            while (!q.isEmpty()) {
                Cord cur = q.poll();
                int curX = cur.x;
                int curY = cur.y;

                for (int i = 0; i < 4; i++) {
                    int nx = curX + dx[i];
                    int ny = curY + dy[i];
                    if (isValidCord(nx, ny)) {
                        vst[nx][ny] = true;
                        q.offer(new Cord(nx, ny));
                    }
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isValidCord(int x, int y) {
        return (-1 < x && x < rNum && -1 < y && y < cNum)&&(graph[x].charAt(y) == '0') && !vst[x][y];
    }

    static class Cord{
        public int x;
        public int y;

        public Cord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}

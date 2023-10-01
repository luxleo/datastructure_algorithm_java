package algorithm.dongbin._4implementation;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class _118GAME {
    private static int[][] vst;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nr = Integer.parseInt(st.nextToken());
        int nc = Integer.parseInt(st.nextToken());

        vst = new int[nr][nc];
        map = new int[nr][nc];

        st = new StringTokenizer(br.readLine());
        int px = Integer.parseInt(st.nextToken());
        int py = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        vst[px][py] = 1; // 처음위치 방문처리

        // 맵초기화
        for (int i = 0; i < nr; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = 0;
            while (st.hasMoreTokens()) {
                map[i][idx++] = Integer.parseInt(st.nextToken());
            }
            System.out.println("map row ok");
        }


        Player player = new Player(px,py,direction);
        int result = player.travel(map, vst);
        System.out.println("result = " + result);

        br.close(); // 이거 작업이 오래 걸리는거 같다.
    }
    @Getter @Setter
    static class Player{
        private Integer px;
        private Integer py;
        private Integer directionIdx;
        public Player(Integer px, Integer py, Integer directionIdx) {
            this.px = px;
            this.py = py;
            this.directionIdx = directionIdx;
        }

        private int[] dx = {1,0,-1,0};
        private int[] dy = {0,1,0,-1};
        private int[] direction = {0,1,2,3};

        public int travel(int[][]map, int[][]vst) {
            int rowNum = map.length, colNum = map[0].length;
            int count = 1;
            int turnCnt = 0;

            int loopCnt = 1;
            while (true) {
                turnLeft();
                System.out.println(String.format("loop count : %d , cord x: %d, cord y: %d, direction: %d",loopCnt++,px,py,directionIdx));
                int nx = px + dx[directionIdx], ny = py + dy[directionIdx];
                if (-1 < nx && nx < rowNum
                        && -1 < ny && ny < colNum
                        && map[nx][ny] == 0
                        && vst[nx][ny] == 0) {
                    vst[nx][ny] = 1;
                    px = nx; py= ny;
                    count++;
                    turnCnt = 0;
                    continue;
                }else{
                    turnCnt++;
                }

                if (turnCnt == 4) {
                    nx = px - dx[directionIdx];
                    ny = py - dy[directionIdx];

                    if (map[nx][ny] == 0) {
                        px = nx;
                        py = ny;
                        turnCnt = 0;
                    }else{
                        break;
                    }
                }
            }
            return count;
        }
        public void turnLeft() {
            directionIdx--;
            if (directionIdx == -1) {
                directionIdx = 3;
            }
        }

    }
}

package leetcode.nonlinear.graph.dijkstra;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * programmers : ㄱㅔ임맵 최단거리
 * 다익스트라를 연결 그래프에서 구현한 경우이다.
 */
public class _ShortGameMap1844 {
    static class Point{
        final int x;
        final int y;
        final int distance;
        public Point(int y, int x, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    Queue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));

    public void addPath(int y, int x, int distance,int[][] map) {
        if ((y >= 0 && y < map.length)
                && (x >= 0 && x < map[0].length)
                && map[y][x] == 1) {
            map[y][x] = distance;
            pq.add(new Point(y, x, distance + 1));
        }
    }

    public int solution(int[][] maps) {
        // 처음 거리를 2로 설정하고 최후에 1을 빼준다.
        maps[0][0] = 2;
        pq.add(new Point(0, 0, 2));

        while (!pq.isEmpty()) {
            Point currentPoint = pq.poll();
            addPath(currentPoint.y+1, currentPoint.x, currentPoint.distance,maps);
            addPath(currentPoint.y-1, currentPoint.x, currentPoint.distance,maps);
            addPath(currentPoint.y, currentPoint.x+1, currentPoint.distance,maps);
            addPath(currentPoint.y, currentPoint.x-1, currentPoint.distance,maps);
        }
        int ySize = maps.length;
        int xSize = maps[0].length;

        if(maps[ySize-1][xSize-1] > 1) return maps[ySize - 1][xSize - 1]; // 2로 시작하여 1 뺴줌.
        return -1;
    }
}

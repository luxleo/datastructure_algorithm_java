package leetcode.linear.priorityq;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 중요 포인트는 새로운 클래스를 정의 하여 Comparator안에 넣는 경우이다.
 */
public class _KClosestPointsToOrigin973 {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.dist));
        for (int[] point :
                points) {
            long dist = point[0] * point[0] + point[1] * point[1];
            pq.add(new Point(point, dist));
        }

        int[][] answer = new int[k][];
        for (int i = 0; i < k; i++) {
            answer[i] = pq.poll().point;
        }
        return answer;
    }
    class Point {
        int[] point;
        long dist;

        public Point(int[] point, long dist) {
            this.point = point;
            this.dist = dist;
        }
    }
}

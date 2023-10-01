package algorithm.dongbin._5dfsbfs;

import java.util.LinkedList;
import java.util.Queue;

public class DfsBfs {
    private static boolean[] dfsVst;
    public static void main(String[] args) {
        int[][] graph = {
                {},
                {2, 3, 8},
                {1, 7},
                {1, 4, 5},
                {3, 5},
                {3, 4},
                {7},
                {2, 6, 8},
                {1, 7}
        };

        System.out.println("=======bfs======");
        bfs(graph, 1);
        System.out.println();
        System.out.println("=======dfs======");
        dfsVst = new boolean[graph.length];
        dfsVst[1] = true;
        dfs(graph, 1);
    }

    /**
     * bfs는 자료 구조로 큐를 이용한다.
     */
    public static void bfs(int[][] graph,int start) {
        boolean[] vst = new boolean[graph.length];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        vst[start] = true;

        while (!q.isEmpty()) {
            Integer cur = q.poll();
            System.out.print(String.format("%d-", cur));
            for (Integer v : graph[cur]) {
                if (!vst[v]) {
                    q.offer(v);
                    vst[v] = true;
                }
            }
        }
    }

    /**
     * dfs는 자료구조로 스택을 이용한다.=> 함수 스택을 이용하여도 무방하다.
     */
    public static void dfs(int[][] graph, int cur) {
        System.out.print(String.format("%d-", cur));
        for (Integer v : graph[cur]) {
            if (!dfsVst[v]) {
                dfsVst[v] = true; // 방문 처리 먼저 해주어야한다.
                dfs(graph, v);
            }
        }
    }
}

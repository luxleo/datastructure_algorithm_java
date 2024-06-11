package leetcode.nonlinear.graph.dijkstra;

import java.util.*;

/**
 * 24.06.11
 * 다익스트라의 기본원리를 살펴볼 수 있었다.
 * 1. 우선순위 큐를 이용하여 시간 복잡도를 ELogV로 만듦
 * 2. 다익스트라는 그래프에서 최소 연결시간을 찾을 수 있다.
 * 3. 일종의 그리디 알고리즘이다.
 */
public class _NetworkDelayTime743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] time :
                times) {
            int start = time[0];
            int end = time[1];
            int timeTaken = time[2];

            graph.putIfAbsent(start, new HashMap<>());
            graph.get(start).put(end, timeTaken);
        }

        int[] dist = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        int maxDist = -1;
        int renewedVertexCount = 0;

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());
        dist[k] = 0;
        dist[0]= -1;
        renewedVertexCount++;

        pq.add(new AbstractMap.SimpleEntry<>(k, 0));

        while (!pq.isEmpty()) {
            Map.Entry<Integer, Integer> pair = pq.poll();
            Integer currentNode = pair.getKey();
            Integer currentDist = pair.getValue();

            if (graph.containsKey(currentNode)) {
                for (Map.Entry<Integer, Integer> nextPair :
                        graph.get(currentNode).entrySet()) {
                    int nextDist = currentDist + nextPair.getValue();
                    int nextNode = nextPair.getKey();

                    //LEARN : ㄷㅏ익스트라 갱신 조건은 무조건 작아야한다. 같은 것을 포함할 경우 무한 루프에 빠진다.
                    if (dist[nextNode] > nextDist) {
                        if(dist[nextNode] == Integer.MAX_VALUE) renewedVertexCount++;
                        dist[nextNode] = nextDist;
                        pq.add(new AbstractMap.SimpleEntry<>(nextNode, nextDist));
                    }
                }
            }
        }

        if(renewedVertexCount != n) return -1;
        return Arrays.stream(dist).max().getAsInt();
    }
}

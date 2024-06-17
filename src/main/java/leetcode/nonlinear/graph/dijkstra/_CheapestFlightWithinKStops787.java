package leetcode.nonlinear.graph.dijkstra;

import java.util.*;

public class _CheapestFlightWithinKStops787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<List<Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(a->a.get(1)));
        Map<Integer, Integer> visited = new HashMap<>(); // Stop by 횟수를 담아둔다.

        // create graph
        for (int[] flight :
                flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];

            graph.putIfAbsent(from, new HashMap<>());
            graph.get(from).put(to, cost);
        }

        // init priority queue
        dist[src] = 0;
        pq.add(List.of(src, 0, 0)); // [to, cost, stopByCount]
        while (!pq.isEmpty()) {
            List<Integer> currentInfo = pq.poll();
            Integer currentNode = currentInfo.get(0);
            Integer currentCost = currentInfo.get(1);
            Integer currentStopBy = currentInfo.get(2);

            if(currentNode == dst) return currentCost;

            // fix2 여기에 분기문을 두어야 더 효율적
            if(currentStopBy > k) continue;

            visited.put(currentNode, currentStopBy);
            // fix1 만일 연결상태가 존재 한다면
            if (graph.containsKey(currentNode)) {
                for (Map.Entry<Integer, Integer> node :
                        graph.get(currentNode).entrySet()) {
                    Integer nextNode = node.getKey();
                    Integer diffValue = node.getValue();

                    //아직 방문 안했거나 했더라도 현재가 경유 횟수가 더 작은경우 갱신가능성이 있다.
                    if (!visited.containsKey(nextNode) || currentStopBy < visited.get(nextNode)) {
                        int nextDist = currentCost + diffValue;
                        pq.add(List.of(nextNode, nextDist, currentStopBy + 1));
                    }
//                    if (dist[nextNode] > nextDist) {
//                        // 다음 노드가 타깃노드이고 경유횟수가 오버되기 전일때
//                        if(currentStopBy > k) continue;
//                        dist[nextNode] = nextDist;
//                        pq.add(List.of(nextNode, nextDist, currentStopBy + 1));
//                    }
                }
            }
        }
        return -1;
    }
}

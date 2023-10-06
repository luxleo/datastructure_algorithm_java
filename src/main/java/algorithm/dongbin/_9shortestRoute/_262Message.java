package algorithm.dongbin._9shortestRoute;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _262Message {
    private static int cityNum, edgeNum;
    private static List<List<Node>> graph = new ArrayList<>();
    private static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        cityNum = Integer.parseInt(st.nextToken());
        edgeNum = Integer.parseInt(st.nextToken());
        int startCity = Integer.parseInt(st.nextToken());

        dist = Arrays.stream(new int[cityNum + 1])
                .map(el -> 1001)
                .toArray();
        dist[startCity] = 0;

        for (int i = 0; i < cityNum + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(s).add(
                    Node.builder()
                            .node(e)
                            .cost(cost)
                            .build()
            );
        }

        dijkstra(startCity);
    }

    private static void dijkstra(int start) {
        for (Node node :graph.get(start)) {
            dist[node.getNode()] = node.getCost();
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(
                Node.builder()
                        .node(start)
                        .cost(0)
                        .build()
        );
        while (!pq.isEmpty()) {
            Node rootNode = pq.poll();
            Integer rootCost = rootNode.getCost();
            Integer rootVertex = rootNode.getNode();

            List<Node> linkedList = graph.get(rootVertex);
            for (Node node : linkedList) {
                Integer curNode = node.getNode();
                int cost = node.getCost() + rootCost;
                if (dist[curNode] < cost) {
                    continue;
                }
                dist[curNode] = cost;
                pq.offer(
                        Node.builder()
                                .node(curNode)
                                .cost(cost)
                                .build()
                );
            }
        }
        int result_num = 0;
        int maxDist = 0;
        for (int curDist : dist) {
            if (curDist < 1001 && curDist>0) {
                result_num++;
                maxDist = Math.max(maxDist, curDist);
            }
        }
        System.out.printf("%d %d",result_num,maxDist);
    }

    @Getter
    @NoArgsConstructor
    static class Node implements Comparable<Node> {
        private Integer node;
        private Integer cost;

        @Builder
        public Node(Integer node, Integer cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.getCost();
        }
    }
}

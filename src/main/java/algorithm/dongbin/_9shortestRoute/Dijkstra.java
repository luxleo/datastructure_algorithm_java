package algorithm.dongbin._9shortestRoute;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * dijkstra는 그리디 전략으로 매번 최단 거리의 노드를 확정적으로 찾아낸다.
 * 구현 방식:
 *  O(V^2): 시작 노드를 제외한 나머지 노드들에 대하여 가장 가까운 노드를 조회한다 -> 이중 for문
 *  O(ElogV): 연결된 간선들에 대하여 우선순위 큐를 통하여 가장 가까운 노드를 조회하여 시간을 줄인다.
 *  TODO: 최단 경로 구하기
 */
public class Dijkstra {
    private static int nodeNum = 6, edgeNum = 11;
    private static int[][] data = {
            {1, 2, 2}, {1, 3, 5},{1,4,1},
            {2,3,3},{2,4,2},
            {3,2,3},{3,6,5},
            {4,3,3},{4,5,1},
            {5,3,1},{5,6,2}
    };
    public static int[] dist;
    public static List<List<Node>> graph = new ArrayList<>();
    private static boolean[] visited;

    public static void main(String[] args) {
        for (int i = 0; i <= nodeNum; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] row : data) {
            int startNode = row[0];
            int endNode = row[1];
            int cost = row[2];

            graph.get(startNode).add(
                    Node.builder()
                            .vertex(endNode)
                            .cost(cost)
                            .build()
            );
        }
        dijkstraNormal(1);
        dijkstraUpgrade(1);
    }

    private static void dijkstraUpgrade(int start) {
        System.out.println("[UPGRADE DIJKSTRA] with PRIORITY QUEUE called");
        dist = Arrays.stream(new int[nodeNum+1]).map(el->Integer.MAX_VALUE).toArray();
        dist[0]=-1; dist[start] = 0;

        //start node와 연결된 node들 거리 갱신해주기
        List<Node> startNodes = graph.get(start);
        for (Node node : startNodes) {
            dist[node.getVertex()] = node.getCost();
        }
        long startTime = System.currentTimeMillis();
        //LEARN: priority queue 사용법, 최대 큐는 new PriorityQueue<>(Collections.reverseOrder);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(Node.builder()
                .vertex(start)
                .cost(0)
                .build());
        while (!pq.isEmpty()) {
            Node rootNode = pq.poll();
            Integer rootVertex = rootNode.getVertex();
            Integer rootCost = rootNode.getCost();

            List<Node> linkedNode = graph.get(rootVertex);
            for (Node node : linkedNode) {
                int curCost = node.getCost() + rootCost;
                int curVertex = node.getVertex();
                if (dist[curVertex] < curCost) {
                    // 이미 갱신되어있으면 넘어간다.
                    continue;
                }
                dist[curVertex] = curCost;
                pq.offer(Node.builder()
                        .cost(curCost)
                        .vertex(curVertex)
                        .build());
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("%d(ms) take",endTime-startTime));
        System.out.println(Arrays.toString(dist));
    }

    private static void dijkstraNormal(int start) {
        System.out.println("[NORMAL DIJKSTRA] called");
        dist = Arrays.stream(new int[nodeNum+1]).map(el->Integer.MAX_VALUE).toArray();
        visited = new boolean[nodeNum+1];

        visited[start] = true;
        dist[0]=-1; dist[start] = 0;

        //start node와 연결된 node들 거리 갱신해주기
        List<Node> startNodes = graph.get(start);
        for (Node node : startNodes) {
            dist[node.getVertex()] = node.getCost();
        }
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < nodeNum-1; i++) {
            // 시작 노드를 제외하고 나머지 노드에 대하여 반복한다.
            int nearestNode = getNearestNode();
            int rootDist = dist[nearestNode];
            visited[nearestNode] = true;

            List<Node> linkedNode = graph.get(nearestNode);
            for (Node node : linkedNode) {
                int curDist = node.getCost() + rootDist;
                int curVertex = node.getVertex();

                if (curDist < dist[curVertex]) {
                    dist[curVertex] = curDist; // 갱신 해야되면 갱신하기
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("%d(ms)",endTime-startTime));

        System.out.println(Arrays.toString(dist));
    }

    private static int getNearestNode() {
        int targetNode = -1;
        int minDist = Integer.MAX_VALUE;
        for (int i = 1; i <= nodeNum; i++) {
            if (!visited[i] && dist[i] < minDist) {
                minDist = dist[i];
                targetNode = i;
            }
        }
        return targetNode;
    }
}

//LEARN: implements Comparable로 커스텀 클래스 대소 관계 만들기
@Getter @Setter
class Node implements Comparable<Node>{
    private Integer vertex;
    private Integer cost;

    @Builder
    public Node(Integer vertex, Integer cost) {
        this.vertex = vertex;
        this.cost = cost;
    }
    @Override
    public int compareTo(Node o) {
        return this.cost - o.getCost();
    }
}


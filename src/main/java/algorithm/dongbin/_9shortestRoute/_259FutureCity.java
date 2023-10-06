package algorithm.dongbin._9shortestRoute;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * a->b->c 경로의 최단 거리는
 * 플로이드 와샬: map[a][b] + map[b][c]로 구한다
 * 다익스트라 : dijkstra(a,b) + dijkstra(b,c)로 구한다.
 */
public class _259FutureCity {
    private static List<List<Node>> graph = new ArrayList<>();
    static int nodeNum, edgeNum, kNode,xNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeNum = Integer.parseInt(st.nextToken()); edgeNum = Integer.parseInt(st.nextToken());
        for (int i = 0; i < nodeNum+1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());

            List<Node> sLinkedList = graph.get(startNode);
            sLinkedList.add(Node.builder()
                    .node(endNode)
                    .cost(1)
                    .build());
            List<Node> eLinkedList = graph.get(endNode);
            eLinkedList.add(Node.builder()
                    .node(startNode)
                    .cost(1)
                    .build());
        }
        st = new StringTokenizer(br.readLine());
        xNode = Integer.parseInt(st.nextToken());
        kNode = Integer.parseInt(st.nextToken());

        System.out.println("[DIJKSTRA SOLUTION]");
        dijkstra_solution();
        System.out.println("\n[FLOYD WASHALL SOLUTION]");
        floyd_solution();
    }

    private static void floyd_solution() {
        int[][] map = new int[nodeNum + 1][nodeNum + 1];
        for (int i = 0; i < nodeNum + 1; i++) {
            for (int j = 0; j < nodeNum + 1; j++) {
                if(i == j) map[i][j] = 0;
                else map[i][j] = 101;
            }
        }
        for (int i = 1; i < nodeNum+1; i++) {
            List<Node> nodes = graph.get(i);
            for (Node node : nodes) {
                //연결된 노드는 거리 갱신해준다.
                map[i][node.getNode()] = node.getCost();
            }
        }
        for (int i = 1; i < nodeNum + 1; i++) {
            for (int j = 1; j < nodeNum+1; j++) {
                if(j == i) continue;
                for (int k = 1; k < nodeNum+1; k++) {
                    if(k == j || k == i) continue;
                    map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                }
            }
        }
        System.out.printf("DIST OF (1-k-x) : %d",map[1][kNode]+map[kNode][xNode]);
    }

    private static void dijkstra_solution() {
        int k_dist = dijkstra(1, kNode);
        System.out.println("k_dist = " + k_dist);
        int x_dist = dijkstra(kNode, xNode);
        System.out.println("x_dist = " + x_dist);
        if (k_dist == -1 || x_dist == -1) {
            System.out.println(-1);
            return;
        }
        System.out.println(k_dist + x_dist);
    }
    private static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(Node.builder()
                .node(start)
                .cost(0)
                .build());
        int[] dist = Arrays.stream(new int[nodeNum + 1])
                .map(el -> 101)
                .toArray();
        dist[0] = 0;
        dist[start] = 0;
        for (Node v : graph.get(start)) {
            dist[v.getNode()] = v.getCost();
        }

        while (!pq.isEmpty()) {
            Node rootNode = pq.poll();
            int rootVertex = rootNode.getNode();
            int rootCost = rootNode.getCost();

            List<Node> rootLinkedList = graph.get(rootVertex);
            for (Node node: rootLinkedList) {
                int curNode = node.getNode();
                //LEARN: 밑에 거 때문에 자꾸 에러났다. cost = dist[curNode]하면 안된다.
                int cost = node.getCost() + rootCost;

                if(dist[curNode]<cost) continue;
                dist[curNode] = cost;
                pq.offer(Node.builder()
                        .node(curNode)
                        .cost(cost)
                        .build());
            }
        }
        System.out.println(Arrays.toString(dist));
        return dist[end] > 100 ? -1 : dist[end]; //불가능한 경우 -1반환
    }
    @Getter
    @NoArgsConstructor
    static class Node implements Comparable<Node>{
        int node;
        int cost;
        @Builder
        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.getCost();
        }
    }
}


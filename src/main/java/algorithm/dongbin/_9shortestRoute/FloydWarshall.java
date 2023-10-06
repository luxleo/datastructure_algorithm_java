package algorithm.dongbin._9shortestRoute;

import java.io.IOException;
import java.util.Arrays;

/**
 * dijkstra는 하나의 노드로 부터 다른 노드까지의 최단 거리를 구하는 알고리즘 : 그리디 기반
 * floyd warshall은 모든 노드의 모든 노드까지의 최단거리를 구하는 알고리즘 : dynamic programming 기반
 * 플로이드 와샬은 D(a,b) = min(D(a,b),D(a,k)+D(k,b))의 점화식으로 나타내어지므로 DP의 일종이다.
 * n개의 노드가 주어질때 현 순회중인 노드를 제외한 n-1P2를 가져야하므로 O(n^3)이다.
 */
public class FloydWarshall {
    private static int nodeNum=4,edgeNum=7;
    private static int[][] data = {
            {1, 2, 4}, {1, 4, 6},
            {2, 1, 3}, {2, 3, 7},
            {3, 1, 5}, {3, 4, 4},
            {4, 3, 2}
    };
    private static int[][] graph;
    private static int[][] graphNormal;
    private static int[][] graphAdvance;
    private static int[] numOfExecution = new int[2];

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        nodeNum = Integer.parseInt(br.readLine());
//        edgeNum = Integer.parseInt(br.readLine());
        graph = new int[nodeNum + 1][nodeNum + 1];

        for (int i = 1; i < nodeNum + 1; i++) {
            for (int j = 1; j < nodeNum + 1; j++) {
                if (i == j) {
                    //자기 자신까지의 거리는 0으로 초기화 나머지는 무한으로
                    graph[i][j] = 0;
                    continue;
                }
                graph[i][j] = Integer.MAX_VALUE/3;
            }
        }

        //간선 정보 입력 받아 graph갱신
//        StringTokenizer st;
//        for (int i = 0; i < edgeNum; i++) {
//            st = new StringTokenizer(br.readLine());
//            int startNode = Integer.parseInt(st.nextToken());
//            int endNode = Integer.parseInt(st.nextToken());
//            int cost = Integer.parseInt(st.nextToken());
//
//            graph[startNode][endNode] = cost;
//        }
        for (int[] info : data) {
            int startNode = info[0];
            int endNode = info[1];
            int cost = info[2];

            graph[startNode][endNode] = cost;
        }
        // 각각 복사 해주기
        graphNormal = deepcopy(graph);
        graphAdvance = deepcopy(graph);

        floydWarshall_Normal();
        floydWarshall_Advance();
    }

    static void floydWarshall_Normal() {
        System.out.println("[NORMAL FLOYD MARSHALL] called");
        for (int i = 1; i < nodeNum + 1; i++) {
            for (int j = 1; j < nodeNum + 1; j++) {
                for (int k = 1; k < nodeNum + 1; k++) {
                    graphNormal[j][k] = Math.min(graphNormal[j][k], graphNormal[j][i] + graphNormal[i][k]);
                    numOfExecution[0]++;
                }
            }
        }
        for (int i = 0; i < graphNormal.length; i++) {
            System.out.println(Arrays.toString(graphNormal[i]));
        }
        System.out.printf("[EXECUTE NUMBER] %d times",numOfExecution[0]);
    }

    /**
     * 중복 조건을 줄여 불필요한 연산 없애기.
     */
    static void floydWarshall_Advance() {
        System.out.println("[Advance FLOYD MARSHALL] called");
        for (int i = 1; i < nodeNum + 1; i++) {
            for (int j = 1; j < nodeNum + 1; j++) {
                if(j == i) continue;
                for (int k = 1; k < nodeNum + 1; k++) {
                    if(k == i || k==j) continue;
                    graphAdvance[j][k] = Math.min(graphAdvance[j][k], graphAdvance[j][i] + graphAdvance[i][k]);
                    numOfExecution[1]++;
                }
            }
        }
        for (int i = 0; i < graphAdvance.length; i++) {
            System.out.println(Arrays.toString(graphAdvance[i]));
        }
        System.out.printf("[EXECUTE NUMBER] %d times",numOfExecution[1]);
    }


    //LEARN: 이차원 배열 복사는 행별로 순회하며 해주어야한다.
    static int[][] deepcopy(int[][] data) {
        int[][] result = new int[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            result[i] = Arrays.stream(data[i]).toArray();
        }
        return result;
    }
}

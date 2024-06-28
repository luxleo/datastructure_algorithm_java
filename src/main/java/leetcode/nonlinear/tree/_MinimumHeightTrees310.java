package leetcode.nonlinear.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * dfs로 모든 노드를 루트로 처리할 생각이었으나 너무 비효율적 TODO: 한번 구현 해보기
 * 아이디어: 종단 노드를 순차적으로 제거해 나간다. -> 최후 횟수에 남은 노드들이 최소 높이를 갖는 노드들이다.
 */
public class _MinimumHeightTrees310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1) return List.of(0);
        //무방향 연결 그래프 구성
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] link = edges[i];
            tree.putIfAbsent(link[0], new LinkedList<>());
            tree.putIfAbsent(link[1], new LinkedList<>());

            tree.get(link[0]).add(link[1]);
            tree.get(link[1]).add(link[0]);
        }

        // 1차 종단 노드 생성
        List<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if(tree.get(i).size() == 1) leaves.add(i);
        }

        // 리프노드를 남은 노드가 2개 이하일 때까지 순차적으로 제거
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new LinkedList<>();
            for (Integer leaf :
                    leaves) {
                Integer targetNode = tree.get(leaf).get(0);
                tree.get(targetNode).remove((Object) leaf);

                if(tree.get(targetNode).size() == 1) newLeaves.add(targetNode);
            }

            leaves = newLeaves;
        }

        return leaves;
    }
}

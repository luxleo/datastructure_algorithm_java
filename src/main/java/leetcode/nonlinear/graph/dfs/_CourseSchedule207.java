package leetcode.nonlinear.graph.dfs;

import java.util.*;

/**
 * 2024.04.23
 * 순환참조인지 검증하는 문제였다. + 가지치기로 이미 처리한 노드는 패스한다.
 * 그래프의 연결 방식을 Map<Integer,List<Integer>>로도 표현할수 있음
 */
public class _CourseSchedule207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> mapToFinish = new HashMap<>();
//        Arrays.stream(prerequisites).forEach(data -> {
//            mapToFinish.putIfAbsent(data[0], new ArrayList<>());
//            mapToFinish.get(data[0]).add(data[1]);
//        });

        //List<Integer> processingNodes = new ArrayList<>();
        //List<Integer> processedNodes = new ArrayList<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int[] currentData = prerequisites[i];
            mapToFinish.putIfAbsent(currentData[0], new ArrayList<>());
            mapToFinish.get(currentData[0]).add(currentData[1]);
        }
        int[] processingNodes = new int[numCourses];
        int[] processedNodes = new int[numCourses];

        for (Integer node :
                mapToFinish.keySet()) {
            if (!dfs(processingNodes, mapToFinish, processedNodes, node)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[] processingNodes, Map<Integer, List<Integer>> mapToFinish, int[] processedNodes, Integer currentNode) {
        if (processedNodes[currentNode] == 1) {
            processingNodes[currentNode]=0;
            return true;
        }
        if(processingNodes[currentNode]==1) return false;

        if (mapToFinish.containsKey(currentNode)) {
            processingNodes[currentNode]=1;
            for (Integer linkedNode :
                    mapToFinish.get(currentNode)) {
                if (!dfs(processingNodes, mapToFinish, processedNodes, linkedNode)) return false;
            }
            processingNodes[currentNode]=0;
        }
        processedNodes[currentNode] = 1;
        return true;
    }
}

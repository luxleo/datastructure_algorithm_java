package leetcode.nonlinear.graph.dfs;

import java.util.*;

public class _ReconstructItinerary332 {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> fromToMap = new HashMap<>();
        for (int i = 0; i < tickets.size(); i++) {
            List<String> currentTickets = tickets.get(i);
            fromToMap.putIfAbsent(currentTickets.get(0), new PriorityQueue<>());
            fromToMap.get(currentTickets.get(0)).add(currentTickets.get(1));
        }
        List<String> result = new LinkedList<>();
//        recur_dfs(result, fromToMap, "JFK");
        iter_dfs(result, fromToMap);
        return result;
    }

    public void recur_dfs(List<String> results, Map<String, PriorityQueue<String>> fromToMap, String from) {
        while (fromToMap.containsKey(from) && !fromToMap.get(from).isEmpty()) {
            String currentTo = fromToMap.get(from).poll();
            recur_dfs(results, fromToMap, currentTo);
        }
        results.add(0,from);
    }
    //LEARN: pop 은 앞에거 꺼내오고 push는 앞으로 넣음
    public void iter_dfs(List<String> result, Map<String,PriorityQueue<String>> fromToMap) {
        Deque<String> stack = new ArrayDeque<>();
        stack.push("JFK");

        while (!stack.isEmpty()) {
            while (fromToMap.containsKey(stack.getFirst()) && !fromToMap.get(stack.getFirst()).isEmpty()) {
                String nextLocation = fromToMap.get(stack.getFirst()).poll();
                stack.push(nextLocation);
            }
            result.add(0,stack.pop());
        }
    }
}

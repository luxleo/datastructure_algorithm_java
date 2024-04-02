package leetcode.linear.hashtable;

import java.util.*;

public class _TopKFrequentElements347 {
    /**
     * hash를 복수번 이용하여 정렬구현
     */
    public int[] topKFrequentV1(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int num :
                nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        Map<Integer, List<Integer>> sortWithCount = new HashMap<>();

        Set<Map.Entry<Integer, Integer>> countMapEntries = countMap.entrySet();
        for (Map.Entry<Integer, Integer> entry :
                countMapEntries) {
            List<Integer> targetList = sortWithCount.getOrDefault(entry.getValue(), new ArrayList<>());
            sortWithCount.put(entry.getValue(), targetList);
            // 빈도수가 나온 숫자를 넣어준다.
            targetList.add(entry.getKey());
        }

        int[] answer = new int[k];
        int idx = 0;

        for (int i = nums.length; i > 0 && idx < k; i--) {
            List<Integer> currentFreqList = sortWithCount.get(i);
            if(currentFreqList == null) continue;

            for (int j = 0; j < currentFreqList.size() && idx < k; j++) {
                answer[idx++] = currentFreqList.get(j);
            }
        }

        return answer;
    }

    /*
    priority queue이용
     */
    public int[] topKFrequentV2(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int num :
                nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        //최대 정렬 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        Set<Map.Entry<Integer, Integer>> entries = countMap.entrySet();

        for (Map.Entry<Integer, Integer> entry :
                entries) {
            int[] data = new int[]{entry.getKey(), entry.getValue()};
            pq.add(data);
        }

        int[] answer = new int[k];
        for (int i = 0; i < k; i++) {
            answer[i] = pq.poll()[0];
        }
        return answer;
    }
}

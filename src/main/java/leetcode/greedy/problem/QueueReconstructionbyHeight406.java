package leetcode.greedy.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class QueueReconstructionbyHeight406 {
    public int[][] reconstructQueue(int[][] people) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ?
                b[0] - a[0]
                : a[1] - b[1]
        );
        List<int[]> answer = new ArrayList<>();
        final int peopleNum = people.length;

        for (int i = 0; i < peopleNum; i++) {
            pq.add(people[i]);
        }

        while (!pq.isEmpty()) {
            int[] person = pq.poll();
            answer.add(person[1], person);
        }
        return answer.toArray(new int[peopleNum][2]);
    }
}

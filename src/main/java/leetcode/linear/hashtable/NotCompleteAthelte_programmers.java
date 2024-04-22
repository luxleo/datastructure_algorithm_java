package leetcode.linear.hashtable;

import java.util.HashMap;
import java.util.Map;

public class NotCompleteAthelte_programmers {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        int length = participant.length;

        for (int i = 0; i < length; i++) {
            // 동명이인 고려
            map.put(participant[i], map.getOrDefault(participant[i],0)+1);
        }
        for (int i = 0; i < length - 1; i++) {
            Integer current = map.get(completion[i]);
            if(current == 1) map.remove(completion[i]);
            else map.put(completion[i], current - 1);
        }

        return map.keySet().iterator().next();
    }
}

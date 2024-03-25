package leetcode.linear.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JewelsAndStone771 {

    public int numJewelsInStonesV1(String jewels, String stones) {
        Map<String, Integer> jewelMap = new HashMap<>();
        int sizeOfJewels = jewels.length();
        int sizeOfStones = stones.length();

        for (int i = 0; i < sizeOfJewels; i++) {
            jewelMap.put(String.valueOf(jewels.charAt(i)), 0);
        }
        for (int i = 0; i < sizeOfStones; i++) {
            String currentStone = String.valueOf(stones.charAt(i));
            if(jewelMap.containsKey(currentStone))
                jewelMap.put(currentStone, jewelMap.get(currentStone) + 1);
        }

        return jewelMap.values().stream()
                .reduce(0, Integer::sum);
    }
    public int numJewelsInStonesV2(String jewels, String stones) {
        int count = 0;
        int sizeOfJewels = jewels.length();
        int sizeOfStones = stones.length();
        Set<Character> jewelSet = new HashSet<>();

        for (int i = 0; i < sizeOfJewels; i++) {
            jewelSet.add(jewels.charAt(i));
        }

        for (int i = 0; i < sizeOfStones; i++) {
            if (jewelSet.contains(stones.charAt(i))) {
                count++;
                continue;
            }
        }
        return count;
    }
}

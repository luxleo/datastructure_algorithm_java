package leetcode.implement;

import java.util.*;

public class _Group49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        for (String str: strs) {
            String curKey = getKey(str);
            if(groups.containsKey(curKey)) {
                groups.get(curKey).add(str);
            } else
                groups.put(curKey, new ArrayList<>(Arrays.asList(str)));
        }
        for (Map.Entry<String,List<String>> group  : groups.entrySet()) {
            Collections.sort(group.getValue());
            result.add(group.getValue());
        }
        return result;
    }

    public String getKey(String data) {
        char[] chars = data.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}

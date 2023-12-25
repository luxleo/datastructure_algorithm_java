package leetcode.implement;

import java.util.*;

/**
 * @see <a href='https://leetcode.com/problems/most-common-word/submissions/1127691187/'>문제 링크</a>
 */
public class _Map819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedWords = new HashSet<>(Arrays.asList(banned));
        String[] words = paragraph.replaceAll("\\W+", " ").toLowerCase().split(" ");
        HashMap<String, Integer> cnt = new HashMap<>();
        for (String word : words) {
            if (!bannedWords.contains(word))
                cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        // HashMap.entrySet()으로 key, value를 획득 할 수 있다.
        return Collections.max(cnt.entrySet(), Map.Entry.comparingByValue())
                .getKey();
    }
}


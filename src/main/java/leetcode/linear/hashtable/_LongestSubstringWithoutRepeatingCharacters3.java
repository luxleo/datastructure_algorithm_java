package leetcode.linear.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 전략: 투포인터를 이용하여 주어진 문자열을 순회하며 맵에 마지막 인덱스를 기록해나가고
 * 만일 중복된 문자열이 나타나는 경우 맵을 갱신해준다.
 */
public class _LongestSubstringWithoutRepeatingCharacters3 {
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int maxLength = 0;
        Map<Character, Integer> lastIndex = new HashMap<>();

        int sizeOfString = s.length();
        for (int i = 0; i < sizeOfString; i++) {
            char currentChar = s.charAt(i);
            if (lastIndex.containsKey(currentChar) && left <= lastIndex.get(currentChar)) {
                // 중복된 문자가 나온경우: 중복된 문자다음의 인덱스로 left 옮겨준다.
                left = lastIndex.get(currentChar) + 1;
            }else {
                // 중복되지 않은 문자의 경우 최대길이를 갱신해준다.
                maxLength = Math.max(maxLength, right - left + 1);
            }
            lastIndex.put(currentChar, i);
            right++;
        }

        return maxLength;
    }
}

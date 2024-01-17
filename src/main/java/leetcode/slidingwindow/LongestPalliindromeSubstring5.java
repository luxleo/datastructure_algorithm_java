package leetcode.slidingwindow;

public class LongestPalliindromeSubstring5 {
    int left, maxLen, sLength;
    public String longestPalindrome(String s) {
        sLength = s.length();
        if(sLength == 1)
            return s;
        for (int i = 0; i < sLength; i++) {
            expandWindow(s, i, i + 1);
            expandWindow(s, i, i + 2);
        }
        return s.substring(left, left+maxLen);
    }

    public void expandWindow(String data, int s, int e){
        while (s >= 0 && e < sLength && data.charAt(s) == data.charAt(e)) {
            s--;
            e++;
        }
        int curLength = e - s - 1;
        if (maxLen < curLength) {
            maxLen = curLength;
            left = s + 1;
        }
    }
}

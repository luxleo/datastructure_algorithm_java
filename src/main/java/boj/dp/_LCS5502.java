package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @see <a href='https://www.acmicpc.net/problem/5502'>5502 문제</a>
 * 펠린드롬 기본문제
 * dp의 LCS(least common sequence) 파악하는 문제이다.
 * 2차원 배열로 LCS를 진행하였다.
 */
public class _LCS5502 {
    public static int[][] dp;
    public static int n;
    public static String data;
    public static String revData;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        data = br.readLine().trim();
        revData = new StringBuilder(data).reverse().toString();
        dp = new int[n+1][n+1];

        solve();
    }

    public static void solve() {
        findLCS();
        System.out.printf("%d",n - dp[n][n]);
    }
    /**
     * @see <a href='https://www.youtube.com/watch?v=sSno9rV8Rhg'>LCS 강의</a>
     * 최장 공통 수열을 구하자.
     */
    public static void findLCS() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(data.charAt(i) == revData.charAt(j))
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                else
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return;
    }

}

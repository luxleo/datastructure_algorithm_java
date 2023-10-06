package algorithm.dongbin._8dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * dp + 점화식의 대표적인 유형이다.
 * 점화식을 파악할때는 서로 겹치지 않고 누락 되지 않게 한다.
 * 항사이에 어떤 관계가 있는지 살피자.
 */
public class _223FloorConstruction {
    private static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        if(size == 1) {
            System.out.println(1);
            return;
        } else if (size == 2) {
            System.out.println(3);
            return;
        }
        dp = new int[size];
        dp[0] = 1;
        dp[1] = 3;
        for (int i = 2; i < size; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 796796;
        }
        System.out.println(dp[size - 1]);
        br.close();
    }
}

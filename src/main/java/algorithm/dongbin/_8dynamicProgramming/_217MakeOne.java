package algorithm.dongbin._8dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _217MakeOne {
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("type size : ");
        int size = Integer.parseInt(br.readLine());
        dp = new int[size + 1];

        int result=0;
        for (int i = size-1; i > 0; i--) {
            int fiveTimes = i * 5 <= size? i*5 : 0;
            int threeTimes = i * 3 <= size? i*3 : 0;
            int doubleTimes = i * 2 <= size? i*2 : 0;

            int[] temp = {dp[fiveTimes], dp[threeTimes], dp[doubleTimes], dp[i + 1]};
            dp[i] = Arrays.stream(temp).min().getAsInt()+1;
        }
        result = dp[1];
        System.out.println("\nresult = " + result);
    }
}

package algorithm.dongbin._8dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 마찬가지로 dp의 점화식 문제 였다.
 * 이전의 항들과 어떤 관계가 있는지 잘 살피고
 * top-down, bottom-up방식 중 어떻게 갱신 해야 맞는지 잘 파악하자
 * 이 경우 bottom-up이 그럴싸해 보였으나 top-down식으로 접근해야만 했다.
 */
public class _226EfficientMoneyCombination {
    private static int[] data;
    private static int[] coins;

    public static void main(String[] args) throws IOException {
        //data 입력부
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("동전 갯수, 목표금액 입력");
        StringTokenizer st = new StringTokenizer(br.readLine());

        int coinNum = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        coins = new int[coinNum];
        data = Arrays.stream(new int[target+1]).map(e -> 10001).toArray();

        System.out.println("동전 입력");
        while (coinNum-- > 0) {
            coins[coinNum] = Integer.parseInt(br.readLine());
        }
        data[target] = 0;
        data[0] = 0;
        //solution
        for (int i = target; i > 0 ; i--) {
            for (int coin : coins) {
                int curIdx = i - coin;
                if(curIdx <0) continue;
                data[curIdx] = Math.min(data[curIdx], data[i] + 1);
            }
        }
        if(data[0] !=0)
        System.out.println(data[0]);
        else System.out.println(-1);
        br.close();
    }
}

package algorithm.dongbin._8dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * dynamic programming은 한번 계산한 값은 다시 계산하지 않도록 추가적인 메모리를 이용하여 한번이라도 계산한 값은 기록을한다.
 * 기록의 방식:
 *  array: index로 값을 갱신하는 점화식 구조에 유용한
 *  map: key로 연속적이지 않은 계산식을 저장할때 유용
 */
public class DynamicProgramming {
    private static int[] logOfExecutionNum = new int[3];
    private static int[] dpTopDown;
    private static int[] dpBottomUp;
    /**
     * dp를 사용한 방식,dp top-down, bottom-up재귀 반복문으로 비교해본다.
     */
    public static void main(String[] args) throws IOException {
        int sig = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("enter fibo num: ");
        sig = Integer.parseInt(br.readLine().trim());
        while (sig > 0) {
            System.out.println(String.format("\n[FIBONUM ==%d]", sig));
            //초기화
            logOfExecutionNum = new int[3];
            dpTopDown = new int[sig + 1];
            dpBottomUp = new int[sig + 1];
            fibo_recur(sig);
            fibo_top_down_dp(sig);
            fibo_bottomUpDp(sig);
            System.out.println(String.format("%-17s:%d\n%-17s:%d\n%-17s:%d\n",
                    "fibo_recur",logOfExecutionNum[0]
                    ,"fibo_topDownDp",logOfExecutionNum[1]
                    ,"fibo_bottomUpDP",logOfExecutionNum[2]));

            System.out.println("to exit type -1");
            System.out.print("enter next fibo num: ");
            sig = Integer.parseInt(br.readLine().trim());
        }
        br.close();
    }

    private static int fibo_recur(int n) {
        if(n>30) return -1;
        logOfExecutionNum[0]++;

        if (n == 1 || n == 2) {
            return 1;
        }
        return fibo_recur(n - 1) + fibo_recur(n - 2);
    }

    private static int fibo_top_down_dp(int n) {
        logOfExecutionNum[1]++;
        if(n == 1 || n==2 ) return 1;

        if(dpTopDown[n] !=0) return dpTopDown[n];
        return dpTopDown[n] = fibo_top_down_dp(n - 1) + fibo_top_down_dp(n - 2);
    }

    private static int fibo_bottomUpDp(int n) {
        if(n == 1 || n==2) return 1;
        dpBottomUp[1] = 1;
        dpBottomUp[2] = 1;

        for (int i = 3; i < n + 1; i++) {
            dpBottomUp[i] = dpBottomUp[i - 1] + dpBottomUp[i - 2];
            logOfExecutionNum[2]++;
        }
        return dpBottomUp[n];
    }
}

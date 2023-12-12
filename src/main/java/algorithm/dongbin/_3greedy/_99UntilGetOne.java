package algorithm.dongbin._3greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _99UntilGetOne {
    static int n;
    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        sol101();
        sol102Advanced();
    }

    static void sol101() {
        int result=0;
        int localN = n;
        while (localN >= k) {
            //나누어 떨어질때까지 1을 감소시킨다.
            while (localN % k != 0) {
                localN -= 1;
                result += 1;
            }
            localN /= k;
            result += 1;
        }
        // k로 나눌수 없는 경우 다 더해준다.
        while (localN > 1) {
            localN -= 1;
            result += 1;
        }
        System.out.println("result = " + result);
    }

    static void sol102Advanced() {
        int result = 0;
        int localN = n;
        while (localN >= k) {
            //빼야할 값을 미리 구한다.
            int target = (localN / k) * k;
            result += localN - target; // 구한값을 더해준다.
            localN = target;
        }
        result += (localN-1);
        System.out.println("result = " + result);
    }
}

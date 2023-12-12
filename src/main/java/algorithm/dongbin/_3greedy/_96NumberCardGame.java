package algorithm.dongbin._3greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;

public class _96NumberCardGame {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // n,m 받기
        String[] rAndC = br.readLine().trim().split(" ");
        int n = Integer.parseInt(rAndC[0]);
        int m = Integer.parseInt(rAndC[1]);

        int result = 0;
        //n번 만큼 순회한다. 매 순회 마다 주어지는 데이터 중 가장 작은값, 현재 result를 비교 하여 큰 쪽으로 갱신한다.
        for (int i = 0; i < n; i++) {
            Optional<Integer> curMinVal = Arrays.stream(br.readLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .sorted()
                    .min(Integer::compareTo);
            result = Math.max(result, curMinVal.orElse(result));
        }
        System.out.println("result = " + result);
    }
}

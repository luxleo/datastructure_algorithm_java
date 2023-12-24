package algorithm.dongbin._3greedy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class _92BigNum {
    static Integer n;
    static Integer m;
    static Integer k;
    static Integer first,second;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String stringInput = br.readLine().trim();

        // n,m,k 할당
        List<Integer> input = Arrays.stream(stringInput.split(" "))
                .mapToInt(Integer::parseInt)
                .boxed() // int -> Integer로 변환(Collection에는 Wrapper밖에 못 담는다).
                .collect(Collectors.toList());
        n = input.get(0);
        m = input.get(1);
        k = input.get(2);

        // data 받아오기, 가장 큰 수, 가장 작은 수 저장
        List<Integer> data = Arrays.stream(br.readLine().trim().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        first=data.get(0);
        second = data.get(1);
        simpleSol();
        advancedSol();
    }

    static void simpleSol() {
        System.out.println("[93p 단순한 반복문 솔루션]");
        int localM = m.intValue();
        int result = 0;

        while (true) {
            //k번 동안 최대횟수를 더하며 최대횟수만큼 더하도록 -=1처리
            for (int i = 0; i < k; i++) {
                if (localM == 0)
                    // 최대횟수 m 번 다 더했으면 반복문 탈출
                    break;
                result += first;
                localM -= 1;
            }
            if (localM == 0)
                // 최대횟수 m 번 다 더했으면 반복문 탈출
                break;
            result += second;
            localM -= 1;
        }
        System.out.println("result = " + result);
    }

    static void advancedSol() {
        System.out.println("[95p 수를 이용한 최적화 솔루션]");
        int cnt = (m / (k + 1)) * k; // 가장 큰 수를 더하는 횟수 계산
        int remainder = m - cnt; // 두번째 큰 수를 더하는 횟수

        int result = cnt * first + remainder * second;
        System.out.println("result = " + result);
    }
}

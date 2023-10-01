package algorithm.dongbin._4implementation;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Getter @Setter
public class _113TIME {
    private static Integer totalMinCount = 60 * 60; // 00분 00초로 조합가능하다.
    private static Integer withoutThree = 45 * 45;
    private static Integer numberIncludeThree = totalMinCount - withoutThree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int result = 0;

        for (int i = 0; i < N + 1; i++) {
            if (i % 10 == 3) {
                result += totalMinCount;
            }else{
                result += numberIncludeThree;
            }
        }

        System.out.println("result = " + result);
    }

}

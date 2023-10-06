package algorithm.dongbin._8dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 전형적인 dp + 점화식 문제이다.
 * Bn = Math.max(An-2 + An, An-1)이다. (dp 수열의 각항 Bn은 data An의 각항과 왼쪽과 같은 관계를 가진다.)
 * 만일 조합으로 구하려는 경우 재귀함수로 재귀 호출을 통해서 구할수 있을 텐데 O(N^2)를 가질 것이다
 */
public class _220AntWorrior {
    private static int[] data;
    public static void main(String[] args) throws IOException {
        //accept data
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("type size of storage");
        int size = Integer.parseInt(br.readLine().trim());
        if(size<3){
            System.out.println("size has to be bigger than 3");
            return;
        }
        data = new int[size];

        System.out.println("type amount of each storage seperated with space");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int idx = 0;
        while (st.hasMoreTokens()) {
            data[idx++] = Integer.parseInt(st.nextToken());
        }

        // solution
        for (int i = 2; i < size; i++) {
            data[i] = Math.max(data[i] + data[i - 2], data[i - 1]);
        }
        System.out.println(Math.max(data[size - 1], data[size - 2]));
        br.close();
    }
}

package algorithm.dongbin._7binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * parametric search(upper bound)이용하여 최적해 찾자
 */
public class _201CreateRiceCake {
    private static int[] data;
    private static int standard = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        standard = Integer.parseInt(st.nextToken());

        data = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        int max = Arrays.stream(data).max().getAsInt();
        System.out.println(upperBoundBinarySearch(max));
    }

    private static int upperBoundBinarySearch(int maxLength) {
        int start = 0, end = maxLength;
        int mid = -1;
        int tot = 0;
        while (start <= end) {
            mid = (start + end) >> 1;
            tot = 0;
            for (int el: data) {
                int cur = (el - mid) > 0 ? el - mid : 0;
                tot += cur;
            }
            if (tot > standard) {
                start = mid + 1;
            } else if (tot < standard) {
                end = mid - 1;
            }else {
                return mid;
            }
        }
        return start;
    }
}

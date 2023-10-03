package algorithm.dongbin._7binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _197FindParts {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int[] data = new int[m];
        Arrays.sort(data);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < data.length; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        int n = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine());
        int target = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            target = Integer.parseInt(st.nextToken());
            if (binarySearch(target, data) == -1) {
                sb.append("no ");
            } else {
                sb.append("yes ");
            }
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static int binarySearch(int target, int[] data) {
        int start = 0, end = data.length-1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) >> 1;
            int cur = data[mid];

            if (cur > target) {
                end = mid - 1;
            } else if (cur < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

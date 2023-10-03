package algorithm.dongbin._7binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * 이진 탐색은 정렬되어있는 데이터에 사용가능하며
 * 탐색해야할 데이터가 n개일때 시간 복잡도는 O(logN)이다.
 */
public class BinarySearch {
    private static int[] data;
    private static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("배열 범위는 어디까지요?:");
        StringTokenizer st = new StringTokenizer(br.readLine());

        int limit = Integer.parseInt(st.nextToken());
        data = IntStream.rangeClosed(0, limit).toArray();

        System.out.println("찾으려는 숫자를 말해보시오:");
        st = new StringTokenizer(br.readLine());
        int target = Integer.parseInt(st.nextToken());
        int targetIdx = binarySearch(target);
        System.out.println(String.format("인덱스: %d, 연산횟수: %d", targetIdx, count));
        count = 0; // upperbound search 를 위해 초기화

        targetIdx = upperBoundBinarySearch(target);
        System.out.println(String.format("인덱스: %d, 연산횟수: %d", targetIdx, count));
    }

    /**
     * TODO: upper bound, 구현 해보기
     */
    private static int binarySearch(int target) {
        int start = 0, end = data.length - 1;
        int mid = 0;
        while (start <= end) {
            count++;
            mid = (start + end) >> 1;
            int cur = data[mid];
            if (cur < target) {
                start = mid + 1;
            } else if (cur>target) {
                end = mid - 1;
            }else{
                return mid;
            }
        }
        return -1; // 찾는 target이 없는 경우 -1 반환하기
    }

    /**
     * 만약 찾는 target이 없으면 target이 들어가야하는 위치를 찾는다
     * upperbound 형식이다.
     */
    private static int upperBoundBinarySearch(int target) {
        int start = 0, end = data.length - 1;
        int mid = 0;
        while (start <= end) {
            count++;
            mid = (start + end) >> 1;
            int cur = data[mid];
            if (cur < target) {
                start = mid + 1;
            } else if (cur>target) {
                end = mid - 1;
            }else{
                return mid;
            }
        }
        return start; // 찾는 target이 없는 경우 upper bound ->
    }
}

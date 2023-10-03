package algorithm.dongbin._6sorting;

import java.util.Arrays;

public class SortingAlgorithms {
    private static int[] data = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

    public static void main(String[] args) {
        System.out.println("original data = " + Arrays.toString(data));
        System.out.println(Arrays.toString(selectionSort()));
        System.out.println(Arrays.toString(insertSort()));
        System.out.println(Arrays.toString(iniQuickSort()));

    }

    /**
     * 선택 정렬이다. 모든 리스트를 순회하며 현재 조회하는 인덱스보다 큰 요소들을
     * 조회한다. 이때 현재 인덱스의 배열값보다 작은 녀석이 있으면 index를 기록하고
     * 가장 작은 값을 가지는 인덱스의 값과 swapping한다.
     *
     * 따라서 O(n^2)의 시간 복잡도를 가진다.
     */
    private static int[] selectionSort() {
        System.out.println("[selection sort started]");
        int[] copiedData = Arrays.stream(data)
                .toArray();

        for (int i = 0; i < copiedData.length; i++) {
            int minIdx = i;
            for (int j = i+1; j <copiedData.length ; j++) {
                if (copiedData[minIdx] > copiedData[j]) {
                    minIdx = j; // 현재 기준 인덱스 보다 작은 녀석이 있으면 기준 인덱스를 바꾸어준다.
                }
            }
            //swapping 해준다.
            int temp = copiedData[i];
            copiedData[i] = copiedData[minIdx];
            copiedData[minIdx] = temp;
        }
        return copiedData;
    }

    /**
     * insert sort( <-- )는 select sort( --> ) 와 진행 방향이 반대이다.
     * 아래로 탐색하며 크기를 맞추어 준다. 버블 정렬 처럼
     * 특징 : 잘 정렬되어있을수록 빠른 속도를 자랑한다.(중간에 break문이 있으므로)
     */
    private static int[] insertSort() {
        System.out.println("[insert sort start]");
        int[] rawData = Arrays.stream(data).toArray();
        int length = rawData.length;

        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0; j--) {
                if (rawData[j] < rawData[j - 1]) {
                    // 만일 정렬해야 되는 대상이면 정렬하면서 내려간다.
                    int temp = rawData[j];
                    rawData[j] = rawData[j - 1];
                    rawData[j - 1] = temp;
                }else {
                    // 이미 자기 보다 작게 정렬되어있으면 패스한다.
                    break;
                }
            }
        }
        return rawData;
    }

    private static void quickSort(int[] data,int start, int end) {
        if(start >=end) return;
        int pivot = start;
        int left = start + 1, right = end;
        int standard = data[pivot];

        while (left <= right) {
            // 기준에 맞게 left,right 인덱스 이동시켜준다.
            while (left <= end && data[left] <= standard) {
                left += 1;
            }
            while (right > start && data[right] >= standard) {
                right -= 1;
            }
            if (left > right) {
                // 종료 조건으로 left와 right가 교차하였을때 이다.
                // pivot인덱스와 right인덱스를 교환 해준다. (현 시점의 right인덱스가 가리키는 요소는 standard보다 항상작으므로
                data[pivot] = data[right];
                data[right] = standard;
            } else{
                //만일 서로 교차 하지 못했다면 정렬조건을 이어나가고 충족하기 위해서
                //left,right의 위치를 교환 해준다.
                int temp = data[left];
                data[left] = data[right];
                data[right] = temp;
            }
        }
        quickSort(data, start, right - 1);
        quickSort(data,right+1,end);
    }

    private static int[] iniQuickSort() {
        System.out.println("[퀵소트 시작했습니다.]");
        int[] result = Arrays.stream(data).toArray();
        quickSort(result, 0, result.length - 1);

        return result;
    }
}

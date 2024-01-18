package leetcode.array;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @see <a href='https://leetcode.com/problems/array-partition/'>문제 링크</a>
 *
 */
public class ArrayPairSum561 {
    /**
     * pair중 가장 적은 녀석은 오름차순으로 정렬후에
     * 홀수 번째 녀석을 더해주면 된다.
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length-1; i+=2) {
            result += nums[i];
        }
        return result;
    }
    public int arrayPairSumStream(int[] nums) {
        Arrays.sort(nums);
        return IntStream.range(0, nums.length)
                .filter(i -> i % 2 == 0)
                .map(i -> nums[i])
                .sum();
    }
    public int arrayPairSumStream2(int[] nums) {
        AtomicInteger answer = new AtomicInteger(0);
        AtomicInteger index = new AtomicInteger(0);

        Arrays.stream(nums)
                .sorted()
                .forEach(num -> {
                    if (index.getAndIncrement() %2 ==0)
                        answer.addAndGet(num);
                });
        return answer.get();
    }
}

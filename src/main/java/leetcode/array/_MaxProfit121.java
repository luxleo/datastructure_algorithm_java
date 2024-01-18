package leetcode.array;

/**
 * @see <a href='https://leetcode.com/problems/best-time-to-buy-and-sell-stock/'>문제 링크</a>
 */
public class _MaxProfit121 {
    /**
     * O(n**2) can not pass
     * @param prices
     * @return
     */
    public int maxProfitBruteForce(int[] prices) {
        int answer = 0;
        int size = prices.length;
        for (int i = 0; i < size-1; i++) {
            int best = prices[i];
            for (int j = i+1; j < size; j++) {
                best = Math.max(best, prices[j]);
            }
            if (best > prices[i]) {
                answer = Math.max(answer, best - prices[i]);
            }
        }
        return answer;
    }

    /**
     * O(n)
     * tip : visualize situation by drawing graph, then could get hint
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = prices[0];
        for (int price :
                prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }
}

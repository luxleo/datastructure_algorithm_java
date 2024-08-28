package leetcode.greedy.problem;

public class BestTimetoBuyandSellStockII_112 {
    public int maxProfit(int[] prices) {
        int answer = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int gap = prices[i + 1] - prices[i];
            if(gap > 0) answer += gap;
        }
        return answer;
    }
}

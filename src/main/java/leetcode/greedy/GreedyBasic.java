package leetcode.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 24.08.28
 * 그리디는 다이내믹 프로그래밍과 비교하여 자주 사용된다.
 * 그리디: 지역 최적해
 * 다이내믹 프로그래밍 : 전역 최적해
 *
 * 대표적인 문제에서 그리디 알고리즘의 적용 (knapsack problem)
 * knapsack 에 담는 물건이 분할 가능하면 greedy 적용가능,
 * 분할 불가능 하면 다이내믹 프로그램으로 풀어야한다.
 *
 * 그리디는 우선순위 큐와 많이 쓰인다. -> leet code 406 참조
 */
public class GreedyBasic {
    static private class Cargo {
        private final int price;
        private final int weight;
        private final float unitPrice; // 단위 무게당 단가

        public Cargo(final int price, final int weight) {
            this.price = price;
            this.weight = weight;
            this.unitPrice = (float) price / weight;
        }
    }

    private static float solveKnapsackProblem(final int limitWeight, final List<Cargo> cargoList) {
        int localLimitWeight = limitWeight;
        float resultPrice = 0;
        // 단가 역순으로 정렬
        cargoList.sort(Comparator.comparingDouble(e-> e.unitPrice * -1));
        for (Cargo c : cargoList) {
            if (localLimitWeight - c.weight > 0) {
                localLimitWeight -= c.weight;
                resultPrice += c.price;
            } else {
                // 남은 무게로 계산 해준다
                resultPrice += c.unitPrice * localLimitWeight;
                break;
            }
        }
        return resultPrice;
    }
    public static void main(String[] args) {
        List<Cargo> cargoList = new ArrayList<>();
        cargoList.add(new Cargo(4, 12));
        cargoList.add(new Cargo(2,1 ));
        cargoList.add(new Cargo(10,4 ));
        cargoList.add(new Cargo(1,1 ));
        cargoList.add(new Cargo(2,2 ));

        float answer = solveKnapsackProblem(15, cargoList);
        System.out.println("answer = " + answer);
    }
}


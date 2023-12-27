package algorithm.programmers.implement;

import java.util.*;
import java.util.stream.Collectors;

public class DiceGame3 {
    public int solution(int a, int b, int c, int d) {
        int answer = 0;

        Set<Integer> keySet = new HashSet<>();
        Map<Integer, Integer> keyMap = new HashMap<>();

        enrollKey(a, keySet,keyMap);
        enrollKey(b, keySet,keyMap);
        enrollKey(c, keySet,keyMap);
        enrollKey(d, keySet,keyMap);

        return answer=doJob(keySet,keyMap);
    }

    /**
     * set,map에 a,b,c,d로 주어지는 키를 각각 할당한다.
     * set에 a,b,c,d각 숫자를 삽입하면 중복 없이 삽입할 수 있다.
     * map에는 key가 a,b,c,d이고 키의 값은 나타난 횟수이다.
     * @param key
     * @param set
     * @param map
     */
    public void enrollKey(int key, Set<Integer> set , Map<Integer, Integer> map) {
        set.add(key);
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    /**
     * 문제 구현 요구 사항을 구현한다.
     * @param set
     * @param map
     * @return
     */
    public int doJob(Set<Integer> set , Map<Integer, Integer> map) {
        int keySize = set.size();
        if(keySize == 1)
            // 키 네 개가 모두 같은 경우
            return 1111 * set.stream().findAny().get();
        else if (keySize == 2) {
            //키값이 두 개인 경우
            Integer randomKey = set.stream().findAny().get();
            Integer randomVal = map.get(randomKey);

            if(randomVal == 2){
                //주사위가 두 개씩 같은 값이 나온 경우
                List<Integer> sorted = set.stream()
                        .sorted()
                        .collect(Collectors.toList());
                Integer min = sorted.get(0);
                Integer max = sorted.get(1);
                return (max + min) * (max - min);
            }else {
                //세 주사위에서 나온 숫자가 p로 같고 나머지 다른 주사위에서 나온 숫자가 q인 경우
                List<Integer> sorted = map.entrySet().stream()
                        .sorted((e1, e2) -> e1.getValue() - e2.getValue())
                        .map(e -> e.getKey())
                        .collect(Collectors.toList());
                Integer min = sorted.get(0);
                Integer max = sorted.get(1);
                return (int) Math.pow((10 * max + min), 2);
            }
        } else if (keySize == 3) {
            //어느 두 주사위에서 나온 숫자가 p로 같고 나머지 두 주사위에서 나온 숫자가 각각 p와 다른 q, r인 경우
            List<Integer> sorted = map.entrySet().stream()
                    .sorted((e1, e2) -> e1.getValue() - e2.getValue())
                    .map(e -> e.getKey())
                    .collect(Collectors.toList());
            return sorted.get(0) * sorted.get(1);
        }else
            //네 주사위에 적힌 숫자가 모두 다른 경우.
            return map.entrySet().stream()
                    .sorted((e1, e2) -> e1.getValue() - e2.getValue())
                    .map(e -> e.getKey())
                    .collect(Collectors.toList())
                    .get(0);
    }
}

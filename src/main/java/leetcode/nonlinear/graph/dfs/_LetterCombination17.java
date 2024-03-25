package leetcode.nonlinear.graph.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _LetterCombination17 {
    List<String> answer = new ArrayList<>();
    Map<Character, List<Character>> table = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        table.put('0', List.of());
        table.put('1', List.of());
        table.put('2', List.of('a', 'b', 'c'));
        table.put('3', List.of('d', 'e', 'f'));
        table.put('4', List.of('g', 'h', 'i'));
        table.put('5', List.of('j', 'k', 'l'));
        table.put('6', List.of('m', 'n', 'o'));
        table.put('7', List.of('p', 'q', 'r','s'));
        table.put('8', List.of('t', 'u', 'v'));
        table.put('9', List.of('w', 'x', 'y', 'z'));

        dfs(0,new StringBuilder(),digits);
        return answer;
    }

    public void dfs(int index,StringBuilder sb ,String digits) {
        if (index == digits.length()) {
            if(!sb.isEmpty())
                answer.add(sb.toString());
            return;
        }
        for (Character c :
                table.get(digits.charAt(index))) {
            sb.append(c);
            dfs(index + 1, sb, digits);
            sb.setLength(sb.length()-1);
        }
    }
}

package leetcode.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @see <a href='https://leetcode.com/problems/reorder-data-in-log-files/description/'>문제 링크</a>
 * 커스텀 comparator를 작성하는게 요지이다.
 * String.matches에 정규표현식을 사용하는 방법도 배웠다.
 * List -> array
 * List.addAll(), Stream.concat()
 */
public class _Sort937 {
    public String[] reorderLogFiles(String[] logs) {
        List<String> digits = new ArrayList<>();
        List<String> letters = new ArrayList<>();

        for (String log : logs) {
            isdigit(log, digits,letters);
        }
        return Stream.concat(
                letters.stream()
                        .sorted((s1, s2) -> {
                            String[] split1 = s1.split(" ");
                            String[] split2 = s2.split(" ");

                            int result = s1.substring(s1.indexOf(" ") + 1).compareTo(
                                    s2.substring(s2.indexOf(" ") + 1)
                            );
                            // If the second word comparison is equal, compare by the first word
                            if (result == 0) {
                                result = split1[0].compareTo(split2[0]);
                            }

                            return result;
                        }), digits.stream()
        ).toArray(String[]::new);
    }

    public void isdigit(String data,List<String> digits,List<String> letters) {
        String subData = data.substring(data.indexOf(" ") + 1);
        if(subData.matches(".*\\d+.*"))
            digits.add(data);
        else letters.add(data);
    }
}

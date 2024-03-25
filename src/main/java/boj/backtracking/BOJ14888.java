package boj.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888 {
    int[] opers = new int[4];
    int[] data;
    int MAX, MIN,size;
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        size = Integer.parseInt(st.nextToken());
        data = new int[size];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            opers[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 1);
        System.out.println(MAX);
        System.out.println(MIN);
    }

    public void dfs(int acc, int index) {
        if (index == size) {
            MAX = Math.max(MAX, acc);
            MIN = Math.min(MIN, acc);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int oper = i % 4;
            if (opers[oper] > 0) {
                opers[oper] -= 1;
                switch (oper) {
                    case 0:
                        dfs(acc + data[index], index + 1);
                        break;
                    case 1:
                        dfs(acc - data[index], index + 1);
                        break;
                    case 2:
                        dfs(acc * data[index], index + 1);
                        break;
                    case 3:
                        dfs(acc / data[index], index + 1);
                        break;
                }
                opers[oper] += 1;
            }
        }
    }
}

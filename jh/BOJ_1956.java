package march;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 운동 / 골드4 / 2시간
 * https://www.acmicpc.net/problem/1956
 */

public class BOJ_1956 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int[][] route = new int[v][v];
        for(int i = 0; i < v; i++)
            Arrays.fill(route[i], Integer.MAX_VALUE);
        int v1, v2, depth;
        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(in.readLine());
            v1 = Integer.parseInt(st.nextToken()) - 1;
            v2 = Integer.parseInt(st.nextToken()) - 1;
            depth = Integer.parseInt(st.nextToken());
            route[v1][v2] = depth;
        }
        for(int i = 0; i < v; i++) {
            for(int start = 0; start < v; start++) {
                if(start == i || route[start][i] == Integer.MAX_VALUE)
                    continue;
                for(int end = 0; end < v; end++) {
                    if(end == i || route[i][end] == Integer.MAX_VALUE)
                        continue;
                    route[start][end] = Math.min(route[start][end], route[start][i] + route[i][end]);
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < v; i++) {
            if(result > route[i][i])
                result = route[i][i];
        }
        for(int[] a: route)
            System.out.println(Arrays.toString(a));
        result = result == Integer.MAX_VALUE? -1: result;
        System.out.println(result);
    }
}

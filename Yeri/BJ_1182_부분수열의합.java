package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 부분수열의합 / 실버2 / 20분 / 3월 1일
 * https://www.acmicpc.net/problem/1182
 */
public class BJ_1182_부분수열의합 {
    static int answer = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0, N, S);
        answer = S == 0 ? answer - 1 : answer;
        System.out.println(answer);
    }

    private static void dfs(int index, int sum, int N, int S) {
        if (index == N) {
            if (sum == S)
                answer++;
            return;
        }
        dfs(index + 1, sum + arr[index], N, S);
        dfs(index + 1, sum, N, S);
    }
}

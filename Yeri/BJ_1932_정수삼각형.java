package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정수 삼각형 / 실버1 /
 * https://www.acmicpc.net/problem/1932
 */
public class BJ_1932_정수삼각형 {
    static int[][] arr;
    static Integer[][] dp;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dp = new Integer[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = arr[n - 1][i];
        }

        System.out.println(findMax(0,0));
    }

    public static int findMax(int line, int index) {
        if (line == n - 1) {
            return dp[line][index];
        }

        if (dp[line][index] == null) {
            dp[line][index] = Math.max(findMax(line+1,index),findMax(line+1,index+1))+arr[line][index];
        }
        return dp[line][index];
    }
}

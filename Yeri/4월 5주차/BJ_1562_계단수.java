import com.sun.jdi.IntegerType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


/**
 * 계단 수 / 골드1 / 풀이 참조 / 5월 11일
 */

public class Main {
    static int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(br.readLine());
        long[][][] dp = new long[n + 1][11][1 << 10];

        for (int i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1024; k++) {
                    int bit = k | (1 << j);
                    if (j == 0) {
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j + 1][k]) % MOD;
                    } else if (j == 9) {
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j - 1][k]) % MOD;
                    } else {
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j + 1][k] + dp[i - 1][j - 1][k]) % MOD;
                    }
                }
            }
        }

        long answer = 0;
        for (int i = 0; i < 10; i++) {
            answer = (answer + dp[n][i][1023]) % MOD;
        }
        System.out.println(answer);
    }
}

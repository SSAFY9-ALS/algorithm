package yeri.algorithm0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 곱셈 / 실버1 / 50분
 * https://www.acmicpc.net/problem/1629
 */
public class BJ_1629_곱셈 {
    static int m1, m2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
//        m1 = A % C;
//        m2 = ((A % C) * (A % C)) % C;

        System.out.println(modular(A, B, C));
    }

    private static long modular(int A, int B, int C) {
        if (A==1 || B == 1) {
            return A%C;
        } else {
            long temp = modular(A, B / 2, C);
            if (B % 2 == 0) {
                return (temp * temp) % C;
            } else {
                return (A * ((temp * temp) % C)) % C;
            }
        }
    }
}

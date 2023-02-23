package yeri.algorithm0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 문자열 게임 2 / 골드 5 / 30분
 * https://www.acmicpc.net/problem/20437
 */
public class BJ_20437_문자열게임2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            String str = br.readLine();
            int[] count = new int[26];
            ArrayList[] alpha = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                alpha[i] = new ArrayList<Integer>();
            }
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int K = Integer.parseInt(br.readLine());
            for (int i = 0; i < str.length(); i++) {
                int al = str.charAt(i) - 'a';
                count[al]++;
                alpha[al].add(i);   //인덱스 저장
                if (count[al] == K) {
                    int temp = i - (int) alpha[al].get(0) + 1;
                    if (min > temp)
                        min = temp;
                    if (max < temp)
                        max = temp;
                } else if (count[al] > K) {
                    alpha[al].remove(0);
                    int temp = i - (int) alpha[al].get(0) + 1;
                    if (min > temp)
                        min = temp;
                    if (max < temp)
                        max = temp;
                }
            }

            if (min == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(min + " " + max);
            }
        }
    }
}

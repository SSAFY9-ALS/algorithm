package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 동전2 / 골드5 / 2시간 + a
 * https://www.acmicpc.net/problem/2294
 *
 */
public class BJ_2294_동전2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n : 동전의 종류 , k : 가치의 합
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 동전 배열
        int[] coins = new int[k+1];
        
        for (int i = 1; i < k+1; i++) {
			coins[i] = 100001;
		}
        
//        -- debug
//        System.out.println(Arrays.toString(coins));

        for (int i = 0; i < n; i++) {            
        	int coin = Integer.parseInt(br.readLine());

            int mod = k / coin;
//            -- debug
//            System.out.println(mod);
            for (int j = 1; j <= mod; j++) {
                // 기존에 있던 코인 개수보다 작으면 값 갱신
                if(j < coins[coin*j]) coins[coin*j] = j;
            }
            
//            -- debug
//            System.out.println(Arrays.toString(coins));
        }
        
        Arrays.sort(coins);
        
        for (int i = 1; i <= k/2+1; i++) {
			coins[k] = Math.min(coins[k], coins[i] + coins[k-i]);
		}
        
        if(coins[k] == 100001) {
        	System.out.println(-1);
        } else {
        	// 최소 동전 개수 찾기
        	System.out.println(coins[k]);	
        }

    }
}

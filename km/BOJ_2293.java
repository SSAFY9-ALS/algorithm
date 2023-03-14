/**
 * 동전 1 / 골드 5 / 50분
 * https://www.acmicpc.net/problem/2293
 */
package baekjoon;

import java.io.*;
import static java.lang.Integer.parseInt;

public class BOJ_2293 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		
		int n = parseInt(tmp[0]);
		int k = parseInt(tmp[1]);
		
		int[] coin = new int[n];
		int[] dp = new int[k+1];
		
		for(int i=0;i<n;i++) {
			coin[i] = parseInt(br.readLine());
		}
		
		dp[0]=1;
		
		for(int i=0;i<n;i++) {
			for(int j=1;j<=k;j++) {
				if(j>=coin[i]) {
					dp[j]+=dp[j-coin[i]];
				}
			}
		}
		
		System.out.println(dp[k]);
	}

}

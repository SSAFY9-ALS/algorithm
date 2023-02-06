/**
 * 타일 채우기 / 골드 4 / 60분
 */
package algorithm_with_java.dynamic_programming;

import java.io.*;
import java.util.*;

public class BOJ_2133 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int num = input.nextInt();
		int[] dp = new int[31];
		
		// 3*홀수인 경우 절대로 채울 수 없음 크기가 2인 타일로는 채울 수 없음
		
		dp[2] = 3;
		
		for(int i=4;i<=30;i+=2) {
			dp[i] = dp[i-2] * dp[2];
			for(int j=i-4;j>=2;j-=2) {
				dp[i] += (dp[j]*2);
			}
			dp[i] += 2;
		}
		System.out.println(dp[num]);
	}

}


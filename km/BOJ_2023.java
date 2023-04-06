/**
 * 신기한 소수 / 골드 5 / 60분
 * https://www.acmicpc.net/problem/2023
 */

package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_2023 {
	
	static int n;
	static StringBuilder sb = new StringBuilder();
	static String[] mid = {"1","3","7","9"}; // 중간은 무조건 이것만
	static String[] start = {"2","3","5","7"}; // 시작은 무조건 여기서
	
	static void makePrime(String num, int len) {
		if(len==n) {
			sb.append(num).append("\n");
		}
		
		for(int i=0;i<mid.length;i++) {
			String nextPrime = num+mid[i];
			int check = Integer.valueOf(nextPrime);
			// 소수인지 판별 -> 에라토스테네스의 체 사용
			if(checkPrime(check)) makePrime(nextPrime,len+1);
		}
	}
	static boolean checkPrime(int num) {
		for(int i=2;i<=num/2;i++) {
			if(num%i==0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		for(int i=0;i<start.length;i++) {
			makePrime(start[i], 1);
		}
		
		System.out.println(sb);
	}

}

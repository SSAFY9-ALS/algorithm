package net.acmicpc;

import java.util.*;
import java.io.*;

/**
 * 동전 1 / 골드 5 / 35분
 * @author 민정인
 * https://www.acmicpc.net/problem/2293
 */

public class BOJ_2293 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coin = new int[n];
		int[] money = new int[k + 1];
		for(int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		money[0] = 1;
		for(int i = 0; i < n; i++) {
			for(int j = 1; j <= k; j++) {
				if(j >= coin[i]) {
					money[j] += money[j - coin[i]];
				}
			}
		}
		System.out.println(money[k]);
	}
}
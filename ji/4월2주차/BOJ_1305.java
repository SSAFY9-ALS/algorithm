package net.acmicpc;

import java.io.*;
import java.util.*;
/**
 * 광고 / 플래티넘 4 / 105분
 * @author 민정인
 * https://www.acmicpc.net/problem/1305
 */
public class BOJ_1305 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		char[] arr = new char[n];
		for(int i = 0; i < n; i++) {
			arr[i] = s.charAt(i);
		}
		int[] p = new int[n];	// 접두어와 접미어가 동일한 길이 저장
		for(int i = 1, j = 0; i < n; i++) {
			while(j > 0 && arr[i] != arr[j]) j = p[j - 1];
			if(arr[i] == arr[j]) p[i] = ++j;
			else p[i] = 0;
		}
		System.out.println(n - p[n-1]);	// 마지막 p 배열의 값은 전체 문자열에서 접두어가 반복되는 길이이므로 전체 길이에서 해당 값을 뺀 부분이 반복됨으로 정의할 수 있다.
	}
}

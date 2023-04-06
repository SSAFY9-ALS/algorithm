package Baekjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 수찾기 / 실버 4 / 30분
 * https://www.acmicpc.net/problem/1920
 *
 */

public class BJ_1920_수찾기 {
	
	static int N;
	static int[] A;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		
		int M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int number = Integer.parseInt(st.nextToken());
			
			int start = 0;
			int end = N - 1;
			int result = 0;
			while(start <= end) {
				int mid = (start + end) / 2;
				
				// 중앙값이 number보다 작으면 오른쪽 탐색
				if(A[mid] < number) {
					start = mid + 1;
				}
				// 중앙값이 number보다 크면 왼쪽 탐색
				else if(A[mid] > number) {
					end = mid - 1;
				}
				// 찾으면 break;
				else {
					result = 1;
					break;
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
}

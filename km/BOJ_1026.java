/**
 * 보물 / 실버 4 / 15분 
 * https://www.acmicpc.net/problem/1026
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_1026 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = parseInt(br.readLine()); // 배열의 크기
		
		int[] a = new int[n];
		int[] b = new int[n];
		int answer = 0;
		
		String[] atmp = br.readLine().split(" ");
		String[] btmp = br.readLine().split(" ");
		
		for(int i=0;i<n;i++) { // 배열 a b 받기
			a[i] = parseInt(atmp[i]);
			b[i] = parseInt(btmp[i]);
		}

		
		Arrays.sort(a); // 오름차순 정렬
		Arrays.sort(b); // 내림차순 정렬
		
		for(int i=0;i<n;i++) {
			answer += a[i] *b[n-1-i]; // 가장 작은값과 가장 큰값을 곱해줌
		}
		
		System.out.println(answer);
	}

}

/**
 * 행복 유치원 / 골드 5 / 70분
 * https://www.acmicpc.net/problem/13164
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_13164 {

	static int N;
	static int K;
	static int[] arr;
	static int[] diff;
	static int answer;
	

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		
		N = parseInt(tmp[0]);
		K = parseInt(tmp[1]);
		arr = new int[N]; 
		diff = new int[N-1]; // 차이는 N-1개 만큼 존재
		
		tmp = br.readLine().split(" ");
		
		for(int i=0;i<N;i++) {
			arr[i]= parseInt(tmp[i]);
		}
		
		for(int i=0;i<N-1;i++) {
			diff[i] = arr[i+1]-arr[i]; // 차이 구하기
		}
		
		
		Arrays.sort(diff);
		
		for(int i=0;i<N-K;i++) {
			answer += diff[i];
		}
		
		System.out.println(answer);
	}

}

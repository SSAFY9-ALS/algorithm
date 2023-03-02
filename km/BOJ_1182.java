/** 
 * 부분 수열의 합 / 실버 2 / 35분
 * https://www.acmicpc.net/problem/1182
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_1182 {

	static int[] arr;
	static int N; // 정수의 개수
	static int S; // 정수의 합
	static int answer=0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		N = parseInt(tmp[0]);
		S = parseInt(tmp[1]);
		
		arr = new int[N];
		
		tmp = br.readLine().split(" ");
		for(int i=0;i<N;i++) {
			arr[i] = parseInt(tmp[i]);
		}
		Arrays.sort(arr);
		dfs(0,0);
		if(S==0) answer-=1; // 0이면 아무것도 선택하지 않은 경우 가 포함되서 하나 빼주어야함!!!!
		System.out.println(answer);
	}
	
	static void dfs(int count, int sum) {
		if(count == N) {
			if(sum == S) {
				answer++;
			}
			return;
		}
		dfs(count+1, sum+arr[count]); // arr의 배열의 count 인덱스값 포함하고 dfs
		dfs(count+1, sum); // arr의 배열의 count 인덱스값 포함하지 않고 dfs
	}
}

/**
 * 수들의 합 2 / 실버 4 / 35분
 * https://www.acmicpc.net/problem/2003
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_2003 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		int n = parseInt(tmp[0]); // 수열의 원소의 개수
		int m = parseInt(tmp[1]); // 구해야하는 합
		
		int[] arr = new int[n];
		tmp = br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr[i] = parseInt(tmp[i]);
		}
		int answer = 0;
		int start = 0;
		int end = 0;
		int sum = 0;

		while(end <= n) {
			if(sum==m) { // 누적합==m이면 개수 증가, sum에서 arr[start]값 빼고, start값 1증가
				answer++;
				sum-=arr[start++];
			}
			else if (sum<m) { // 누적합<m이면 sum에 arr[end]더하고 end값 증가
				if(end==n) 
					break; // 누적합<m이일때 end가 이미 마지막원소이면 누적합이 더 커질수 없음
				sum+=arr[end++];
			}
			else { // 누적합>m이면, sum에서 arr[start]값 빼고, start값 1증가
				sum-=arr[start++];
			}
			
		}
		System.out.println(answer);
	}

}

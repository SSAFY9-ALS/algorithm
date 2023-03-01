/**
 *  소수의 연속합 / 골드 3 / 25분
 *  https://www.acmicpc.net/problem/1644
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_1644 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		boolean[] arr = new boolean[n+1]; // 소수 담을 배열 false면 소수
		
		arr[1] = true;
		
		for(int i=2;i<n+1;i++) {
			for(int j=i*2;j<n+1;j+=i) {
				arr[j] = true;
			}
		}
		ArrayList<Integer> prime = new ArrayList<>();
		
		for(int i=2;i<n+1;i++) {
			if(!arr[i]) prime.add(i); // 소수만 빠로 뽑은 배열
		}
		
		int sum = 0;
		int start = 0; // 시작 인덱스
		int end = 0; 
		int answer = 0;
		
		while(end<=n+1) {
			if(sum==n) { // 누적합 = n이면 개수 증가, start 인덱스 1 증가
				answer++;
				sum-=prime.get(start); // start 값 제외
				start++;
				
			}
			else if(sum < n) {
				if(end==prime.size()) break; // end가 마지막 까지 갔지만 sum<n 이면 더이상 n을 만들 수없음
				
				sum+=prime.get(end); // end값 추가 후 증가
				end++;
			}
			else { // sum > n
				sum-=prime.get(start);  // start 제외 후 증가
				start++;
			}
		}
		
		System.out.println(answer);
	}

}

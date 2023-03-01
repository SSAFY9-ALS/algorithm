package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 부분수열의 합 / 실버2 / 15분
 * https://www.acmicpc.net/problem/1182
 */

public class BOJ_1182 {
	static int n, s, count;
	static int[] nums;
	
	// 조합을 구하는 메서드
	static void findCombination(int r, int idx, int cnt, int sum) {
		if(r == 0) { // r개만큼 뽑았을 때
			if(sum == s) // 그 합이 s와 같으면
				count++; // count 1 증가
			return; // 종료
		}
		for(int i = idx; i < n; i++) // 조합이기 때문에 이전 값 이후부터 탐색
			findCombination(r-1, i+1, cnt+1, sum+nums[i]); // sum에 더해주고 다시 함수 호출
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken()); // n 입력
		s = Integer.parseInt(st.nextToken()); // s 입력
		nums = new int[n];
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++)
			nums[i] = Integer.parseInt(st.nextToken()); // 수열 원소 입력
		for(int r = 1; r <= n; r++) // 크기가 양수인 부분 수열 모두 탐색
			findCombination(r, 0, 0, 0); // 조함을 구하는 함수 호출
		System.out.println(count); // 결과 출력
	}
}

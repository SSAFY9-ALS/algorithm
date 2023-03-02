package algorithm_mar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 퇴사 / 실버3 / 50분
 * https://www.acmicpc.net/problem/14501
 */

public class BOJ_14501 {
	static int max = Integer.MIN_VALUE;
	static int[][] plan;

	// 브루트포스 알고리즘
	static void findMaxValue(int idx, int sum) {
		if(idx >= plan.length) { // 날짜 인덱스가 최대를 넘어갈 경우
			if(sum > max) // 지금까지의 합과 최댓값을 비교
				max = sum; // 합이 크다면 값 갱신
			return; // 종료
		}
		findMaxValue(idx + plan[idx][0], sum + plan[idx][1]); // 현재 날짜에 상담을 진행할 경우
		findMaxValue(idx + 1, sum); // 현재 날짜에 상담을 진행하지 않을 경우
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(in.readLine()); // n 입력
		int t, p;
		plan = new int[n][]; // 상담 계획을 저장할 배열
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			t = Integer.parseInt(st.nextToken()); // t 입력
			p = Integer.parseInt(st.nextToken()); // p 입력
			if(i + t <= n) // 상담 기간이 퇴사 날짜를 초과하지 않을 때
				plan[i] = new int[] {t, p}; // 값을 그대로 넣어줌
			else // 초과할 때
				plan[i] = new int[] {i+1, 0}; // t는 다음 날짜, 비용은 0으로 넣어줌
		}
		findMaxValue(0, 0); // 브루트포스 수행 함수 호출
		System.out.println(max); // 결과 출력
	}
}

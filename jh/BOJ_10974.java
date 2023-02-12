package ssafy;

import java.util.Scanner;

/**
 * 모든 순열 / 실버3 / 10분
 * https://www.acmicpc.net/problem/10974
 */

public class BOJ_10974 {
	static void findPermutation(int n, int r, boolean[] visited, String sen) {
		if(r == 0) // n개를 모두 뽑았다면
			System.out.println(sen); // 선택된 순열 출력
		else {
			for(int i = 0; i < n; i++) { // 순열이기 때문에 항상 배열 전체를 탐색해야 함
				if(!visited[i]) { // 현재가 방문하지 않았다면
					visited[i] = true; // visited를 true로 바꾸고
					findPermutation(n, r-1, visited, sen + (i+1) + " "); // string에 현재 위치를 추가하고, 다시 함수 호출
					visited[i] = false; // visited를 다시 false로 바꿔 다른 값을 받을 수 있게 함
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // n 입력
		findPermutation(n, n, new boolean[n], ""); // 조합을 구하는 함수 호출
	}
}

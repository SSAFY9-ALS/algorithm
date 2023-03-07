package algorithm_mar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 야구 / 골드4 / 1시간 20분
 * https://www.acmicpc.net/problem/17281
 */

public class BOJ_17821 {
	static int n, max = Integer.MIN_VALUE;
	static int[] permutation = new int[9];
	static boolean[] visited = new boolean[9];
	static int[][] player;
	
	static void runGame(int inning, int start, int score) {
		if(inning == n) { // 이닝이 종료됐을 때
			if(score > max) // 현재까지의 최댓값보다 스코어가 크면
				max = score; // 값 갱신
			return; // 종료
		}
		// 경기에 필요한 변수들
		int out = 0, now = start, p;
		boolean one = false, two = false, three = false;
		
		while(out != 3) { // 3 아웃이 아닐 때
			p = permutation[now]; // 현재 타석에 올라온 타자
			
			// 홈런을 쳤을 때
			if(player[inning][p] == 4) {
				if(three) {
					score++;
					three = false;
				}
				if(two) {
					score++;
					two = false;
				}
				if(one) {
					score++;
					one = false;
				}
				score++;
			}
			// 3루타를 쳤을 때
			else if(player[inning][p] == 3) {
				if(three) {
					score++;
					three = false;
				}
				if(two) {
					score++;
					two = false;
				}
				if(one) {
					score++;
					one = false;
				}
				three = true;
			}
			// 2루타를 쳤을 때
			else if(player[inning][p] == 2) {
				if(three) {
					score++;
					three= false;
				}
				if(two) {
					score++;
					two = false;
				}
				if(one) {
					three= true;
					one = false;
				}
				two = true;
			}
			// 안타를 쳤을 때
			else if(player[inning][p] == 1) {
				if(three) {
					score++;
					three = false;
				}
				if(two) {
					three = true;
					two = false;
				}
				if(one) {
					two = true;
					one = false;
				}
				one = true;
			}
			// 아웃일 때
			else
				out++;
			
			now = now == 8? 0: now + 1; // 다음 타석을 위한 now 값 갱신
		}
		runGame(inning + 1, now, score); // 다음 이닝을 위한 호출
	}
	
	static void findPermutation(int r, int idx) {
		if(r == 0) { // 8명을 다 뽑았을 때
			runGame(0, 0, 0); // 게임을 수행하는 함수 호출
			return; // 종료
		}
		for(int i = 1; i < 9; i++) { // 순열이기 때문에 전체 범위 탐색
			if(!visited[i]) { // 방문하지 않은 선수면
				visited[i] = true; // 방문 처리
				permutation[idx] = i; // 조합 배열에 담음
				if(idx == 2) // 4번 타자는 이미 고정되었기 때문에 5번으로 넘어가야 함
					findPermutation(r-1, idx+2); // 재귀 함수 호출
				else
					findPermutation(r-1, idx+1); // 재귀 함수 호출
				visited[i] = false; // 다음 탐색을 위해 비방문 처리
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(in.readLine()); // n 입력
		player = new int[n][9]; // 타자의 이닝별 결과를 담을 배열
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < 9; j++)
				player[i][j] = Integer.parseInt(st.nextToken()); // 타자의 결과 입력
		}
		permutation[3] = 0; // 1번 선수는 4번 타자로 고정됨
		visited[0] = true; // 1번 방문 처리
		findPermutation(8, 0); // 순열을 구하는 함수 호출
		System.out.println(max); // 결과 출력
	}
}

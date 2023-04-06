package algorithm_mar;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 테트로미노 / 골드4 / 1시간 20분
 * https://www.acmicpc.net/problem/14500
 */

public class BOJ_14500 {
	static int n, m, max = Integer.MIN_VALUE;
	static int[][] board;
	static Point[] selected = new Point[4];
	static boolean[][] visited;
	
	// 좌표가 보드 안의 범위인지 판별하는 메서드
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < n && y >= 0 && y < m)
			return true;
		return false;
	}
	
	// 테트로미노를 만들어 최댓값을 구하는 메서드
	static void findMaxValue(int r, int moveX, int moveY, int sum) {
		if(r == 3) { // 4개를 뽑았을 때
			if(sum > max) // 합이 최댓값보다 크면
				max = sum; // 값 갱신
			return; // 종료
		}
		int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 네 방향
		int x, y, dx, dy;
		for(int i = 0; i <= r; i++) { // 테트로미노를 이루는 모든 좌표에 대해 상하좌우 탐색
			x = selected[i].x;
			y = selected[i].y;
			for(int k = 0; k < 4; k++) {
				dx = d[k][0];
				dy = d[k][1];
				if(isRange(x+dx, y+dy) && !visited[x+dx-moveX][y+dy-moveY]) { // board 안이고, 아직 방문하지 않은 좌표일 때
					visited[x+dx-moveX][y+dy-moveY] = true; // 방문 처리
					selected[r+1] = new Point(x+dx, y+dy); // 선택한 좌표를 selected 배열에 넣어줌
					findMaxValue(r+1, moveX, moveY, sum+board[x+dx][y+dy]); // 다시 함수 호출
					visited[x+dx-moveX][y+dy-moveY] = false; // 다음 탐색을 위해 비방문 처리
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken()); // n 입력
		m = Integer.parseInt(st.nextToken()); // m 입력
		board = new int[n][m]; // 종이의 각 칸에 쓰여 있는 숫자를 담는 배열
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < m; j++)
				board[i][j] = Integer.parseInt(st.nextToken()); // 숫자 입력
		}
		// 모든 점을 시작점으로 하는 테트로미노를 만듦
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				visited = new boolean[9][9]; // 방문 여부를 체킹할 배열 -> 시작점을 기준으로 4번 사방으로 나갈 수 있기 때문에 크기를 9x9로 지정
				visited[4][4] = true; // 시작점 방문 처리
				selected[0] = new Point(i, j); // 시작점을 테트로미노를 이루는 좌표를 가지는 배열에 넣어줌
				findMaxValue(0, i-4, j-4, board[i][j]); // 테트로미노를 만드는 함수 호출
			}
		}
		System.out.println(max); // 결과 출력
	}
}

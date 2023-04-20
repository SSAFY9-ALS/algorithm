package april;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 사다리 조작 / 골드3 / 1시간
 * https://www.acmicpc.net/problem/15684
 */

public class BOJ_15684 {
	static int h, n;
	static int[][] map;
	static int result = -1;
	static boolean flag = false;
	
	static boolean calc() {
		int cnt, nowY;
		for(int j = 1; j <= n; j++) { // 각 세로선을 탐색
			cnt = 0;
			nowY = j;
			for(int i = 1; i <= h; i++) { // 모든 가로선 위치를 탐색
				cnt += map[i][nowY];
				if(map[i][nowY] == 1)
					nowY++;
				else if(map[i][nowY] == -1)
					nowY--;
			}
			if(cnt != 0) // 사타리타기 결과 자신의 열에 도착하지 않는다면
				return false; // false 리턴
		}
		return true; // 모든 탐색이 종료되면 true 리턴
	}
	
	static void findMinValue(int goal, int count) {
		if(goal == count) {
			if(calc()) { // 사타리타기 수행 결과가 true면
				flag = true; // flag 변경
				result = goal; // 결과값 변경
			}
			return; // 종료
		}
		for(int i = 1; i <= h; i++) { // 모든 가로선 위치를 탐색
			for(int j = 1; j < n; j++) { // 모든 세로선 위치를 탐색
				if(map[i][j] != 0 || map[i][j+1] > 0) // 사타리를 놓을 수 없는 조건이라면
					continue; // 다음으로 넘어감
				// 현재 값에 사다리를 놓음
				map[i][j] = 1;
				map[i][j+1] = -1;
				findMinValue(goal, count+1); // 다음 depth 탐색
				if(flag) // 탐색 결과 flag가 true면
					return; // 종료
				// 사다리 원위치
				map[i][j] = 0;
				map[i][j+1] = 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h+1][n+1];
		
		int a, b;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			// 왼쪽 -> 오른쪽은 1을, 오른쪽 -> 왼쪽은 -1 값을 넣어줌
			map[a][b] = 1;
			map[a][b+1] = -1;
		}
		
		// 3번까지 탐색
		for(int i = 0; i <= 3; i++) {
			if(!flag) // 아직 결과를 구하지 못했다면
				findMinValue(i, 0); // 탐색
		}
		
		System.out.println(result); // 결과 출력
	}
}
package may;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 빙산 / 골드4 / 50분 / 5월 9일
 * https://www.acmicpc.net/problem/2573
 */

public class BOJ_2573 {
	static int n, m, total;
	static int[][] map;
	static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
	
	// 종료 조건 확인 메서드
	static boolean isFinished() {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];

		// 가장 처음 만나는 빙산의 위치를 찾음
		loop:
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] > 0) {
					visited[i][j] = true;
					queue.offer(new int[] {i, j});
					break loop;
				}
			}
		}
		
		// 찾은 빙산부터 상하좌우를 탐색해 연결된 덩어리를 구함
		int[] temp;
		int dx, dy, cnt = 0;
		while(!queue.isEmpty()) {
			temp = queue.poll();
			cnt++;
			for(int k = 0; k < 4; k++) {
				dx = temp[0] + d[k][0];
				dy = temp[1] + d[k][1];
				
				if(dx >= 0 && dx < n && dy >= 0 && dy < m && map[dx][dy] > 0 && !visited[dx][dy]) {
					visited[dx][dy] = true;
					queue.offer(new int[] {dx, dy});
				}
			}
		}
		
		if(cnt == total) // 구한 덩어리가 현재 남은 빙산의 개수와 같으면
			return true; // true 반환
		return false; // 아니면 false 반환
	}
	
	// 녹은 빙산을 찾는 메서드
	static boolean findIce() {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		boolean flag = false;
		int dx, dy, cnt;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 0)
					continue;
				
				cnt = 0;
				for(int k = 0; k < 4; k++) { // 빙산의 상하좌우 탐색
					dx = i + d[k][0];
					dy = j + d[k][1];
					
					if(dx >= 0 && dx < n && dy >= 0 && dy < m && map[dx][dy] == 0) // 주변 영역이 물이면
						cnt++; // 카운트 1 증가
				}
				
				if(cnt >= map[i][j]) { // 빙산이 녹아 바닷물이 되는 경우
					queue.offer(new int[] {i, j}); // 큐에 넣어줌
					total--; // 전체 개수 1 감소
					flag = true; // 종료 조건을 찾는 메서드 실행을 위한 플래그
				}
				else { // 아니면
					map[i][j] -= cnt; // 카운트만큼만 감소
				}
			}
		}
		
		// 큐에 있는 빙산을 모두 0으로 변경
		for(int[] p: queue)
			map[p[0]][p[1]] = 0;
		
		// 종료 조건을 실행할 수 있으면
		if(flag) {
			if(!isFinished()) // 메서드 실행
				return true; // 결과 반환
		}
		return false; // 결과 반환
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		// 입력값
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0)
					total++;
			}
		}
		
		// 종료 조건이 될 때까지 반복
		int time = 1;
		while(total > 0) {
			if(findIce())
				break;
			time++;
		}
		
		// 결과 출력
		if(total > 0)
			System.out.println(time);
		else
			System.out.println("0");
	}
}
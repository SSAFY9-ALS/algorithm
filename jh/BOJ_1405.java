package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 미친 로봇 / 골드4 / 1시간 10분 
 * https://www.acmicpc.net/problem/1405
 */

public class BOJ_1405 {
	static double[] direction = new double[4];
	static double result = 0.0;
	static boolean[][] map;
	
	// 좌표가 map 안인지 판별하는 함수
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < map.length && y >= 0 && y < map.length)
			return true;
		return false;
	}
	
	// 다음 위치로 로봇이 움직일 수 있는지 판별하는 함수
	static int[] canMove(int d, int x, int y) {
		switch(d) { // 방향에 따라
		case 0: // 동쪽
			if(isRange(x, y+1) && !map[x][y+1]) // 이동 위치가 map 안이고 아직 방문하지 않았으면
				return new int[] {x, y+1}; // 이동 위치 좌표 반환
			return null; // 움직일 수 없다면 null 반환
		case 1: // 서쪽
			if(isRange(x, y-1) && !map[x][y-1]) // 이동 위치가 map 안이고 아직 방문하지 않았으면
				return new int[] {x, y-1}; // 이동 위치 좌표 반환
			return null; // 움직일 수 없다면 null 반환
		case 2: // 남쪽
			if(isRange(x+1, y) && !map[x+1][y]) // 이동 위치가 map 안이고 아직 방문하지 않았으면
				return new int[] {x+1, y}; // 이동 위치 좌표 반환
			return null; // 움직일 수 없다면 null 반환
		default: // 북쪽
			if(isRange(x-1, y) && !map[x-1][y]) // 이동 위치가 map 안이고 아직 방문하지 않았으면
				return new int[] {x-1, y}; // 이동 위치 좌표 반환
			return null; // 움직일 수 없다면 null 반환
		}
	}
	
	// 로봇을 움직이는 메서드
	static void moveRobot(int r, int x, int y, double total) {
		if(r == 0) { // r번만큼 움직였을 때
			result += total; // 결과에 확률을 더해줌
			return; // 종료
		}
		int[] temp;
		for(int i = 0; i < 4; i++) { // 4방향을 탐색
			if(direction[i] != 0) { // 그 방향으로 갈 수 있을 때
				temp = canMove(i, x, y); // 움직일 수 있는지 확인
				if(temp != null) { // 움직일 수 있다면
					map[temp[0]][temp[1]] = true; // 다음 값을 방문 처리하고
					moveRobot(r-1, temp[0], temp[1], total * direction[i]); // 다시 함수 호출
					map[temp[0]][temp[1]] = false; // 다음 탐색을 위해 미방문 처리
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // n 입력
		map = new boolean[2*n+1][2*n+1]; // map의 최대 크기를 고려해 생성 -> (n, n)에서 시작해 한 방향으로만 n번 가는 것이 최대
		for(int i = 0; i < 4; i++) {
			direction[i] = Integer.parseInt(st.nextToken()) * 0.01; // 입력된 확률을 실수로 바꿔 저장
		}
		map[n][n] = true; // 시작 위치 방문 처리
		moveRobot(n, n, n, 1); // 로봇을 n번 움직이는 함수 호출
		System.out.println(result); // 결과 출력
	}
}

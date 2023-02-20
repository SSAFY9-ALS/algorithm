package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 드래곤 커브 / 골드4 / 2시간 30분
 * https://www.acmicpc.net/problem/15685
 */

public class BOJ_15685 {
	static boolean[][] map = new boolean[101][101]; // 드래곤 커브에 속하는 꼭짓점인지 판별하는 배열
	
	// 격자 범위 안에 있는지 판단하는 메서드
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < 101 && y >= 0 && y < 101) // 격좌 범위 안에 있으면
			return true; // true 반환
		return false; // 아니면 false 반환
	}
	
	// 드래곤 커브를 회전시키는 메서드
	static int[] rotatePoint(int[] line, int centerX, int centerY) {
		// 원점을 중심으로 좌표를 회전시키는 공식을 이용해 새로운 좌표를 구함
		// x' = x*cos - y*sin, y' = x*sin + y*cos
		// 이 공식은 시계 반대 방향으로 돌기 때문에 각도는 90도가 아닌 270도로 줘야 함
		int newStartX = line[1] + centerX - centerY;
		int newStartY = -line[0] + centerX + centerY;
		int newEndX = line[3] + centerX - centerY;
		int newEndY = -line[2] + centerX + centerY;
		if(isRange(newStartX, newStartY)) // 회전시킨 좌표가 격자 범위 안이라면
			map[newStartX][newStartY] = true; // true로 변경
		if(isRange(newEndX, newEndY)) // 회전시킨 좌표가 격자 범위 안이라면
			map[newEndX][newEndY] = true; // true로 변경
		return new int[] {newEndX, newEndY, newStartX, newStartY}; // 회전시킨 좌표 반환
	}
	
	// 시작 방향을 이용해 0세대 드래곤 커브 도착 좌표를 구하는 메서드
	static int[] findDirection(int x, int y, int d) {
		// 문제의 방향을 참고해 작성
		if(d == 0)
			return new int[] {x, y+1};
		else if(d == 1)
			return new int[] {x-1, y};
		else if(d == 2)
			return new int[] {x, y-1};
		else
			return new int[] {x+1, y};
	}
	
	// 드래곤 커브를 그리는 메서드
	static void findDragonCurv(int x, int y, int d, int g) {
		ArrayDeque<int[]> queue = new ArrayDeque<int[]>(); // 드래곤 커브 꼭짓점을 담는 queue
		ArrayDeque<int[]> rotate = new ArrayDeque<int[]>(); // 드래곤 커브 꼭짓점을 회전시킨 것을 담는 queue
		int[] add, temp;
		int startX = x; // 0세대 드래곤 커브 시작 x 좌표
		int startY = y; // 0세대 드래곤 커브 시작 y 좌표
		int[] end = findDirection(x, y, d); // 시작 방향을 이용해 0세대 드래곤 커브의 도착점 구함
		int endX = end[0]; // 0세대 드래곤 커브 도착 x 좌표
		int endY = end[1]; // 0세대 드래곤 커브 도착 x 좌표
		// 0세대 드래곤 커브 꼭짓점들 true로 변경
		map[startX][startY] = true;
		map[endX][endY] = true;
		queue.add(new int[] {startX, startY, endX, endY}); // 0세대 드래곤 커브 queue에 넣음
		for(int c = 0; c < g; c++) { // 드래콘 커브 세대만큼 반복
			for(int i = 0; i < queue.size(); i++) { // 현재 있는 모든 선분들에 대해서 반복
				add = queue.removeLast(); // 가장 마지막에 있는 선분이 가장 먼저 회전
				temp = rotatePoint(add, endX, endY); // 회전시킴
				queue.addFirst(add); // queue에서 빼냈던 선분을 다시 추가
				rotate.add(temp); // 회전시킨 선분을 따로 만들어둔 queue에 저장
			}
			while(!rotate.isEmpty()) // 회전시킨 선분을 담는 queue가 비어 있지 않다면
				queue.add(rotate.remove()); // 하나씩 빼내서 드래곤 커브 꼭짓점으로 추가시킴
			// 갱신된 드래곤 커브 끝점을 구함
			add = queue.peekLast();
			endX = add[2];
			endY = add[3];
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(in.readLine()); // n 입력
		int x, y, d, g;
		// 드래곤 커브의 정보 입력
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			findDragonCurv(y, x, d, g); // 드래곤 커브를 그리는 함수 호출 -> 그림을 보면 x, y 위치가 반대이기 때문에 서로 바꿔서 호출
		}
		int count = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) { // 1x1 사이즈의 꼭짓점이 모두 드래곤 커브이면
					count++; // count 1 증가
				}
			}
		}
		System.out.println(count); // 결과 출력
	}
}

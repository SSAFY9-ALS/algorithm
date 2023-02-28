package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 뱀 / 골드4 / 1시간
 * https://www.acmicpc.net/problem/3190
 */

public class BOJ_3190 {
	static int n;
	static char[][] board;
	
	// 종료 조건을 판별하는 메서드 -> 종료 조건: 벽에 부딪히거나, 자신의 몸에 부딪힘
	static boolean isContinued(int x, int y) {
		if(x >= 0 && x < n && y >= 0 && y < n && board[x][y] != '2') // 종료 조건에 해당하지 않다면
			return true; // true 반환
		return false; // 아니면 false 반환
	}
	
	// 다음 이동 위치를 찾는 메서드
	static int[] nextPoint(int x, int y, int d) {
		switch(d) { // 방향에 따라 이동 -> 상, 우, 하, 좌 순서대로 0, 1, 2, 3
		case 0:
			if(isContinued(x-1, y)) // 종료 조건에 해당하지 않다면
				return new int[] {x-1, y}; // 다음 좌표 반환
			return null; // 종료 조건이면 null 반환
		case 1:
			if(isContinued(x, y+1)) // 종료 조건에 해당하지 않다면
				return new int[] {x, y+1}; // 다음 좌표 반환
			return null; // 종료 조건이면 null 반환
		case 2:
			if(isContinued(x+1, y)) // 종료 조건에 해당하지 않다면
				return new int[] {x+1, y}; // 다음 좌표 반환
			return null; // 종료 조건이면 null 반환
		default:
			if(isContinued(x, y-1)) // 종료 조건에 해당하지 않다면
				return new int[] {x, y-1}; // 다음 좌표 반환
			return null; // 종료 조건이면 null 반환
		}
	}
	
	// 방향을 바꾸는 메서드
	static int findNewDirection(int d, char c) {
		if(c == 'L') // L이면 왼쪽으로 90도 회전
			return (d+3) % 4; // 상, 우, 하, 좌 => 좌, 상, 우, 하가 되어야 함
		else // D이면 오른쪽으로 90도 회전
			return (d+1) % 4; // 상, 우, 하, 좌 => 우, 하, 좌, 상이 되어야 함
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(in.readLine()); // n 입력
		int k = Integer.parseInt(in.readLine()); // k 입력
		board = new char[n][n]; // 사과와 뱀의 위치를 저장하는 배열 생성
		
		// 보드의 모든 값을 '0'으로 초기화
		for(char[] bo: board)
			Arrays.fill(bo, '0');
		
		// 보드에서 사과가 있는 칸을 '1'로 변경
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(in.readLine());
			board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = '1';
		}
		
		// 방향 변환 정보를 map에 담음
		HashMap<Integer, Character> map = new HashMap<Integer, Character>();
		int l = Integer.parseInt(in.readLine());
		for(int i = 0; i < l; i++) {
			st = new StringTokenizer(in.readLine());
			map.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		
		// 뱀의 이동 시작
		int d = 1;
		int headX = 0, headY = 0; // 뱀의 머리 위치 좌표
		ArrayDeque<int[]> snake = new ArrayDeque<>(); // 뱀의 꼬리 위치를 보기 위해 큐 생성
		snake.add(new int[] {0, 0}); // 뱀 초기값을 큐에 넣어줌
		board[0][0] = '2'; // 보드에서 뱀의 위치한 초기값을 바꿈
		int[] now, temp;
		int second = 0; // 게임의 초 수를 가지는 변수

		while(true) {
			if(map.containsKey(second)) { // 이번에 뱀의 회전이 발생한다면
				d = findNewDirection(d, map.get(second)); // 뱀을 회전시켜 방향을 갱신하는 함수 호출
			}
			now = nextPoint(headX, headY, d); // 뱀의 현재 머리 위치와 방향을 이용해 다음에 이동할 좌표를 구함
			if(now == null) // 다음 좌표가 게임의 종료 조건에 해당하면
				break; // 반복문 종료
			if(board[now[0]][now[1]] == '1') { // 다음 이동 위치에 사과가 있다면
				board[now[0]][now[1]] = '2'; // 다음 위치에 뱀이 존재
				// 뱀의 머리를 새로운 이동 위치로 갱신
				headX = now[0];
				headY = now[1];
				snake.add(new int[] {headX, headY}); // 뱀의 위치를 가지고 있는 큐에 넣어줌
			}
			else { // 다음 이동 위치에 사과가 없다면
				board[now[0]][now[1]] = '2'; // 다음 위치에 뱀이 존재
				// 뱀의 머리를 새로운 이동 위치로 갱신
				headX = now[0];
				headY = now[1];
				snake.add(new int[] {headX, headY}); // 뱀의 위치를 가지고 있는 큐에 넣어줌
				temp = snake.poll(); // 뱀의 꼬리가 존재하는 칸의 정보를 가져옴
				board[temp[0]][temp[1]] = '0'; // 해당 위치를 '0'으로 만들어줌
			}
			second++; // 1초 증가
		}
		System.out.println(second+1); // 0초부터 시작했기 때문에 +1 해서 결과를 출력
	}
}

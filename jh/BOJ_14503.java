package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 로봇 청소기 / 골드5 / 1시간
 * https://www.acmicpc.net/problem/14503
 */

public class BOJ_14503 {
	static int count = 0; // 청소한 방 개수를 담을 변수
	
	// 배열 범위 안에 있는지 판별하는 메서드
	static boolean isRange(int idx, int size) {
		if (idx >= 0 && idx < size)
			return true;
		return false;
	}

	// 상하좌우 방향의 좌표를 구하는 메서드
	static int[] rotateDirection(char[][] room, int r, int c, int d) {
		switch (d) {
		case 0:
			if (isRange(r - 1, room.length))
				return new int[] { r - 1, c };
			break;
		case 1:
			if (isRange(c + 1, room[0].length))
				return new int[] { r, c + 1 };
			break;
		case 2:
			if (isRange(r + 1, room.length))
				return new int[] { r + 1, c };
			break;
		case 3:
			if (isRange(c - 1, room[0].length))
				return new int[] { r, c - 1 };
			break;
		}
		return null;
	}

	// 현재 위치에서의 상하좌우 방향에 청소되지 않는 칸이 있는지 판별하는 메서드
	static int[] isMove(char[][] room, int r, int c, int d) {
		int[] temp;
		int[] returnArray = new int[3];
		for(int i = 1; i <= 4; i++) {
			int newD = (d + 4 - i) % 4; // 왼쪽으로 90도씩 돌아간 방향
			temp = rotateDirection(room, r, c, newD); // 바라보는 방향의 좌표를 구함
			if(room[temp[0]][temp[1]] == '0') { // 구한 좌표가 아직 청소되지 않았다면
				returnArray[0] = temp[0];
				returnArray[1] = temp[1];
				returnArray[2] = newD;
				return returnArray; // 이동할 방의 좌표, 방향을 return 해줌
			}
		}
		return null;
	}

	static void findVisited(char[][] room, int r, int c, int d) {
		if(room[r][c] == '0') { // 현재 칸이 청소되지 않았다면
			room[r][c] = '2'; // 청소한 것으로 만들고
			count++; // count 1 증가
		}
		int[] temp;
		temp = isMove(room, r, c, d); // 현재 위치의 상하좌우 방향에 청소되지 않은 칸이 있는지 확인
		if(temp != null) { // nul 이 아니라면 -> 청소되지 않은 칸이 있는 경우
			room[temp[0]][temp[1]] = '2'; // 청소한 것으로 만들고
			count++; // count 1 증가
			findVisited(room, temp[0], temp[1], temp[2]); // 새로운 위치에서 다시 함수 호출
		}
		else { // null이라면 -> 청소되지 않은 칸이 없는 경우
			int newD = (d + 2) % 4; // 바라보는 방향의 반대 방향을 구해서
			temp = rotateDirection(room, r, c, newD); // 좌표를 구해옴
			if(temp != null) { // null 이 아니라면 -> 배열 내의 범위일 때
				if(room[temp[0]][temp[1]] != '1') // 벽이 아니라면
					findVisited(room, temp[0], temp[1], d); // 한 칸 후진하고 방향은 원래 방향으로 해줌
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // n 입력
		int m = Integer.parseInt(st.nextToken()); // m 입력
		st = new StringTokenizer(in.readLine());
		int r = Integer.parseInt(st.nextToken()); // r 입력
		int c = Integer.parseInt(st.nextToken()); // c 입력
		int d = Integer.parseInt(st.nextToken()); // d 입력
		char[][] room = new char[n][m]; // 방 상태를 담을 배열
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++)
				room[i][j] = st.nextToken().charAt(0); // 방 상태 입력
		}
		findVisited(room, r, c, d); // 청소한 방 개수를 구하는 함수 호출
		System.out.println(count); // 결과 출력
	}
}

package algorithm_mar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 상어 초등학교 / 골드5 / 1시간
 * https://www.acmicpc.net/problem/21608
 */

public class BOJ_21608 {
	static int n, result;
	static int[][] classroom;
	static HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
	
	// 자리가 교실 범위 내인지 판별하는 메서드
	static boolean isRange(int x, int y) {
		if(x >= 0 && x < n && y>= 0 && y < n)
			return true;
		return false;
	}
	
	// 학생의 자리를 찾는 메서드
	static void findSeat(int student, HashSet<Integer> favor) {
		int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 방향
		int favorMax = -1, emptyMax = -1, x = -1, y = -1; // 자리를 정하기 위한 초기 변수
		int dx, dy, favorCnt, emptyCnt;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(classroom[i][j] != 0) // 이 자리에 이미 다른 학생이 앉아 있다면
					continue; // 다음으로 넘어감
				favorCnt = 0; // 인접한 좋아하는 학생의 수
				emptyCnt = 0; // 인접한 빈칸의 수
				for(int k = 0; k < 4; k++) { // 상하좌우 탐색
					dx = i + d[k][0];
					dy = j + d[k][1];
					if(isRange(dx, dy)) { // 교실 안일 때
						if(classroom[dx][dy] == 0) // 인접한 칸이 빈칸이면
							emptyCnt++; // 빈칸 개수 증가
						else if(favor.contains(classroom[dx][dy])) // 좋아하는 학생이 인접한 칸에 있다면
							favorCnt++; // 좋아하는 학생의 수 증가
					}
				}
				// 조건에 만족하면 값 갱신
				if(favorCnt > favorMax) {
					favorMax = favorCnt;
					emptyMax = emptyCnt;
					x = i;
					y = j;
				}
				else if(favorCnt == favorMax && emptyCnt > emptyMax) {
					favorMax = favorCnt;
					emptyMax = emptyCnt;
					x = i;
					y = j;
				}
			}
		}
		classroom[x][y] = student; // 만족한 칸을 학생 번호로 변경
	}
	
	// 만족도를 계산하는 메서드
	static void countFavor() {
		int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 방향
		HashSet<Integer> favor;
		int dx, dy, cnt;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				cnt = 0;
				favor = map.get(classroom[i][j]); // 현재 학생이 좋아하는 학생 목록 가져옴
				for(int k = 0; k < 4; k++) { // 상하좌우 방향 탐색
					dx = i + d[k][0];
					dy = j + d[k][1];
					if(isRange(dx, dy)) { // 교실 안일 때
						if(favor.contains(classroom[dx][dy])) // 좋아하는 학생이면
							cnt++; // 카운트 증가
					}
				}
				if(cnt > 0) // 카운트가 0이 아니면
					result += Math.pow(10, cnt-1); // 만족도 계산
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(in.readLine()); // n 입력
		classroom = new int[n][n]; // 교실을 나타내는 배열
		
		int student;
		HashSet<Integer> favor; // 각 학생이 좋아하는 학생을 담는 set
		for(int i = 0; i < n * n; i++) {
			st = new StringTokenizer(in.readLine());
			favor = new HashSet<>();
			student = Integer.parseInt(st.nextToken()); // 학생 입력
			for(int j = 0; j < 4; j++)
				favor.add(Integer.parseInt(st.nextToken())); // 학생이 좋아하는 학생 입력
			map.put(student, favor); // 입력된 값을 map에 넣어줌
			findSeat(student, favor); // 자리를 찾는 함수 호출
		}
	
		countFavor(); // 만족도를 계산하는 함수 호출
		
		System.out.println(result); // 결과 출력
	}
}

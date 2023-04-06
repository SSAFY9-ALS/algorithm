package algorithm_mar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 마법사 상어와 파이어볼 / 골드4 / 2시간
 * https://www.acmicpc.net/problem/20056
 */

public class BOJ_20056 {
    static int N, result;
	static ArrayList<Ball> ball;
	
	// Ball 클래스
    static class Ball implements Comparable<Ball> {
		int r, c, m, s, d;

		public Ball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		// 정렬을 위한 compareTo 정의
		@Override
		public int compareTo(Ball o) {
			if(this.r == o.r)
				return this.c - o.c;
			return this.r - o.r;
		}
	}
	
    // 파이어볼을 움직이는 메서드
	static void moveBall() {
		int[][] d = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}}; // 8개 방향
		int x, y, direction, s, dx, dy;
		for(int i = 0; i < ball.size(); i++) { // 볼 개수만큼 반복
			x = ball.get(i).r;
			y = ball.get(i).c;
			direction = ball.get(i).d;
			s = ball.get(i).s;
			// 속력과 방향을 이용해 이동할 거리를 한번에 구함 -> s % N을 하여 동일한 과정이 반복되지 않게 함
			dx = d[direction][0] * (s % N);
			dy = d[direction][1] * (s % N);
			ball.get(i).r = (x + dx + N) % N;
			ball.get(i).c = (y + dy + N) % N;
		}
	}
	
	// 파이어볼을 삭제하는 메서드
	static void removeBall(int start, int end, int r, int c, int weight, int speed, boolean flag) {
		for(int i = start; i >= end; i--) { // start부터 end까지 반복 -> 삭제 연산이기 때문에 뒤에서부터
			ball.remove(i); // 파이어볼 삭제
		}
		result -= weight; // 전체에서 질량 합을 빼줌
		if(weight / 5 == 0) // 질량 합을 나눌 때 0이 되면
			return; // 그냥 종료
		int num = flag? 0: 1; // flag 변수에 따라 방향 결정
		for(int i = num; i < 8; i += 2) {
			ball.add(new Ball(r, c, weight/5, speed/(start-end+1), i)); // 새로 나누어진 파이어볼을 추가
			result += weight / 5; // 결과에 새로운 파이어볼의 질량을 더해줌
		}
	}
	
	// 파이어볼을 나누는 메서드
	static void divideBall() {
		int start = ball.size() - 1; // 시작 파이어볼
		int sumWeight = ball.get(start).m; // 행, 열이 같은 파이어볼들의 총 질량 합
		int sumSpeed = ball.get(start).s; // 행, 열이 같은 파이어볼들의 총 속력 합
		int cnt = 1;
		int direction = (ball.get(start).d % 2 == 0)? 0: 1; // 거리가 짝수일 때 0, 홀수일 때 1
		boolean flag = true; // 합쳐지는 파이어볼의 방향이 모두 짝수거나 홀수인지 판별하는 변수
		for(int i = start-1; i >= 0; i--) { // 삭제가 이루어지기 때문에 뒤에서부터 탐색
			if(ball.get(i).r == ball.get(i+1).r && ball.get(i).c == ball.get(i+1).c) { // 행, 열이 같다면
				sumWeight += ball.get(i).m; // 질량 더해줌
				sumSpeed += ball.get(i).s; // 속력 더해줌
				cnt++; // 개수 1 증가
				if(direction == 0 && ball.get(i).d % 2 != 0) // 이전값의 방향과 짝/홀수가 다르다면
					flag = false; // flag false로 설정
				else if(direction == 1 && ball.get(i).d % 2 == 0) // 이전값의 방향과 짝/홀수가 다르다면
					flag = false; // flag false로 설정
			}
			else { // 이전값과 행, 열이 다를 때
				if(cnt > 1) // 개수가 1보다 크면 -> 이전에 합쳐진 파이어볼이 존재
					removeBall(start, i+1, ball.get(start).r, ball.get(start).c, sumWeight, sumSpeed, flag); // 파이어볼을 삭제하는 함수 호출
				
				// 탐색에 필요한 변수 초기화
				start = i;
				sumWeight = ball.get(i).m;
				sumSpeed = ball.get(i).s;
				cnt = 1;
				direction = (ball.get(i).d % 2 == 0)? 0: 1;
				flag = true;
			}
		}
		if(cnt > 1) // 인덱스 0번까지 하나로 합쳐진 경우를 체크
			removeBall(start, 0, ball.get(start).r, ball.get(start).c, sumWeight, sumSpeed, flag);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // n 입력
		int M = Integer.parseInt(st.nextToken()); // m 입력
		int K = Integer.parseInt(st.nextToken()); // k 입력
		ball = new ArrayList<>(); // 파이어볼을 저장할 리스트
		int r, c, m, s, d;
		for(int i = 0; i < M ; i++) {
			// 파이어볼 입력
			st = new StringTokenizer(in.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			ball.add(new Ball(r, c, m, s, d)); // 리스트에 add
			result += m; // 총 질량을 더해줌
		}
		for(int i = 0; i < K; i++) { // k번 명령
			if(ball.size() == 0) { // 남은 파이어볼이 없다면
				result = 0; // 결과는 0
				break; // 반복문 종료
			}
			moveBall(); // 파이어볼이 이동하는 함수 호출
			Collections.sort(ball); // 같은 칸에 있는지 확인하기 위해 행, 열 순서로 정렬
			divideBall(); // 파이어볼을 나누는 함수 호출
		}
		System.out.println(result); // 결과 출력
	}
}
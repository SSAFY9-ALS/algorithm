import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 뱀 / 골드4 / 1시간
 * https://www.acmicpc.net/problem/3190
 */
public class BJ_3190_뱀 {
	
	// 방향 초기화 : 오른쪽 부터 시계방향
	static final int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	// 방향 바꾸는 거 표시해주는 변수
	static HashMap<Integer, Integer> changeDirection;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 보드의 크기 : N
		int N = Integer.parseInt(br.readLine());
		
		// 사과의 개수 : K
		int K = Integer.parseInt(br.readLine());
		
		// 사과의 위치
		int[][] apple = new int[N+1][N+1];
		
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			apple[x][y] = 1;
		}
		
		// 뱀의 방향 변환 횟수 : L
		int L = Integer.parseInt(br.readLine());
		
		// 방향 바꾸는 거 표시하는 변수 초기화
		changeDirection = new HashMap<>();
		
		// 방향 (초기화 : 오른쪽)
		int d = 0;
		
		// 뱀의 방향 변환 정보
		for (int l = 0; l < L; l++) {
			st = new StringTokenizer(br.readLine());
			
			// X초가 끝난뒤 L(왼쪽으로 90도 방향 회전, 반시계), D(오른쪽으로 90도 방향 회전, 시계)
			// 정수 X
			int X = Integer.parseInt(st.nextToken());
			
			// 문자 C
			char C = st.nextToken().charAt(0);
			
			if(C == 'L') {
				d = (d+3)%4;
			} else {
				d = (d+1)%4;
			}
			
			changeDirection.put(X, d);
		}
	
		
		// 뱀 꼬리
		LinkedList<int[]> tail = new LinkedList<>();
		
		tail.add(new int[] {1,1});
		
		int time = 0;
		int dir = 0;
		int dx = 1;
		int dy = 1;
		// 종료 flag
		boolean bye = false;
		while(!bye) {
			
			if(changeDirection.containsKey(time)) {
				// 방향 바꾸기
				dir = changeDirection.get(time);
			}

			time++;
			
			dx += directions[dir][0];
			dy += directions[dir][1];
			
			// 이전 꼬리 좌표 값
			int frontX = dx;
			int frontY = dy;

			// 1. 범위 벗어나면 종료
			if(dx < 1 || dx > N || dy < 1 || dy > N) break;			
			
			// 사과가 있을 때 길이 증가
			if(apple[dx][dy] == 1) {
				tail.add(new int[] {dx, dy});
				
				// 사과를 먹었으니 0으로 초기화
				apple[dx][dy] = 0;
				continue;
			}
			
			for (int i = tail.size()-1; i >= 0; i--) {
				int x = tail.get(i)[0];
				int y = tail.get(i)[1];
				
				// 2. 자기자신과 부딪히면 종료
				if(x == dx && y == dy) {
					bye = true;
					break;
				}
				
				// 현재 꼬리보다 앞의 꼬리의 좌표를 넣는다.
				tail.get(i)[0] = frontX;
				tail.get(i)[1] = frontY;
				
				// 그리고 그 다음 꼬리를 위해 현재 꼬리 좌표로 갱신
				frontX = x;
				frontY = y;
			}
		}
		
		System.out.println(time);
	}

}

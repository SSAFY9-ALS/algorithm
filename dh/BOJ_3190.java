import java.io.*;
import java.util.*;
/**
 * 
 * 뱀 / 골드4 / 120분
 * https://www.acmicpc.net/problem/3190
 * 
 */
class Pair{
	int x;
	int y;
	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int n;
	static int[][] map;
	static int k;
	static int l;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	
	static int time;
	static int start;
	static int end;
	static ArrayDeque<Pair> snake;
	static int dir = 1;
	static boolean finish;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n+2][n+2];
		k = Integer.parseInt(br.readLine());
		snake = new ArrayDeque<Pair>();
		// 뱀이 위치하고 있는 각각의 좌표를 큐에 저장
		for(int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			// 사과 표시
		}
		l = Integer.parseInt(br.readLine());
		snake.add(new Pair(1,1));
		start = 1;
		for(int i = 0; i < l; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			end = Integer.parseInt(st.nextToken());
			move();
			// 언제까지 반복 해야 하는지 입력 받은 후 진행
			
			if(finish) {
				// finish 변수가 변경되었으면 break
				break;
			}
			
			char change = st.nextToken().charAt(0);
			if(change == 'D') {
				dir = (dir + 1) % 4;
			}
			else {
				dir = (dir + 3) % 4;
			}
			// 방향 전환해주기
			
			if(i == l - 1) {
				// for문의 마지막 진행
				end = 102;
				move();
			}
		}
		System.out.println(time);
	}
	
	static void move() {
		for(int i = start; i <= end; i++) {
			// 이전에 진행했던 시간부터 입력받은 end까지 진행
			time++;
			// 초 증가
			int lx = snake.peekLast().x + dx[dir];
			int ly = snake.peekLast().y + dy[dir];
			// 머리부분에서 방향에 맞게 한칸 전진
			if(lx <= 0 || lx > n || ly <= 0 || ly > n) {
				// 경계에 놓이면 종료
				finish = true;
				return;
			}
			for(Pair p : snake) {
				if(lx == p.x && ly == p.y) {
					// 뱀의 위치한 좌표와 같아지면 종료
					finish = true;
					return;
				}
			}
			if(map[lx][ly] == 1) {
				// 전진한 칸에 사과가 있으면 사과를 먹은 후 꼬리는 그대로 하며 머리는 전지
				map[lx][ly] = 0;
				snake.add(new Pair(lx, ly));
			}
			else {
				// 전진한 칸이 빈 공간이면 머리 전진, 꼬리 삭제
				snake.add(new Pair(lx, ly));
				snake.poll();
			}
			
		}
		start = end + 1;
		// 진했했던 시간 저장
	}
	
}
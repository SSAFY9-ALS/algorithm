import java.io.*;
import java.util.*;
/**
 * 
 * 구슬탈출2 / 골드1 / 200+분
 * https://www.acmicpc.net/problem/13460
 * 
 */
class Marble {
	int Rx;
	int Ry;
	int Bx;
	int By;
	int move;
	public Marble() {
		super();
	}
	public Marble(int rx, int ry, int bx, int by, int move) {
		super();
		Rx = rx;
		Ry = ry;
		Bx = bx;
		By = by;
		this.move = move;
	}
}

public class Escape {
	static int n, m;
	static int Hx, Hy;
	static Marble o;
	static Queue<Marble> q;
	static boolean isRedIntoHole;
	static boolean isBlueIntoHole;

	static boolean possible;
	static int answer = -1;
	static char[][] map;
	static boolean[][][][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		visited = new boolean[n][m][n][m];
		o = new Marble();
		// 초기값 객체 생성
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'R') {
					o.Rx = i;
					o.Ry = j;
				} else if (map[i][j] == 'B') {
					o.Bx = i;
					o.By = j;
				} else if (map[i][j] == 'O') {
					Hx = i;
					Hy = j;
				}
			}
		}
		o.move = 1;
		start();
		System.out.println(answer);
	}

	static void start() {
		q = new ArrayDeque<Marble>();
		q.add(o);
		visited[o.Rx][o.Ry][o.Bx][o.By] = true;
		// 초기값 큐에 넣고 방문 여부 확인
		while (!q.isEmpty()) {
			Marble tmp = q.poll();
			int nowRx = tmp.Rx;
			int nowRy = tmp.Ry;
			int nowBx = tmp.Bx;
			int nowBy = tmp.By;
			int nowMove = tmp.move;
			// 객체 값 옮기기
			
			if(nowMove > 10) {
				answer = -1;
				return;
			}
			for (int i = 0; i < 4; i++) {
				escape(nowRx, nowRy, nowBx, nowBy, nowMove, i);
				isBlueIntoHole = false;
				isRedIntoHole = false;
				if(possible == true) {
					return;
				}
			}
		}
		
	}

	static void escape(int nowRx, int nowRy, int nowBx, int nowBy, int nowMove, int dir) {
		// 주어진 방향으로 이동한 결과를 분석하여 이어서 진행하게 하는 메서드
		int origin_Rx = nowRx;
		int origin_Ry = nowRy;
		int origin_Bx = nowBx;
		int origin_By = nowBy;
		while (true) {
			// R 구슬을 주어진 방향에 맞춰 이동
			nowRx += dx[dir];
			nowRy += dy[dir];
			if (nowRx == Hx && nowRy == Hy) {
				isRedIntoHole = true;
				// 홀의 좌표와 같아지면 넣어지는 것이므로 체크
			}
			if(map[nowRx][nowRy] == '#') {
				// 이동한 위치가 벽이면 뒤로 이동 후 탈출
				nowRx -= dx[dir];
				nowRy -= dy[dir];
				break;
			}
		}
		while (true) {
			// B 구슬을 주어진 방향에 맞춰 이동
			nowBx += dx[dir];
			nowBy += dy[dir];
			if (nowBx == Hx && nowBy == Hy) {
				isBlueIntoHole = true;
			}
			if(map[nowBx][nowBy] == '#') {
				nowBx -= dx[dir];
				nowBy -= dy[dir];
				break;
			}
		}
		
		if(isBlueIntoHole) {
			// B구슬이 들어갔다면 리턴
			return;
		}
		else if(isRedIntoHole) {
			possible = true;
			answer = nowMove;
			return;
			// 가능 여부 표시 후 이동 횟수 저장 후 리턴
		}
		
		if(nowRx == nowBx && nowRy == nowBy) {
			// 구슬이 같은 위치에 놓여져 있으면 기존에 있던 위치를 비교하여
			// 이동했던 방향에 맞춰 뒤로 이동
			if(dir == 0) {
				if(origin_Rx > origin_Bx) {
					nowRx -= dx[dir];
				}
				else {
					nowBx -= dx[dir];
				}
			} else if (dir == 1) {
				if(origin_Ry > origin_By) {
					nowBy -= dy[dir];
				}
				else {
					nowRy -= dy[dir];
				}
				
			} else if (dir == 2) {
				if(origin_Rx > origin_Bx) {
					nowBx -= dx[dir];
				}
				else {
					nowRx -= dx[dir];
				}
				
			} else if (dir == 3) {
				if(origin_Ry > origin_By) {
					nowRy -= dy[dir];
				}
				else {
					nowBy -= dy[dir];
				}
			}
		}
		if(!visited[nowRx][nowRy][nowBx][nowBy]) {
			// 방문 여부 체크
			nowMove++;
			q.add(new Marble(nowRx, nowRy, nowBx, nowBy, nowMove));
			visited[nowRx][nowRy][nowBx][nowBy] = true;
		}
	}

}

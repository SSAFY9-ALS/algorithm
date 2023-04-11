import java.util.*;
import java.io.*;

/**
 * 
 * 달리기 / 플레 3 / 120분
 * https://www.acmicpc.net/problem/16930
 * 
 */
class Pair {
	int x;
	int y;
	int time;

	public Pair(int x, int y, int time) {
		super();
		this.x = x;
		this.y = y;
		this.time = time;
	}
}

class Visit {
	boolean result;
	int time;

	public Visit(boolean result, int time) {
		super();
		this.result = result;
		this.time = time;
	}
}

public class Main {
	static int n;
	static int m;
	static int k;
	static char[][] map;
	static Visit[][] visited;
	static int startx;
	static int starty;
	static int endx;
	static int endy;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		visited = new Visit[n][m];
		// 방문 기록을 방문 여부와 방문했던 시간을 저장
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visited[i][j] = new Visit(false, Integer.MAX_VALUE);
			}
		}
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		st = new StringTokenizer(br.readLine());
		startx = Integer.parseInt(st.nextToken()) - 1;
		starty = Integer.parseInt(st.nextToken()) - 1;
		// 시작 위치
		endx = Integer.parseInt(st.nextToken()) - 1;
		endy = Integer.parseInt(st.nextToken()) - 1;
		// 종료 위치

		bfs();
		System.out.println(answer);

	}

	static void bfs() {
		Queue<Pair> q = new ArrayDeque<>();
		q.offer(new Pair(startx, starty, 0));
		visited[startx][starty] = new Visit(true, 0);
		while (!q.isEmpty()) {
			Pair temp = q.poll();
			for (int d = 0; d < 4; d++) {
				int lx = temp.x;
				int ly = temp.y;
				for (int i = 1; i <= k; i++) {
					// k만큼 이동
					lx += dx[d];
					ly += dy[d];
					if (lx < 0 || lx >= n || ly < 0 || ly >= m || map[lx][ly] == '#' || visited[lx][ly].time < temp.time + 1) {
						// k만큼 전진하여 갈 수 없는 상황일 때는 break;
						break;
					}
					if (visited[lx][ly].time == temp.time + 1)
						// 전진하였을 때 걸린 시간이 이전에 방문했던 시간과 같다면 진행하지않고 continue;
						continue;
					if (!visited[lx][ly].result && visited[lx][ly].time > temp.time + 1) {
						// 방문하지 않았고 전진하였을 때 이전에 방문했던 시간보다 작으면 실행
						if (lx == endx && ly == endy) {
							answer = temp.time + 1;
							return;
						}
						q.offer(new Pair(lx, ly, temp.time + 1));
						visited[lx][ly].result = true;
						visited[lx][ly].time = temp.time + 1;
						// 큐에 방문한 좌표 및 걸린 시간을 넣고
						// 방문 기록 저장
					}
				}
			}
		}
		answer = -1;
	}
}

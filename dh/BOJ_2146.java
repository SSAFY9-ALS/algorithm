import java.io.*;
import java.util.*;

/**
 * 
 * 다리 만들기 / 골드3 / 45분
 * https://www.acmicpc.net/problem/2146
 *
 */
class Pair{
	int x;
	int y;
	int count;
	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Pair(int x, int y, int count) {
		super();
		this.x = x;
		this.y = y;
		this.count = count;
	}
	
	
}
public class Main {
	static int n;
	static int[][] map;
	static int[][] island;
	static boolean[][] visited;
	static Queue<Pair> q;
	static int length;
	static int answer = Integer.MAX_VALUE;
	static boolean check;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		island = new int[n][n];
		visited = new boolean[n][n];
		q = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int num = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1 && visited[i][j] == false)
					bfs_island(new Pair(i, j), num++);
				// 각 섬마다 번호를 붙여 분리해 island맵에 저장
			}
		}
		visited = new boolean[n][n];
		// 방문 여부 초기화
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1 && visited[i][j] == false) {
					bfs_bridge(new Pair(i, j, 0), island[i][j]);
					// 방문하지 않은 섬일 때 bfs실행
					if(check) {
						// 다른 섬에 도달했을 때의 거리 비교하여 answer에 저장
						answer = Math.min(answer, length);
						check = false;
						// 다른 섬 도착 여부 초기화
					}
					q.clear();
					// 큐 초기화
					visited = new boolean[n][n];
					// 방문 여부 초기화
				}
			}
		}
		System.out.println(answer);

	}

	static void bfs_island(Pair p, int num) {
		q.add(p);
		visited[p.x][p.y]= true;
		island[p.x][p.y] = num;
		while(!q.isEmpty()) {
			Pair tmp = q.poll();
			for(int i = 0; i < 4; i++) {
				int lx = tmp.x + dx[i];
				int ly = tmp.y + dy[i];
				if(lx >= 0 && lx < n && ly >= 0 && ly < n && map[lx][ly] == 1 && visited[lx][ly] == false) {
					q.add(new Pair(lx, ly));
					visited[lx][ly] = true;
					island[lx][ly] = num;
					// 섬일때 해당 칸을 주어진 num 저장
				}
			}
		}
	}
	
	static void bfs_bridge(Pair p, int num) {
		q.add(p);
		visited[p.x][p.y]= true;
		while(!q.isEmpty()) {
			Pair tmp = q.poll();
			for(int i = 0; i < 4; i++) {
				int lx = tmp.x + dx[i];
				int ly = tmp.y + dy[i];
				if(lx >= 0 && lx < n && ly >= 0 && ly < n && visited[lx][ly] == false) {
					if(island[lx][ly] == 0) {
						q.add(new Pair(lx, ly, tmp.count + 1));
						visited[lx][ly] = true;
						// 탐색한 곳이 바다이면 위치 및 다리길이를 1 증가하여 객체를 생성 후 큐에 저장
						// 방문 체크
					}
					else if(island[lx][ly] != num) {
						length = tmp.count;
						check = true;
						return;
						// 탐색한 곳이 다른 섬이면 지나온 길이를 length에 저장 후 check변경 후 bfs종료
					}
					
				}
			}
		}
	}
}
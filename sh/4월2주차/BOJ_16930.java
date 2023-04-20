import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 달리기 / 플3 / 2시간 + a
 *
 */
public class BJ_16930_달리기 {
	
	static final int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static final int INF = Integer.MAX_VALUE;
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 체육관의 크기 : N, M
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 칸의 최대 개수 : K
		int K = Integer.parseInt(st.nextToken());
		
		// 체육관 초기화
		map = new char[N][M];
		
		// 체육관의 상태
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		// 시작 지점
		// 도착 지점
		int[] startPos = new int[2];
		int[] endPos = new int[2];
		
		// 시작, 도착 지점 초기화
		st = new StringTokenizer(br.readLine());
		startPos[0] = Integer.parseInt(st.nextToken())-1;
		startPos[1] = Integer.parseInt(st.nextToken())-1;
		endPos[0] = Integer.parseInt(st.nextToken())-1;
		endPos[1] = Integer.parseInt(st.nextToken())-1;
		
		int dis = bfs(startPos, N, M, K, endPos);
		
		bw.write(dis + "\n"); // 버퍼에 있는 값 전부 출력
		bw.flush(); // 남아있는 데이터를 모두 출력
		bw.close(); // 스트림을 닫음
	}
	
	static int bfs(int[] pos, int N , int M, int K, int[] endPos) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {pos[0], pos[1], 0});
		
		int[][] visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = INF;
			}
		}
		
		visited[pos[0]][pos[1]] = 0;
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			int x = poll[0];
			int y = poll[1];
			int time = poll[2];
			
			// 2. 도착지점이라면 그대로 종료
			if(x == endPos[0] && y == endPos[1]) return time;

			for (int i = 0; i < 4; i++) {
				for (int j = 1; j <= K; j++) {
					int dx = x + (j * directions[i][0]);
					int dy = y + (j * directions[i][1]);
					
					if(dx < 0 || dx >= N || dy < 0 || dy >= M) break;
					
					// 자기보다 작은 시간의 초면은 그대로 벗어나기
					if(visited[dx][dy] < time + 1) break;
					if(visited[dx][dy] == time + 1) continue;
					
					// 빈칸 : '.', 벽 : '#'
					// 1. 벽이면 지나가기
					if(map[dx][dy] == '#') break;
					
					visited[dx][dy] = time+1;
					queue.add(new int[] {dx, dy, time + 1});					
				}
			}
		}
		return -1;
	}
}

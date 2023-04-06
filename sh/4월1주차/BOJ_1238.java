import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 파티 / 골드3 
 * https://www.acmicpc.net/problem/1238
 */
public class BJ_1238_파티 {
	
	static final int INF = 10000001;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 학생 수 : N, 단방향 도로들 : M, 파티 장소 : X
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		// 도로
		int[][] road = new int[N+1][N+1];
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				if(i == j) road[i][j] = 0;
				else road[i][j] = INF;
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			road[start][end] = weight;
		}
	
		for (int k = 1; k < N+1; k++) {
			for (int s = 1; s < N+1; s++) {
				for (int e = 1; e < N+1; e++) {
					road[s][e] = Math.min(road[s][e], road[s][k] + road[k][e]);
				}
			}
		}
		
		// 최대거리
		int maxDis = 0;
		for (int i = 1; i < N+1; i++) {
			maxDis = Math.max(maxDis, road[i][X] + road[X][i]);
		}
		
		System.out.println(maxDis);
	}
}

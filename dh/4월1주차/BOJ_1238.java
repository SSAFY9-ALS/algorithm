import java.util.*;
import java.io.*;

/**
 * 
 * 파티 / 골드 3 / 20분
 * https://www.acmicpc.net/problem/1238
 * 
 */
public class Main {
	static int n;
	static int m;
	static int x;
	static int[][] matrix;
	final static int inf = 100000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		matrix = new int[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i == j) {
					matrix[i][j] = 0;
					continue;
				}
				matrix[i][j] = inf;
				// 플로이드 알고리즘을 사용하기 위해 i->i를 제외한 값을 최대값으로 초기화
			}
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			matrix[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		// 인접행렬 입력값을 통해 초기화
		}
		
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
						// 플로이드 알고리즘 사용
					}
				}
			}
		}
		int max = 0;
		for(int i = 1; i <= n; i++) {
			if(max < matrix[i][x] + matrix[x][i]) {
				max = matrix[i][x] + matrix[x][i];
				// 집에서 x로, x에서 집으로 오고 가는데 가장 큰 비용 구하기
			}
		}
		System.out.println(max);
		
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* 
 * 플로이드 / 골드4 / 30분
 * https://www.acmicpc.net/problem/11404
 */

public class Main {
	static final int inf = 10000001;
	// n의 최대 100, 비용의 최대 100000 이므로 
	// 10000000을 넘지 않는다. 최댓값 10000001로 설정

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		int[][] citys = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) {
					citys[i][j] = 0;
				} else {
					citys[i][j] = inf;
				}
				// 시작 도시와 도착 도시가 같으면 0, 그 외에는 inf 값 저장 ( 1차 초기화 )
			}
		}

		for (int i = 1; i <= m; i++) {
			String[] temp = br.readLine().split(" ");
			int start = Integer.parseInt(temp[0]);
			int end = Integer.parseInt(temp[1]);
			int value = Integer.parseInt(temp[2]);
			
			citys[start][end] = Math.min(citys[start][end], value);
			// 동일한 경로일시엔 최솟값 저장
		}
		// 입력받은 정보를 통해 각 인접행렬의 정보 저장 ( 2차 초기화 )
		
		for(int i = 1; i <= n; i++) {
			// 거처갈 노드 1부터 n까지
			for(int j = 1; j <= n; j++) {
				// 출발지 j 설정
				for(int k = 1; k <= n; k++) {
					// 도착지 k 설정
					citys[j][k] = Math.min(citys[j][k], citys[j][i] + citys[i][k]);
				}
				// 
			}
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (citys[i][j] == inf) {
					citys[i][j] = 0;
					// i에서 j로 갈 수 없는 경우에는 0 저장
				}
				System.out.print(citys[i][j] + " ");
			}
			System.out.println();
		}

	}
}
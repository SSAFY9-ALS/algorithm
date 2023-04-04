import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
 * 운동 / 골드4 / 60분
 * https://www.acmicpc.net/problem/1956
 */

public class Main {
	static final int inf = 10000000;
	static int min = 10000000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int v = Integer.parseInt(s[0]);
		int e = Integer.parseInt(s[1]);
		int[][] citys = new int[v + 1][v + 1];

		for (int i = 1; i <= v; i++) {
			for (int j = 1; j <= v; j++) {
				if (i == j) {
					citys[i][j] = 0;
				} else {
					citys[i][j] = inf;
				}
				// i == j이면 0, 그외에는 inf
			}
		}

		for (int i = 0; i < e; i++) {
			s = br.readLine().split(" ");
			citys[Integer.parseInt(s[0])][Integer.parseInt(s[1])] = Integer.parseInt(s[2]);
		} // 입력받은 거리 저장
		
		for(int k = 1; k <= v; k++) {
			// 거처갈 노드 1부터 v까지
			for(int i = 1; i <= v; i++) {
				for(int j = 1; j <= v; j++) {
					// 출발지 i, 도착지 j
					int value = citys[i][k] + citys[k][j];
					citys[i][j] = Math.min(citys[i][j], value);
				}
				// 중간지점을 거쳐간 거리와 기존의 거리를 비교하여 최솟값 저장
			}
		}
		
		int check = 0;
		for(int i = 1; i <= v; i++) {
			for(int j = 1; j <= v; j++) {
				if(!(citys[i][j] == inf) && !(citys[j][i] == inf) && !(i == j)) {
					// i -> i 인 사이클을 구해야 하므로 i->j->i인 사이클을 탐색
					// i->j 혹은 j->i 의 경로가 존재하고 i와 j가 동일하지 않으면 실행
					min = Math.min(min, citys[i][j] + citys[j][i]);
					// 기존 최솟값과 i -> j -> i의 거리의 최솟값을 저장
					check = 1;
				}
			}
		}
		if(check == 0)
			min = -1;
		System.out.println(min);
		
	}
}
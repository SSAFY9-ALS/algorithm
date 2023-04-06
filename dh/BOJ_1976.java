import java.util.*;
import java.io.*;
/**
 * 
 * 여행 가자 / 골드 4 / 25분
 * https://www.acmicpc.net/problem/1976
 * 
 */
public class Main {
	static int n;
	static int m;
	static int[][] matrix;
	static int[] arr;
	static final int inf = 200000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		matrix = new int[n+1][n+1];
		arr = new int[m];
		
		StringTokenizer st = null;
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				if(i!=j && matrix[i][j] == 0) {
					matrix[i][j] = inf;
					// 플로이드 알고리즘을 사용하기 위해 i->i를 제외한 0값을 최대값으로 초기화
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
						// 갈 수 있는 경로는 값을 할당받아 초기화가 된다.
					}
				}
			}
		}
		boolean flag = true;
		for(int i = 1; i < m; i++) {
			if(matrix[arr[i-1]][arr[i]] == inf) {
				// 초기화가 되지 않았다면 갈 수가 없다는 뜻
				flag = false;
				break;
			}
		}
		if(flag) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
		
	}
}

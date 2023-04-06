import java.io.*;
import java.util.*;
/**
 * 
 * 테트로미노 / 골드4 / 60분
 * https://www.acmicpc.net/problem/14500
 * 
 */
class Pair{
	int x;
	int y;
	int count;
	public Pair(int x, int y, int count) {
		super();
		this.x = x;
		this.y = y;
		this.count = count;
	}
	
}
public class Main {
	static int n, m;
	static int[][] matrix;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] visited;
	static int[] arr;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		matrix = new int[n][m];
		visited = new boolean[n][m];
		arr = new int[4];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				visited[i][j] = true;
				bfs(i, j, 0);
				visited[i][j] = false;
			}
		}
		System.out.println(answer);
		
	}
	static void bfs(int x, int y, int count) {
		if(count == 4) {
			// 4칸이 되면 저장된 배열 모두 더하기
			int tmp = 0;
			for(int i = 0; i < 4; i++) {
				tmp += arr[i];
			}
			answer = Math.max(answer, tmp);
			// 최댓값 구하기
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int lx = x + dx[i];
			int ly = y + dy[i];
			if(lx >= 0 && lx < n && ly >= 0 && ly < m
					&& !visited[lx][ly]) {
				if(count == 2) {
					// 'ㅗ' 모양은 dfs로 구할 수 없으므로
					// 2칸을 탐색한 후 3칸을 탐색할 때 원래 탐색할려는 칸을 arr[2](3번째 칸)에 넣은 후 방문표시를 하고
					// 2칸에서 다시 dfs를 실행하여 'ㅗ', 'ㅜ', 'ㅏ', 'ㅓ' 모양을 찾는다.
					arr[count] = matrix[lx][ly];
					visited[lx][ly] = true;
					bfs(x, y, count + 1);
					visited[lx][ly] = false;
				}
				// dfs를 통하여 테트로미노를 찾는다.
				arr[count] = matrix[lx][ly];
				visited[lx][ly] = true;
				bfs(lx, ly, count + 1);
				visited[lx][ly] = false;
			}
		}
	}
}
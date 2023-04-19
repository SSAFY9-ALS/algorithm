import java.util.*;
import java.io.*;

/**
 * 
 * 사다리 조작 / 골드 3 / 100분
 * https://www.acmicpc.net/problem/15684
 * 
 */

public class Main {
	static int n;
	static int m;
	static int h;
	static int[][] map;
	static boolean finish;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		finish = false;
		map = new int[h+1][n+1];
		for(int i = 0;i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1; // 오른쪽 다리가 있으면 1
			map[a][b+1] = 2; // 왼쪽 다리가 있으면 2
		}
		
		
		for(int i = 0; i <= 3; i++) {
			// 가로줄을 0 ~ 3개 놓기
			answer = i;
			dfs(1, 0);
			if(finish) {
				break;
			}
		}
		
		if(!finish) {
			// 3개 놓아도 도착하지 않으면 -1
			answer = -1;
		}
		System.out.println(answer);
	}
	public static void dfs(int x, int count) {
		if(finish) {
			return;
			// 도착하면 종료
		}
		if(answer == count) {
			// 놓을 가로줄 만큼 count가 채워지면 도착하는지 확인
			if(check()) {
				finish = true;
			}
			return;
		}
		for (int i = x; i < h + 1; i++) {
			for (int j = 1; j < n; j++) {
				if (map[i][j] == 0 && map[i][j + 1] == 0) {
					map[i][j] = 1;
					map[i][j + 1] = 2;
					// 가로줄 놓기
					dfs(i, count + 1);
					map[i][j] = 0;
					map[i][j + 1] = 0;
					// 가로줄 빼기 (백트래킹)
				}
			}
		}
		
	}
	
	public static boolean check() {
		for(int i = 1; i <= n ; i++) {
			int num = i;
			for(int j = 1; j <= h; j++) {
				if(map[j][num] == 1) {
					num++;
					// 오른쪽에 사다리가 있으면 num(열값) 오른쪽으로 이동
				}
				else if(map[j][num] == 2) {
					num--;
					// 왼쪽에 사다리가 있으면 num(열값) 왼쪽으로 이동
				}
			}
			if(num != i) {
				return false;
				// num값이 i에 도달하지 않으면 false 리턴
			}
		}
		return true;
		// 체크 결과 모두 통과하면 true 리턴
	}
}

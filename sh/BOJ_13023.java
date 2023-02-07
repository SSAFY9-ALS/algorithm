import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * ABCDE / 골드 5 / 2시간
 * https://www.acmicpc.net/problem/13023
 */

public class q13023 {
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		// 사람의 수
		int N = Integer.parseInt(st.nextToken());
		// 친구 관계의 수
		int M = Integer.parseInt(st.nextToken());

		list = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0;i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		for(int i = 0;i < N; i++) {
			visited = new boolean[N];
			dfs(i,0);
		}
		System.out.println(0);
	}
	
	public static void dfs(int x, int len) {
		if(len == 4) {
			System.out.println(1);
			System.exit(0);
		}
		
		visited[x] = true;
		
		for(int i = 0; i < list[x].size(); i++) {
			int temp = list[x].get(i);
			if(visited[temp] == false) {
				visited[temp] = true;
				dfs(temp, len+1);
				visited[temp] = false;
			}
		}
	}
}

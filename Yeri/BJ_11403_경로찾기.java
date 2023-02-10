package yeri.algorithm0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 경로찾기 / 실버1 / 40분
 * https://www.acmicpc.net/problem/11403
 */
public class BJ_11403_경로찾기 {
	static int[][] visited;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N];
		visited = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			graph[r] = new ArrayList<>();
			for (int c = 0; c < N; c++) {
				if (st.nextToken().equals("1")) {
					graph[r].add(c);
				}
			}
		}
		for (int start = 0; start < N; start++) {
			for (int i = 0; i < graph[start].size(); i++) {
//			if(graph[start].size()>0)
				dfs(start, graph[start].get(i));
			}

		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(visited[r][c]+" ");
			}
			System.out.println();
		}

	}

	private static void dfs(int start, int curr) {
		visited[start][curr] = 1;
		for (int i = 0; i < graph[curr].size(); i++) {
			if (visited[start][graph[curr].get(i)] == 1) {
				continue;
			}
			dfs(start, graph[curr].get(i));
		}
	}

}

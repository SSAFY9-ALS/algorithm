package yeri_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * DFS와BFS / 실버2 / 30분
 * https://www.acmicpc.net/problem/1260
 */
public class BJ_1260_DFS와BFS {

	static ArrayList<Integer>[] graph;
	static ArrayList<Integer> stack = new ArrayList<>();
	static boolean visited[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int curr = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for(int i = 0; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		visited = new boolean[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		for(int i = 0; i < N+1; i++) {
			Collections.sort(graph[i]);
		}
		
		dfs(curr);
		visited = new boolean[N+1];
		sb.append("\n");
		
		stack.add(curr);
		bfs(curr);
		
		System.out.println(sb);
	}
	private static void bfs(int curr) {
		visited[curr] = true;
		sb.append(stack.get(0)).append(" ");
			for(int i = 0; i < graph[curr].size(); i++) {
				if(visited[graph[curr].get(i)]) {
					continue;
				}
				if(stack.indexOf(graph[curr].get(i))<0)
					stack.add(graph[curr].get(i));
			}
		stack.remove(0);
		if(stack.size()>0)
			bfs(stack.get(0));
		
	}
	private static void dfs(int curr) {
		visited[curr] = true;
		sb.append(curr).append(" ");
		for(int i = 0; i < graph[curr].size(); i++) {
			if(visited[graph[curr].get(i)]) {
				continue;
			}
			dfs(graph[curr].get(i));
		}
		
	}

}

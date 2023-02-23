package com.ssafy.a_basic.array;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_1260 {
	static int[][] arr;
	static int[] visited;
	static int n;
	static int m;
	static int start;
	static Queue<Integer> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = parseInt(tmp[0]); // 정점의 개수
		m = parseInt(tmp[1]); // 간선의 개수
		start = parseInt(tmp[2]); // 시작 노드 번호
		arr = new int[n+1][n+1];
		visited = new int[n+1]; // 방문 확인
		for(int i=0;i<m;i++) {
			tmp = br.readLine().split(" ");
			arr[parseInt(tmp[0])][parseInt(tmp[1])] = 1; // 2차원 배열로 그래프 완성
			arr[parseInt(tmp[1])][parseInt(tmp[0])] = 1;
		}
		dfs(start);
		System.out.println();
		visited = new int[n+1];
		bfs(start);
	}
	
	private static void dfs(int start) {
		System.out.print(start+" "); // 스택에 들어간 순서와 동일
		visited[start] = 1;  // 방문함
		
		for(int i=1;i<n+1;i++) {
			if(visited[i]==0 && arr[start][i]==1) {
				dfs(i);
			}
		}
	}
	private static void bfs(int start) {
		queue.add(start); // 시작 노드 큐에 삽입
		visited[start] = 1;
		System.out.print(start+" ");
		int x = -1;
		while(!queue.isEmpty()) {
			x = queue.poll();
			for(int i=1;i<=n;i++) {
				if(visited[i]==0 && arr[x][i]==1) {
					queue.add(i);
					visited[i]=1;
					System.out.print(i+" ");
				}
			}
		}
	}
}



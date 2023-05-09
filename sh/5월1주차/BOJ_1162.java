package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 도로포장 / 골드1 / 2시간 + a / 5월 9일 
 */
public class BJ_1162_도로포장 {
	static class Node {
		int node, cnt;
		long weight;
		
		public Node(int node, long weight, int cnt) {
			super();
			this.node = node;
			this.cnt = cnt;
			this.weight = weight;
		}
	}
	static int N, M, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Node>> road = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			road.add(new ArrayList<>());
		}
		for (int i = 1; i < M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int node = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken()); 
			// 도로 양방향 연결
			road.get(idx).add(new Node(node, cnt, 0));
			road.get(node).add(new Node(idx, cnt, 0));
		}
		
		System.out.println(dijkstra(1, road));
	}
	
	static long dijkstra(int start, ArrayList<ArrayList<Node>> road) {
		long[][] distance = new long[N+1][K+1];
		for (int i = 0; i < N+1; i++) 
			Arrays.fill(distance[i], Long.MAX_VALUE);
		
		distance[start][0] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.weight));
		pq.add(new Node(start, 0, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (cur.weight > distance[cur.node][cur.cnt]) continue;
			
			for (Node next : road.get(cur.node)) {
				
				
				// 도로를 포장했을 경우 => next 노드의 weight 값을 합산x
				if (cur.cnt < K && distance[next.node][cur.cnt+1] > distance[cur.node][cur.cnt]) {
					distance[next.node][cur.cnt+1] = distance[cur.node][cur.cnt];
					pq.add(new Node(next.node, distance[next.node][cur.cnt+1], cur.cnt+1));
				}
				
				// 도로를 포장하지 않았을 경우 => next 노드의 weight 값 합산
				if (distance[next.node][cur.cnt] > distance[cur.node][cur.cnt] + next.weight ) {
					distance[next.node][cur.cnt] = distance[cur.node][cur.cnt] + next.weight;
					pq.add(new Node(next.node, distance[next.node][cur.cnt], cur.cnt));
				}
			}
		}
		
		// java8 부터 지원
		// distance[][] 배열에서 최소값 찾아서 long타입으로 반환
		return Arrays.stream(distance[N]).min().getAsLong();
	}
}

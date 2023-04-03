/**
 * 파티 / 골드 3 / 70분
 * https://www.acmicpc.net/problem/1238
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_1238 {
	
	static int n,m,x; // 노드 개수, 다리 개수, 파티 장소
	static ArrayList<ArrayList<Node>> arr1 = new ArrayList<>(); // 인접 리스트
	static ArrayList<ArrayList<Node>> arr2 = new ArrayList<>(); // 인접 리스트	
	static int[] go;// 갈때의 거리
	static int[] back; // 올때의 거리
	
	static int answer = Integer.MIN_VALUE;
	
	static final int INF = Integer.MAX_VALUE;
	
	public static class Node implements Comparable<Node> {
		int to;
		int weight;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int[] dijkstra(ArrayList<ArrayList<Node>> arr) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		int[] distance = new int[n+1];
		boolean[] visited = new boolean[n+1];
		Arrays.fill(distance, INF);
		distance[x] = 0;
		
		
		pq.offer(new Node(x,0));
		
		while(!pq.isEmpty()) {
			int cur = pq.peek().to;
			int min = pq.peek().weight;
			pq.poll();
			
			if(visited[cur]) continue;
			
			visited[cur] = true;
			
			for(int i=0;i<arr.get(cur).size();i++) {
				Node tmp = arr.get(cur).get(i);
				
				if(!visited[tmp.to] && distance[tmp.to] > tmp.weight+distance[cur]) {
					distance[tmp.to] = tmp.weight+distance[cur];
					pq.offer(new Node(tmp.to,distance[tmp.to]));
				}
			}
		}
		
		return distance;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = parseInt(tmp[0]);
		m = parseInt(tmp[1]);
		x = parseInt(tmp[2]);
		
		for(int i=0;i<n+1;i++) {
			arr1.add(new ArrayList<>());
			arr2.add(new ArrayList<>());
		}
		
		// 인접리스트 초기화
		for(int i=0;i<m;i++) {
			tmp = br.readLine().split(" ");
			int start = parseInt(tmp[0]);
			int end = parseInt(tmp[1]);
			int weight = parseInt(tmp[2]);
			
			arr1.get(start).add(new Node(end,weight));
			arr2.get(end).add(new Node(start, weight));
		}
		
		go = dijkstra(arr1);
		back = dijkstra(arr2);
		
		for(int i=1;i<n+1;i++) {
			answer = Math.max(answer, go[i]+back[i]);
		}
		
		System.out.println(answer);
		
	}

}

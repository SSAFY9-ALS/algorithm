package march;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 파티 / 골드3 / 2시간+
 * https://www.acmicpc.net/problem/1238
 */

public class BOJ_1238 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // n 입력
		int m = Integer.parseInt(st.nextToken()); // m 입력
		int x = Integer.parseInt(st.nextToken()) - 1; // x 입력
		
		HashMap<Integer, HashMap<Integer, Integer>> departure = new HashMap<>(); // <출발, <도착, 시간>>의 값을 가지는 map
		HashMap<Integer, HashMap<Integer, Integer>> arrival = new HashMap<>(); // <도착, <출발, 시간>>의 값을 가지는 map -> 모든 정점에서 x로 가는 최단 경로를 찾기 위해
		
		int n1, n2, time;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			n1 = Integer.parseInt(st.nextToken()) - 1; // 시작점 입력
			n2 = Integer.parseInt(st.nextToken()) - 1; // 도착점 입력
			time = Integer.parseInt(st.nextToken()); // 소요 시간 입력
			
			// map에 값 add
			if(!departure.containsKey(n1)) {
				departure.put(n1, new HashMap<>());
			}
			departure.get(n1).put(n2, time);
			
			if(!arrival.containsKey(n2)) {
				arrival.put(n2, new HashMap<>());
			}
			arrival.get(n2).put(n1, time);
		}
		
		int[] start = new int[n]; // x까지 갈 때 최단 경로를 담을 배열
		int[] end = new int[n]; // x에서 마을로 돌아올 때 최단 경로를 담을 배열

		boolean[] visited; // 방문 여부 체킹 배열
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
			return o1[1] - o2[1];
		}); // 우선순위 큐 생성
		HashMap<Integer, Integer> list;
		int[] temp;
		int key, value;
		
		
		// 모든 마을에서 x로 가는 최단 경로를 찾는 다익스트라 
		visited = new boolean[n];
		Arrays.fill(start, Integer.MAX_VALUE);
		list = arrival.get(x);
		for(Map.Entry<Integer, Integer> entry: list.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();		
			queue.offer(new int[] {key, value});
			visited[key] = true;
			start[key] = value;
		}
		
		while(!queue.isEmpty()) {
			temp = queue.poll();
			list = arrival.get(temp[0]);
			if(list == null)
				continue;
			for(Map.Entry<Integer, Integer> entry: list.entrySet()) {
				key = entry.getKey();
				value = entry.getValue();
				if(start[key] > start[temp[0]] + value) {
					queue.offer(new int[] {key, start[temp[0]] + value});
					visited[key] = true;
					start[key] = start[temp[0]] + value;
				}
			}
		}
		
		
		// x에서 모든 마을로 가는 최단 경로를 찾는 다익스트라
		visited = new boolean[n];
		Arrays.fill(end, Integer.MAX_VALUE);
		queue.clear();
		list = departure.get(x);
		for(Map.Entry<Integer, Integer> entry: list.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();		
			queue.offer(new int[] {key, value});
			visited[key] = true;
			end[key] = value;
		}
		
		while(!queue.isEmpty()) {
			temp = queue.poll();
			list = departure.get(temp[0]);
			if(list == null)
				continue;
			for(Map.Entry<Integer, Integer> entry: list.entrySet()) {
				key = entry.getKey();
				value = entry.getValue();
				if(end[key] > end[temp[0]] + value) {
					queue.offer(new int[] {key, end[temp[0]] + value});
					visited[key] = true;
					end[key] = end[temp[0]] + value;
				}
			}
		}
		
		// 가장 오래 걸리는 학생을 찾음
		int max = 0;
		for(int i = 0; i < n; i++) {
			if(i == x)
				continue;
			if(start[i] + end[i] > max)
				max = start[i] + end[i];
		}
		
		System.out.println(max); // 결과 출력
	}
}
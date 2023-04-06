package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 최단경로 / 골드4 / 1시간 20분
 * https://www.acmicpc.net/problem/1753
 */

public class BOJ_1753 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int v = Integer.parseInt(st.nextToken()); // v 입력
		int e = Integer.parseInt(st.nextToken()); // e 입력
		int k = Integer.parseInt(in.readLine()); // k 입력
		
		// (u, v, w)를 담을 map 생성
		HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<Integer, HashMap<Integer, Integer>>();
		
		int n1, n2, w;
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(in.readLine());
			n1 = Integer.parseInt(st.nextToken()); // u 입력
			n2 = Integer.parseInt(st.nextToken()); // v 입력
			w = Integer.parseInt(st.nextToken()); // w 입력
			if(map.containsKey(n1)) { // n1이 키로 존재할 때
				if(map.get(n1).containsKey(n2)) { // n1에 연결된 map에 n2도 키로 존재할 때
					if(w < map.get(n1).get(n2)) // 현재 가중치가 이전에 저장되어 있는 가중치보다 작으면
						map.get(n1).put(n2, w); // 값 갱신
				}
				else { // n1에 연결된 map에 n2가 키로 존재하지 않을 때
					map.get(n1).put(n2, w); // 값 추가
				}
			}
			else { // n1이 key로 존재하지 않을 때
				map.put(n1, new HashMap<Integer, Integer>()); // n1를 키로 추가
				map.get(n1).put(n2, w); // n2를 키로 추가
			}
		}
		
		int[] result = new int[v+1]; // 결과를 담을 배열 생성
		for(int i = 1; i <= v; i++)
			result[i] = Integer.MAX_VALUE; // 초기값 할당
		result[k] = 0;
		
		// 다익스트라 알고리즘 수행을 진행할 우선순위 큐 생성
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((o1, o2) -> {
			return o1[1] - o2[1];
		});
		queue.add(new int[] {k, 0}); // 시작 노드, 시작 노드의 최단 경로(0)를 배열로 만들로 queue에 추가
		
		int[] node;
		HashMap<Integer, Integer> temp;
		while(!queue.isEmpty()) { // 큐가 비어 있지 않다면
			node = queue.poll(); // 하나 빼옴
			temp = map.get(node[0]); // 노드와 그에 연결된 노드들의 map 구함
			if(temp == null)
				continue;
			for(Map.Entry<Integer, Integer> entry: temp.entrySet()) {
				int key = entry.getKey(); // key 계산
				int value = entry.getValue(); // value 계산
				if(result[node[0]] + value < result[key]) { // 현재 탐색하는 노드에서 시작한 경로가 이미 저장된 최단 경로보다 짧으면
					result[key] = result[node[0]] + value; // 값 갱신
					queue.add(new int[] {key, result[key]}); // 큐에 추가
				}
			}
		}
		StringBuilder sb = new StringBuilder(); // 시간복잡도를 줄이기 위해 StringBuilder 사용
		for(int i = 1; i <= v; i++) {
			if(result[i] == Integer.MAX_VALUE) // 경로가 존재하지 않는 경우
				sb.append("INF\n"); // INF 출력
			else // 경로가 존재할 경우
				sb.append(result[i] + "\n"); // 최단 경로 출력
		}
		System.out.println(sb);
	}
}
package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * DFS와 BFS / 실버2 / 1시간
 * https://www.acmicpc.net/problem/1260
 */

public class BOJ_1260 {
	static String findRoute(HashMap<Integer, ArrayList<Integer>> map, boolean[] visited, int v, StringBuilder sb,
			char flag) {
		Stack<Integer> stack = new Stack<Integer>(); // 정점을 저장할 stack 선언
		stack.add(v);
		ArrayList<Integer> list;
		int n = 0;
		int size;
		while (stack.size() != 0) { // stack 사이즈가 0이 아닐 때까지 반복
			if (flag == 'D') // DFS면
				n = stack.pop(); // 뒤에서 가져옴
			else if (flag == 'B') // BFS면
				n = stack.remove(0); // 앞에서 가져옴
			if (visited[n]) // 이미 방문한 노드면 넘김
				continue;
			sb.append((n + 1) + " "); // 결과를 출력하기 위한 StringBuilder에 append
			visited[n] = true;
			if (map.get(n) == null) // key에 연결된 노드가 존재하지 않을 때 넘김
				continue;
			list = map.get(n);
			Collections.sort(list); // 정점이 여러 개인 경우 정점 번호가 작은 것부터 방문 -> sort 수행
			size = list.size();
			for (int i = 0; i < size; i++) {
				if (flag == 'D' && !visited[list.get(size - i - 1)]) // DFS는 뒤에서부터 빼오기 때문에 내림차순 순서로 입력되어야 함
					stack.add(list.get(size - i - 1));
				if (flag == 'B' && !visited[list.get(i)])
					stack.add(list.get(i)); // BFS는 앞에서부터 빼오기 때문에 오름차순 순서로 입력되어야 함
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		// 입력값들 받음
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken()) - 1;
		int key, value;
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>(); // key-value 쌍으로 구성된 map을 만들어 입력값 저장
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			key = Integer.parseInt(st.nextToken()) - 1;
			value = Integer.parseInt(st.nextToken()) - 1;
			
			// 간선은 양방향이기 때문에 2개의 입력값을 각각 key로 한 map 구성
			if (map.containsKey(key))
				map.get(key).add(value);
			else {
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(value);
				map.put(key, list);
			}
			if (map.containsKey(value))
				map.get(value).add(key);
			else {
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(key);
				map.put(value, list);
			}
		}
		// DFS, BFS 수행
		System.out.println(findRoute(map, new boolean[n], v, new StringBuilder(), 'D'));
		System.out.println(findRoute(map, new boolean[n], v, new StringBuilder(), 'B'));

	}
}

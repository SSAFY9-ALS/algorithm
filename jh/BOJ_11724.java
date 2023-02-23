package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 연결 요소의 개수 / 실버2 / 1시간
 * https://www.acmicpc.net/problem/11724
 */

public class BOJ_11724 {
	// 전체를 다 방문했는지 판단하는 함수
	static int isFinished(boolean[] visited) {
		for(int i = 0; i < visited.length; i++) {
			if(!visited[i])
				return i; // 아직 방문하지 않은 게 있다면 값 반환
		}
		return -1; // 없다면 -1 반환
	}
	
	static int findConnection(HashMap<Integer, ArrayList<Integer>> map, boolean[] visited, int first) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(first); // 초기값 stack에 넣어줌
		int n, flag;
		int count = 0;
		ArrayList<Integer> list;
		while(stack.size() >= 0) {
			if(stack.size() == 0) { // 현재 stack이 비어 있다면
				count++; // 하나의 연결 요소가 끝났다는 뜻 -> count 1 증가
				flag = isFinished(visited); // 방문하지 않는 노드가 있는지 확인
				if(flag > -1) // 남은 게 있다면
					stack.add(flag); // 다시 stack에 넣어줌
				else
					break;
			}
			else {
				n = stack.pop(); // 노드 하나 빼오기
				if(visited[n]) // 이미 방문한 곳이면
					continue; // 무시
				visited[n] = true; // 방문 처리
				list = map.get(n); // n에 연결된 리스트
				if(list == null) // 그 리스트가 없다면
					continue; // 무시
				for(int i = 0; i < list.size(); i++) { // 아니라면 리스트 요소 하나씩 확인해서
					if(!visited[list.get(i)]) // 이미 방문한 곳이 아니면
						stack.add(list.get(i)); // stack에 넣어줌
				}
			}
		}
		return count; // count 반환
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		// 입력값들 받음
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		// 연결된 노드 2개를 받을 변수
		int key = 0;
		int value;
		ArrayList<Integer> list;
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			key = Integer.parseInt(st.nextToken()) - 1; // 입력값 받음
			value = Integer.parseInt(st.nextToken()) - 1; // 입력값 받음
			// 받은 노드 2개를 map에 넣어줌
			if(map.containsKey(key))
				map.get(key).add(value);
			else {
				list = new ArrayList<Integer>();
				list.add(value);
				map.put(key, list);
			}
			if(map.containsKey(value))
				map.get(value).add(key);
			else {
				list = new ArrayList<Integer>();
				list.add(key);
				map.put(value, list);
			}
		}
		System.out.println(findConnection(map, new boolean[n], key));
	}
}
package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 바이러스 / 실버3 / 20분
 * https://www.acmicpc.net/problem/2606
 */

public class BOJ_2606 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine()); // n 입력
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>(); // 연결 요소를 담을 map 생성
		boolean[] visited = new boolean[n]; // 방문 여부를 담을 배열 생성
		int e = Integer.parseInt(in.readLine()); // e 입력
		int n1, n2, num;
		int count = 0;
		StringTokenizer st;
		ArrayList<Integer> list;
		Stack<Integer> stack = new Stack<Integer>(); // 연결 요소를 파악하기 위한 stack 생성
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(in.readLine());
			n1 = Integer.parseInt(st.nextToken()) - 1; // 연결 요소 n1 입력
			n2 = Integer.parseInt(st.nextToken()) - 1; // 연결 요소 n2 입력
			// 연결 요소들은 쌍으로 연결되었기 때문에 n1, n2 각각에 대해 map 생성
			if(map.containsKey(n1))
				map.get(n1).add(n2);
			else {
				map.put(n1, new ArrayList<Integer>());
				map.get(n1).add(n2);
			}
			if(map.containsKey(n2))
				map.get(n2).add(n1);
			else {
				map.put(n2, new ArrayList<Integer>());
				map.get(n2).add(n1);
			}
		}
		// 초기값 할당
		stack.add(0);
		visited[0] = true;
		while(stack.size() != 0) { // stack이 비어 있지 않다면 -> 이전 노드에 연결된 요소가 있을 때
			num = stack.pop(); // 하나를 pop으로 가져오고
			list = map.get(num); // 연결된 리스트 가져옴
			if(list != null) { // 리스트가 비어 있지 않다면 -> 연결된 요소가 있다면
				for(int i = 0; i < list.size(); i++) { // 각 요소에 대해 stack에 넣을지 말지를 정함
					num = list.get(i);
					if(!visited[num]) { // 아직 방문하지 않았다면
						count++; // count 1 증가
						visited[num] = true; // 방문한 것으로 바꿈
						stack.add(num); // stack에 add
					}
				}
			}
		}
		System.out.println(count); // 결과 출력
	}
}

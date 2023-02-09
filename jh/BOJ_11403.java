package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 경로 찾기 / 실버1 / 20분
 * https://www.acmicpc.net/problem/11403
 */

public class BOJ_11403 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>(); // 연결 요소 받을 map 생성
		int n = Integer.parseInt(in.readLine()); // n 입력
		int[][] result = new int[n][n]; // 결과를 담을 배열 생성
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				if(st.nextToken().equals("1")) { // 읽은 값이 1이면 -> 연결되어 있으면
					// 연결은 i -> j의 단방향 연결이기 때문에 i를 키로 하는 요소만 map에 넣어줌 
					if(map.containsKey(i))
						map.get(i).add(j);
					else {
						map.put(i, new ArrayList<Integer>());
						map.get(i).add(j);
					}
				}
			}
		}
		int num;
		Stack<Integer> stack;
		ArrayList<Integer> list;
		boolean[] visited;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				visited = new boolean[n]; // 각 값마다 방문 여부를 확인할 배열 행성
				visited[i] = true;
				stack = new Stack<Integer>(); // 각 값마다 연결요소를 담아둘 stack 생성
				stack.add(i);
				loopOut:
				while(stack.size() != 0) { // stack이 비어 있지 않다면 -> 연결 요소를 체크할 노드가 있다면
					num = stack.pop();
					list = map.get(num);
					if(list != null) {
						for(int k = 0; k < list.size(); k++) {
							if(list.get(k) == j) { // 현재 노드에 연결된 것이 j라면
								result[i][j] = 1; // result 값 갱신 후
								break loopOut; // 반복문 종료
							}
							else if(!visited[list.get(k)]) { // j가 아니지만 아직 방문하지 않았을 때
								visited[list.get(k)] = true; // 방문 여부를 바꾸고
								stack.add(list.get(k)); // stack에 넣어줌
							}
						}
					}
				}
			}
		}
		// 결과 출력
		for(int[] nums: result) {
			for(int r: nums)
				System.out.print(r + " ");
			System.out.println();
		}
	}
}

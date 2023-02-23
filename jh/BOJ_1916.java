package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 최소비용 구하기 / 골드5 / 2시간
 * https://www.acmicpc.net/problem/1916
 */

public class BOJ_1916 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine()); // n 입력
		int m = Integer.parseInt(in.readLine()); // m 입력
		StringTokenizer st;
		HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<Integer, HashMap<Integer, Integer>>(); // 버스 정보를 저장할 map 생성
		
		int key, value, cost; // 입력값을 저장할 변수 선언
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			key = Integer.parseInt(st.nextToken()); // 출발 도시 입력
			value = Integer.parseInt(st.nextToken()); // 도착 도시 입력
			cost = Integer.parseInt(st.nextToken()); // 비용 입력
			if (map.containsKey(key)) { // 이미 출발 도시에 대한 정보가 map에 있을 때
				if(map.get(key).containsKey(value)) { // 도착 도시에 대한 정보가 map에 있으면 -> 출발-도착으로 가는 버스가 여러 개 존재하면
					if(cost < map.get(key).get(value)) // 비용이 더 작은 것을 찾아서
						map.get(key).put(value, cost); // 값 입력해 줌
				}
				else // 도착 도시에 대한 정보가 없을 때
					map.get(key).put(value, cost); // map에 추가해 줌
			}
			else { // 출발 도시에 대한 정보가 없을 때
				map.put(key, new HashMap<Integer, Integer>()); // map에 추가해 줌
				map.get(key).put(value, cost); // 값 입력해 줌
			}
		}
		
		st = new StringTokenizer(in.readLine());
		int start = Integer.parseInt(st.nextToken()); // 시작 도시
		int goal = Integer.parseInt(st.nextToken()); // 도착 도시

		int[] result = new int[n + 1]; // 최소 비용 결과를 담을 배열
		for (int i = 1; i <= n; i++) {
			if (i != start)
				result[i] = Integer.MAX_VALUE; // 초기값 max 값으로 설정
		}

		Stack<Integer> stack = new Stack<Integer>(); // 다익스크라 알고리즘을 위한 stack 생성
		stack.add(start); // stack에 처음 도시 추가

		HashMap<Integer, Integer> temp;
		while(stack.size() != 0) { // stack에 탐색할 도시가 남아 있다면
			int num = stack.pop(); // 도시 빼옴
			temp = map.get(num); // 도시에 연결된 map 정보 출력
			if(temp == null) // map이 null이면 -> 연결된 도시가 없다면
				continue; // 다음으로 넘어감
			for(Map.Entry<Integer, Integer> entry: temp.entrySet()) { // 도시에 연결된 도착 도시들 하나씩 보기 위해 반복
				int idx = entry.getKey();
				int c = entry.getValue();
				if(c > result[idx]) // 도착 도시의 비용보다 현재 보고 있는 비용이 크면
					continue; // 다음으로 넘어감
				if(result[num] + c < result[idx]) { // 도착 도시의 비용보다 현재 보고 있는 비용이 작으면
					result[idx] = result[num] + c; // 값 갱신
					stack.add(idx); // 연결된 도시들도 다시 확인해야 함 -> stack에 넣어줌
				}
			}
		}
		
		System.out.println(result[goal]); // 도착 도시의 최소 비용 출력
	}
}
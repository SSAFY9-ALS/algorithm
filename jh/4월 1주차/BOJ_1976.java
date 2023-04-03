package march;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 여행 가자 / 골드4 / 40분
 * https://www.acmicpc.net/problem/1976
 */

public class BOJ_1976 {
	static int n;
	static HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
	
	// 가능한 루트인지 판별하는 메서드
	static boolean isValidRoute(int start, int end) {
		ArrayDeque<Integer> queue = new ArrayDeque<>(); // BFS를 위한 큐 생성
		boolean[] visited = new boolean[n]; // 방문 위치 판별을 위한 배열
		HashSet<Integer> set;
		
		// 시작 도시 먼저 넣어줌
		queue.offer(start);
		visited[start] = true;
		
		int waypoint;
		while(!queue.isEmpty()) { // 큐가 비어 있지 않다면
			waypoint = queue.poll(); // 하나 빼옴
			set = map.get(waypoint); // 빼온 도시를 key로 하는 set 가져옴
			for(int w: set) { // 연결된 도시 하나씩 탐색
				if(w == end) // 도착 도시면
					return true; // true 리턴
				if(!visited[w]) { // 도착 도시가 아닌데 방문하지 않은 도시면 
					queue.offer(w); // 큐에 넣어줌
					visited[w] = true; // 방문 처리
				}
			}
		}
		return false; // 여기까지 오면 도착 도시로 가는 경로가 없는 것 -> false 리턴
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(in.readLine());
		int m = Integer.parseInt(in.readLine());
		
		int num;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			map.put(i, new HashSet<>()); // i번 도시에 대한 map 생성
			map.get(i).add(i); // 자기 자신을 추가
			for(int j = 0; j < n; j++) {
				num = Integer.parseInt(st.nextToken());
				if(num == 0) // 0이면 연결되지 않은 것
					continue; // 넘어감
				map.get(i).add(j); // 아니면 map에 추가
			}
		}
		
		String result = "YES";
		int start, end;
		st = new StringTokenizer(in.readLine());
		start = Integer.parseInt(st.nextToken()) - 1; // 초기 시작 도시 가져옴
		
		for(int i = 1; i < m; i++) {
			end = Integer.parseInt(st.nextToken()) - 1; // 다음 도시 가져옴
			if(result.equals("NO")) // 입력을 받으면서 동시에 판별하기 때문에 전체 입력을 위해 종료시키면 안 됨 -> 이미 불가능한 경우 함수 호출을 하지 않도록 작성
				continue;
			if(!isValidRoute(start, end)) // 가능한 루트인지 판별하는 함수 호출 -> 불가능하면
				result = "NO"; // 결과 설정
			start = end; // 도착 위치를 시작으로 만들어 다음 탐색 진행
		}
		
		System.out.println(result); // 결과 출력
	}
}

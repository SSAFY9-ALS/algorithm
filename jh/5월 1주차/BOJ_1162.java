package may;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 도로포장 / 골드1 / 2시간 30분 / 5월 11일
 * https://www.acmicpc.net/problem/1162
 */

public class BOJ_1162 {
	// 도로 구조체
	static class Pave {
		int city;
		int count;
		long time;
		
		public Pave(int city, long time) {
			this.city = city;
			this.time = time;
		}

		public Pave(int city, int count, long time) {
			super();
			this.city = city;
			this.count = count;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, ArrayList<Pave>> pave = new HashMap<>();
		
		// 도로 정보 입력
		int p1, p2, v;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			p1 = Integer.parseInt(st.nextToken());
			p2 = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			
			if(!pave.containsKey(p1)) {
				pave.put(p1, new ArrayList<>());
				pave.get(p1).add(new Pave(p2, v));
			}
			else
				pave.get(p1).add(new Pave(p2, v));
			if(!pave.containsKey(p2)) {
				pave.put(p2, new ArrayList<>());
				pave.get(p2).add(new Pave(p1, v));
			}
			else
				pave.get(p2).add(new Pave(p1, v));
		}
		
		PriorityQueue<Pave> queue = new PriorityQueue<>((o1, o2) -> {
			return Long.compare(o1.time, o2.time);
		});
		long[][] minRoute = new long[n+1][k+1]; // minRoute[i][j] => i번째 도시에서 j개 도로를 포장했을 때의 최소 시간
		
		// 배열 초기화
		for(long[] mr: minRoute)
			Arrays.fill(mr, Long.MAX_VALUE - 1000001);
		
		minRoute[1][0] = 0;
		queue.offer(new Pave(1, 0, 0));
		
		int city;
		long d;
		Pave p;
		ArrayList<Pave> list;
		
		loop:
		while(!queue.isEmpty()) { // 큐가 비어 있지 않을 때
			p = queue.poll(); // 하나 빼옴
			list = pave.get(p.city);
			
			// 탐색이 필요하지 않는 경우 넘어감
			if(minRoute[p.city][p.count] < p.time)
				continue;
			if(list == null)
				continue;
			
			// 도로로 연결된 도시 탐색 -> 다익스트라 사용
			for(Pave road: list) {
				city = road.city; 
				d = p.time + road.time;
				
				if(minRoute[city][p.count] < p.time)
					continue;
				
				// 현재 도로를 포장하지 않을 경우
				if(d < minRoute[city][p.count]) {
					minRoute[city][p.count] = d;
					queue.offer(new Pave(city, p.count, d));
					if(city == n && minRoute[city][p.count] < minRoute[city][k])
						minRoute[city][k] = minRoute[city][p.count];
				}
				// 현재 도로를 포장할 경우
				if(p.count < k && p.time < minRoute[city][p.count+1]) {
					if(city == n && p.time == 0) {
						minRoute[city][k] = 0;
						break loop;
					}
					minRoute[city][p.count+1] = p.time;
					queue.offer(new Pave(city, p.count+1, p.time));
					if(city == n && minRoute[city][p.count+1] < minRoute[city][k])
						minRoute[city][k] = minRoute[city][p.count+1];
				}
			}
		}
		
		System.out.println(minRoute[n][k]); // 결과 출력
	}
}

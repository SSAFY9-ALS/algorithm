package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 보석 도둑 / 골드 2 / 129분
 * @author 민정인
 * https://www.acmicpc.net/problem/1202
 */
 
public class BOJ_1202 {
	static class Jew {	// 보석 정보 클래스
		int w;
		int v;
		public Jew(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		ArrayList<Jew> list = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Jew(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		ArrayList<Integer> bag = new ArrayList<>();
		for(int i = 0; i < k; i++) {
			bag.add(Integer.parseInt(br.readLine()));
		}
		Collections.sort(list, new Comparator<Jew>() {	 // 보석 무게 오름차순 정렬
			@Override
			public int compare(Jew o1, Jew o2) {
				return o1.w - o2.w;
			}
		});
		Collections.sort(bag);	// 가방 오름차순 정렬
		long result = 0;
		PriorityQueue<Jew> pq = new PriorityQueue<>(new Comparator<Jew>() {	// 보석 가치 내림차순 정렬
			@Override
			public int compare(Jew o1, Jew o2) {
				return o2.v - o1.v;
			}
		});
		int idx = 0;
		for(int i = 0; i < k; i++) {
			int cur = bag.get(i);
			while(idx < n && list.get(idx).w <=cur) {	// 해당 가방이 버티는 무게보다 작거나 같은 경우까지 pq에 추가
				Jew tmp = list.get(idx++);
				pq.add(new Jew(tmp.w, tmp.v));
			}
//			for(int j = idx; j < list.size(); j++) {
//				if(list.get(j).w <= cur) {
//					Jew tmp = list.get(j);
//					pq.add(new Jew(tmp.w, tmp.v));
//				} else {
//					idx = j;
//					break;
//				}
//			}
			if(!pq.isEmpty()) {		// pq의 가장 앞 보석의 가치를 더함
				result += pq.poll().v;
			}
		}
		System.out.println(result);
	}
}

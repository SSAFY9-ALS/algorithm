package march;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 보석 도둑 / 골드2 / 1시간
 * https://www.acmicpc.net/problem/1202
 */

public class BOJ_1202 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// 보석 정보 입력
		int[][] jewels = new int[n][2];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			jewels[i][0] = Integer.parseInt(st.nextToken());
			jewels[i][1] = Integer.parseInt(st.nextToken());
		}
		// 무게의 오름차순으로, 무게가 같다면 가격의 내림차순으로 정렬
		Arrays.sort(jewels, (o1, o2) -> {
			if(o1[0] == o2[0])
				return o2[1] - o1[1];
			return o1[0] - o2[0];
		});
		
		// 가방 정보 입력
		int[] bags = new int[k];
		for(int i = 0; i < k; i++)
			bags[i] = Integer.parseInt(in.readLine());		
		Arrays.sort(bags); // 가방 무게 오름차순으로 정렬

		long ans = 0l;
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder()); // 보석 무게의 내림차순으로 정렬하는 우선순위 큐
		int idx = 0;
		
		// 가방에 들어갈 수 있는 보석들의 목록을 구하는 반복문
		for(int bag: bags) { // 가방을 앞에서부터 하나씩 탐색하면서
			while(idx < n) {
				if(jewels[idx][0] > bag) // 현재 보석 무게가 가방 무게보다 크다면
					break; // 반복문 종료
				queue.offer(jewels[idx][1]); // 아니면 우선순위 큐에 넣어줌
				idx++;
			}
			if(!queue.isEmpty()) { // 큐가 비어 있지 않다면
				ans += queue.poll(); // 우선순위 큐에서 하나 빼와서 결과 값에 add
				// 우선순위 큐에 들어 있는 값은 가방에 들어갈 수 있는 보석들의 가치임 -> 내림차순으로 정렬되었기 때문에 하나를 빼면 현재 가방에 들어갈 수 있는 최대 가격이 됨
			}
		}
		System.out.println(ans); // 결과 출력
	}
}

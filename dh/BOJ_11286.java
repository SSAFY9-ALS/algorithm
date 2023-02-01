package algorithm;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		PriorityQueue<Integer> p_pq = new PriorityQueue<>(); // 오름차순
		PriorityQueue<Integer> n_pq = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순
		for (int i = 0; i < n; i++) {
			int tmp = sc.nextInt();
			if (tmp == 0) {
				if (p_pq.isEmpty() && n_pq.isEmpty()) { // 두 큐가 비어있으면 0 출력
					System.out.println(0);
					continue;
				} else if (p_pq.isEmpty()) {
					System.out.println(n_pq.poll());
				} else if (n_pq.isEmpty()) {
					System.out.println(p_pq.poll()); // 한 큐가 비어있으면 나머지 큐에 있는 값 출력
				} else {
					if (Math.abs(p_pq.peek()) < Math.abs(n_pq.peek())) { // 절댓값이 작은 값 출력
						System.out.println(p_pq.poll());
					} else {
						System.out.println(n_pq.poll());
					}
				}

			} else {
				if (tmp > 0) { // 양수 음수 구분하여 큐에 저장
					p_pq.add(tmp);
				} else {
					n_pq.add(tmp);
				}
			}
		}
	}
}


// https://www.acmicpc.net/problem/11286


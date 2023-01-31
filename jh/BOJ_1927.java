package algorithm;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1927 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int n;
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(); // 값을 뽑을 때 미리 정한 기준으로 값을 뽑아오는 우선순위 큐 이용
		for(int tc = 0; tc < T; tc++) {
			n = sc.nextInt();
			if(n == 0) { // 0이 입력됐을 때
				if(heap.size() == 0) // size가 0이면
					System.out.println("0"); // 0 출력
				else // 아니면
					System.out.println(heap.poll()); // 가장 작은 값 출력
			}
			else // 0이 아닌 수가 입력됐을 때
				heap.add(n); // heap에 추가
		}
	}
}

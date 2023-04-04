package algorithm;
/* 
 * N번째 큰 수 / 실버2 / 15분
 * https://www.acmicpc.net/problem/2075
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				pq.add(sc.nextInt());
			}
		}
		
		for(int i = 0; i < n-1; i++) {
			pq.poll();
		}
		System.out.println(pq.poll());
	}
}
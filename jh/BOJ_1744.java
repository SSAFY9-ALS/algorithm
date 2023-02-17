package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 수 묶기 / 골드4 / 1시간
 * https://www.acmicpc.net/problem/1744
 */

public class BOJ_1744 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine()); // n 입력
		PriorityQueue<Integer> positive = new PriorityQueue<Integer>((o1, o2) -> {
			return o2 - o1;
		}); // 입력값 중 양수를 저장하는 큐 -> 내림차순으로 정렬
		PriorityQueue<Integer> negative = new PriorityQueue<Integer>(); // 입력값 중 음수를 저장하는 큐 -> 오름차순으로 정렬
		Stack<Integer> zero = new Stack<Integer>(); // 입력값 중 0을 저장하는 스택
		int num;
		for(int i = 0; i < n; i++) {
			num = Integer.parseInt(in.readLine()); // 수열 입력
			if(num > 0) // 양수라면
				positive.add(num); // positive에 저장
			else if(num == 0) // 0이면
				zero.add(num); // zero에 저장
			else // 음수면
				negative.add(num); // negative에 저장
		}
		int sum = 0; // 합을 담는 변수
		int num1, num2;
		
		// 최댓값이 되려면?
		// 양수(1이 아닐 때): 가장 큰수 * 두 번째로 큰 수일때 최댓값
		// 양수(1일 때): 1을 묶지 않고 더해야 최댓값 ex) 1 * 4 = 4, 1 + 4 = 5
		while(!positive.isEmpty()) { // positive가 비어 있지 않을 때
			num1 = positive.poll(); // 남은 것 중 가장 큰 수 뽑음
			if(positive.size() == 0 || num1 == 1) // 이후 size가 0이거나 num이 1일 때
				sum += num1; // 묶지 않고 더함
			else { // 아닌 경우 -> 묶는 경우를 고려해야 함
				num2 = positive.poll(); // 남은 것 중 가장 큰 수 뽑음
				sum += num2 == 1? num1 + num2: num1 * num2; // 뽑은 게 1인지 아닌지에 따라 sum에 더해줌
			}
		}
		
		// 최댓값이 되려면?
		// 음수(묶이는 경우): 가장 작은 수 * 두 번째로 작은 수 일때 최댓값 ex) -4 * -3 = 12, -1 * -2 = 2
		// 음수(묶이지 않은 경우): 음수 * 0일 때 최댓값
		while(negative.size() > 1) { // negative가 비어 있지 않을 때
			sum += negative.poll() * negative.poll(); // 남은 것 중 가낭 작은 것 두 개를 뽑아 묶어서 더해줌
		}
		if(!negative.isEmpty() && zero.isEmpty()) // negative에서 묶이지 않은 것이 있고, 입력값 중 0이 없을 때
			sum += negative.poll(); // negative 남은 것을 뽑아 sum에 더해줌
		System.out.println(sum); // 결과 출력
	}
}

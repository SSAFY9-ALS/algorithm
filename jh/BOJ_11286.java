package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 절댓값 힙 / 실버1 / 20분
 * https://www.acmicpc.net/problem/11286
 */

public class BOJ_11286 {
	public static void main(String[] args) throws IOException {
		int num, numAbs;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 우선순위 설정 절댓값 기준 오름차순, 절댓값이 같다면 기존값 기준 오름차순
		PriorityQueue<int[]> list = new PriorityQueue<>((o1, o2) -> {
			if(o1[0] == o2[0])
				return o1[1] - o2[1];
			return o1[0] - o2[0];
		});
		
		for(int i = 0 ; i < n; i++) {
			num = Integer.parseInt(br.readLine());
			if(num != 0) {
				numAbs = Math.abs(num);
				list.add(new int[] {numAbs, num});
			}
			else {
				if(list.size() == 0)
					System.out.println("0");
				else
				 System.out.println(list.poll()[1]);
			}
		}
	}
}

/**
 * 줄 세우기 / 골드 3 / 50분
 * https://www.acmicpc.net/problem/2252
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_2252 {
	
	static int n;
	static int m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] tmp = br.readLine().split(" ");
		
		n = parseInt(tmp[0]);
		m = parseInt(tmp[1]);
		
		// 진입차수 배열
		int[] inCount = new int[n+1];
		// 그래프
		ArrayList<Integer>[] arr = new ArrayList[n+1];
		for(int i=1;i<n+1;i++) {
			arr[i] = new ArrayList<>();
		}
		
		// 진입차수 & 그래프 입력
		for(int i=0;i<m;i++) {
			tmp = br.readLine().split(" ");
			arr[parseInt(tmp[0])].add(parseInt(tmp[1]));
			inCount[parseInt(tmp[1])]++;
		}
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		// 진입 차수가 0인 노드 큐에 넣기
		for(int i=1;i<n+1;i++) {
			if(inCount[i]==0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			// 뺀 학생번호 출력
			sb.append(now).append(" ");
			
			for(int i=0;i<arr[now].size();i++) {
				// 뺀 학생 뒤에 서야하는학생
				int back = arr[now].get(i);
				// 간선 -- 해주고 간서의 개수가 0이면 큐에 넣음
				inCount[back]--;
				if(inCount[back]==0) {
					queue.offer(back);
				}
			}
		}
		
		System.out.println(sb);
	}

}

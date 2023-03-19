package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 줄 세우기 / 골드 3/ 78분
 * @author 민정인
 * https://www.acmicpc.net/problem/2252
 */

public class BOJ_2252 {
	static int n;
	static ArrayList<Integer>[] list;
	static StringBuilder sb = new StringBuilder();
	static int[] count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		list = new ArrayList[n + 1];
		count = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < m; i++) {	// 뒤에 오는 번호들 저장 및 뒷 순서의 번호가 자신이 저장된 횟수 저장
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			list[first].add(second);
			count[second]++;
		}
		ArrayList<Integer> result = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i <= n; i++) {	// 저장된 횟수가 0 : 여기서 출발한다는 의미(앞에 입력된 순서 번호 외에 무엇이 와도 상관없음)
			if(count[i] == 0) {
				q.add(i);
			}
		}
		while(!q.isEmpty()) {
			int tmp = q.poll();
			result.add(tmp);	// 들어오는 값들은 전부 연결된 횟수가 0인 값들 : 즉 지금 들어와도 상관없는 값
			for(int i = 0; i < list[tmp].size(); i++) {	// 뒤에 연결된 값들의 횟수 감소 및 횟수 0이 된 값 큐에 입력
				int next = list[tmp].get(i);
				count[next]--;
				if(count[next] == 0) {
					q.add(next);
				}
			}
		}
		for(int i = 0; i < result.size(); i++) {
			sb.append(result.get(i)).append(" ");
		}
		System.out.println(sb.toString());
	}
}

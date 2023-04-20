package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 공항 / 골드2 / 1시간 
 * https://www.acmicpc.net/problem/10775
 */

public class BOJ_10775 {
	static int[] airport;
	
	// 내 바로 직전 비어 있는 값을 찾는 메서드
	static int findRoot(int a) {
		if(airport[a] == a)
			return a;
		return airport[a] = findRoot(airport[a]);
	}
	
	// 바로 직전의 비어 있는 값을 합치는 메서드
	static void union(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		airport[bRoot] = aRoot;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int g = Integer.parseInt(in.readLine());
		int p = Integer.parseInt(in.readLine());

		airport = new int[g+1]; // 내 바로 직전 비어 있는 값을 가리킴
		for(int i = 1; i <= g; i++)
			airport[i] = i;
		
		int n, prev;
		int cnt = 0;
		for(int i = 0; i < p; i++) {
			n = Integer.parseInt(in.readLine());
			prev = findRoot(n); // union이 일어날 때 대상이 되는 원소만 바뀌기 때문에 갱신이 필요
			if(prev == 0) // 비행기가 어느 게이트에도 도킹할 수 없을 때
				break; // 종료
			union(airport[n]-1, airport[n]); // 값을 합쳐줌
			cnt++; // 개수 1 증가
		}
		
		System.out.println(cnt); // 결과 출력
	}
}

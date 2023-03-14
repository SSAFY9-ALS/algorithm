/**
 * 거짓말 / 골드 4 / 120분
 * https://www.acmicpc.net/problem/1043
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_1043 {
	
	static int N,M,K; // 총 사람수, 파티의 개수, 초기 진실을 아는 사람 수
	static ArrayList<Integer>[] arr; // 파티에 오는 사람 정보
	static int[] parents; // 서로소 집합을 위한 부모 배열
	static boolean[] knowTruth; // 진실을 아는 사람들
	static int answer;
	
	static int findSet(int a) {
		if(parents[a]==a) {
			return a;
		}
		return findSet(parents[a]);
	}

	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot != bRoot) {
			if(aRoot>bRoot) {
				parents[aRoot] = bRoot;
			}
			else {
				parents[bRoot] = aRoot;				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String[] tmp = br.readLine().split(" ");
		N = parseInt(tmp[0]);
		M = parseInt(tmp[1]); // 사람수, 파티의 수 입력
		
		parents = new int[N+1];
		knowTruth = new boolean[N+1];
		for(int i=0;i<N+1;i++) { // union-find 초기화
			parents[i] =i;
		}
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		for(int i=0; i<n; i++) {
			knowTruth[Integer.parseInt(st.nextToken())] = true;
		}
		
		arr = new ArrayList[M];
		for(int i=0; i<M; i++) {
			arr[i] = new ArrayList<>();
		}
		
		int value, pre =0;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			if(n > 0) {
				pre = Integer.parseInt(st.nextToken());
				arr[i].add(pre);
			}
			for(int j=1; j<n; j++) {
				value = Integer.parseInt(st.nextToken());
				arr[i].add(value);
				union(pre, value); // 두명씩 union하면 모두가 같은 parent를 갖게 됨.
				pre = value;
			}
		}
		
		int parent;
		for(int i=1; i<knowTruth.length; i++) {
			if(knowTruth[i]) {
				knowTruth[findSet(i)] = true;
			}
		}
		
		for(int i=0; i<M; i++) {
			if(arr[i].size() > 0) {
				parent = findSet(arr[i].get(0));
				if(!knowTruth[parent]) answer++;
			}
		}

		// 6. 거짓말 할 수 있는 파티 최대 수 출력
		System.out.println(answer);
		
	}

}

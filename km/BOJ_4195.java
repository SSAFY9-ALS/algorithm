/**
 * 친구 네트워크 / 골드 2 / 60분
 * https://www.acmicpc.net/problem/4195
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_4195 {
	
	static int f;
	static int[] parent; // 서로소 집합 부모 배열
	static int[] level; // 연결된 친구 수
	
	static int findSet(int a) { // 부모 찾음
		if(a == parent[a]) {
			return a;
		}
		return parent[a]=findSet(parent[a]);
	}
	
	static int union(int a, int b) { // 
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot != bRoot) {
			parent[bRoot] = aRoot;
			level[aRoot] += level[bRoot]; // 연결되면 레벨수 더해서 친구수 추가해줌
		}
		return level[aRoot];
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = parseInt(br.readLine());
		
		for(int t=0;t<tc;t++) {
			f = parseInt(br.readLine());
			
			parent = new int[f*2];
			level = new int[f*2];
			for(int i=0;i<f*2;i++) {
				parent[i] = i; // 부모 배열 초기화
				level[i] = 1; // 본인만 알고 있으므로 1로 초기화
			}
			
			int num = 0;
			HashMap<String, Integer> arr = new HashMap<>();
			
			for(int i=0;i<f;i++) {
				String [] tmp = br.readLine().split(" ");
				
				// 입력받은 사람 없으면 인덱스 늘리면서 추가
				
				if(!arr.containsKey(tmp[0])) {
					arr.put(tmp[0], num++);
				}
				if(!arr.containsKey(tmp[1])) {
					arr.put(tmp[1], num++);
				}
				
				sb.append(union(arr.get(tmp[0]), arr.get(tmp[1]))).append("\n");
			}
		}
		
		System.out.println(sb);

	}

}

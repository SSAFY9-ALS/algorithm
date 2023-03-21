package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 친구 네트워크 / 골드 2 / 111분(솔루션 참고)
 * @author 민정인
 * https://www.acmicpc.net/problem/4195
 */

public class BOJ_4195 {
	static int[] parents;
	static int[] val;
	static Map<String, Integer> map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < test; i++) {
			parents = new int[200000];
			val = new int[200000];
			Arrays.fill(val, 1);
			map = new HashMap<>();
			int idx = 0;
			int conn = Integer.parseInt(br.readLine());
			for(int j = 0; j < conn; j++) {
				st = new StringTokenizer(br.readLine());
				String start = st.nextToken();
				String end = st.nextToken();
				if(map.get(start) == null) {	// 각 이름의 index 저장
					map.put(start, idx);
					parents[idx] = idx++;
				}
				if(map.get(end) == null) {
					map.put(end, idx);
					parents[idx] = idx++;
				}
				sb.append(union(map.get(start), map.get(end)) + "\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	static int findR(int i) {	// 부모 찾기
		if(parents[i] == i) {
			return i;
		}
		return parents[i] = findR(parents[i]);
	}
	static int union(int a, int b) {	// 합집합(합집합 연산에서 오류로 인해 실패. 이후 솔루션 확인하여 수정)
		int parA = findR(a);
		int parB = findR(b);
		if(parA != parB) {
			parents[parB] = parA;
			val[parA] += val[parB];	// 최상단 부모의 값에 대해 현재 연결된 수를 더함
			val[parB] = 1;
		}
		return val[parA];
	}
}

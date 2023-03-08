package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 0 만들기 / 골드 5 / 39분
 * @author 민정인
 * https://www.acmicpc.net/problem/7490
 */

public class BOJ_7490 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<String> result = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			int val = Integer.parseInt(br.readLine());
			char[] arr = new char[val - 1];
			dfs(val, 0, arr);
			Collections.sort(list);
			for(int j = 0; j < list.size(); j++) {
				result.add(list.get(j));
			}
			result.add("\n");
			list.clear();
		}
		for(int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i));
		}
	}
	static ArrayList<String> list = new ArrayList<>();
	static void dfs(int val, int idx, char[] arr) {	// 중복순열 진행
		if(idx == val - 1) {						// +, -, 빈칸 순열 완성시 진행
			int result = 1;
			for(int i = 0; i < val - 1; i++) {
				switch(arr[i]) {
				case '+':
					result += i + 2;
					break;
				case '-':
					result -= i + 2;
					break;
				case ' ':		// 빈칸일 경우 빈칸이 연속되는 동안의 값 저장
					int tmp = 0;
					int num = i + 1;
					int j = -1;
					for(j = i; j < val - 1; j++) {
						if(arr[j] != ' ') {
							break;
						}
					}
					for(int k = j - i; k >= 0; k--) {
						tmp += num++ * Math.pow(10, k);
					}
					if(i == 0) {	// 최초값일 경우 초기 result 변경
						result = tmp;
						break;
					}
					if(arr[i - 1] == '-') {	// 이후일 경우 -는 기존에 빼준 값을 되돌리고 다시 빼기
						result += i + 1;
						result -= tmp;
					} else {				// +는 반대로
						result -= i + 1;
						result += tmp;
					}
					break;
				}
			}
			if(result == 0) {
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < val - 1; i++) {
					sb.append(i + 1);
					sb.append(arr[i]);
				}
				sb.append(val + "\n");
				list.add(sb.toString());
			}
			return;
		}
		arr[idx] = '+';			// 중복순열 진행 재귀
		dfs(val, idx + 1, arr);
		arr[idx] = '-';
		dfs(val, idx + 1, arr);
		arr[idx] = ' ';
		dfs(val, idx + 1, arr);
	}
}

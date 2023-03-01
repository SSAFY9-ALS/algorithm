package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 단어 수학 / 골드 4 / 86분
 * @author 민정인
 * https://www.acmicpc.net/problem/1339
 */

public class BOJ_1339 {
	static int n;
	static Map<Character, Integer> map = new HashMap<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String[] arr = new String[n];
		int[] alpha = new int[26];		// 각 알파벳들이 차지하는 양
		ArrayList<Integer> list = new ArrayList<>();	// 사용된 알파벳 정보
		for(int i = 0; i < n; i++) {
			arr[i] = br.readLine();
			for(int j = 0; j < arr[i].length(); j++) {
				int idx = arr[i].charAt(j) - 'A';
				if(list.indexOf(idx) == -1) {	// 알파벳 정보 추가
					list.add(idx);
				}
				alpha[idx] += (int) Math.pow(10, arr[i].length() - j - 1);	// 알파벳 차지하는 양 증가
			}
		}
		int num = 9;
		while(!list.isEmpty()) {		// 모든 알파벳들 중 가장 많은 비중을 차지하는 알파벳부터 9~0 순으로 map에 추가
			int maxVal = Integer.MIN_VALUE;
			int idx = -1;
			for(int i = 0; i < list.size(); i++) {
				if(alpha[list.get(i)] > maxVal) {
					maxVal = alpha[list.get(i)];
					idx = i;
				}
			}
			map.put((char) ('A' + list.get(idx)), num--);
			list.remove(idx);
		}
		int result = 0;
		for(int i = 0; i < n; i++) {
			result += changeNum(arr[i]);
		}
		System.out.println(result);
	}
	static int changeNum(String s) {	// 맵의 문자 정보에 따른 숫자에 따라 값 반환
		int sum = 0;
		int mul = 1;
		for(int i = s.length() - 1; i >= 0; i--) {
			sum += map.get(s.charAt(i)) * mul;
			mul *= 10;
		}
		return sum;
	}
}

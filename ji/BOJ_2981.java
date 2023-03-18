package net.acmicpc;

import java.util.*;
import java.io.*;

/**
 * 검문 / 골드 4 / 105분
 * @author 민정인
 * https://www.acmicpc.net/problem/2981
 */

public class BOJ_2981 {
	static ArrayList<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			list.add(num);
		}
		Collections.sort(list);
		int val = list.get(1);	// 나올 수 있는 최대의 검문값은 오름차순 중 두번째 수 - 1(이후로는 첫번째 수와 두번째 수 자신이 나머지가 되기 때문에 불가능
		int result = 1;
		for(int i = val; i >= 2; i--) {
			if(chk(i, 1, list.get(0) % i)) {	// 선택한 값, 시작 인덱스, 나와야 하는 나머지
				result = i;	// 통과했다면 해당 값이 최대값이므로 정지
				break;
			}
		}
		ArrayList<Integer> ans = new ArrayList<>();
		for(int i = 2; i <= Math.sqrt(result); i++) {	// 약수 구하기. 1은 제외하므로 2부터 약수들 저장
			if(result % i == 0) {
				ans.add(i);
				if(result / i != i) {					
					ans.add(result / i);
				}
			}
		}
		Collections.sort(ans);	// 정렬
		ans.add(result);	// 자기자신도 포함되어야 하므로 마지막에 추가
		for(int i = 0; i < ans.size(); i++) {
			sb.append(ans.get(i)).append(" ");
		}
		System.out.println(sb.toString());	// 결과 출력
	}
	
	static boolean chk(int val, int idx, int mod) {	// 나머지가 동일하지 않은 경우 발생시 해당 경우는 무조건 정지
		if(list.get(idx) % val != mod) {
			return false;
		}
		if(idx == list.size() - 1 && list.get(idx) % val == mod) {	// 마지막 인덱스 도착 및 나머지 동일하면 해당 값이 최대인 M이 됨
			return true;
		}
		return chk(val, idx + 1, mod);	// 재
	}
}

package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 가장 긴 증가하는 부분 순열 2 / 골드 2 / 76분(솔루션 참고)
 * -> 풀이방식
 * 값들을 하나씩 입력받고, 이를 배열의 끝에 넣음
 * -> 여기서 배열의 끝보다 입력값이 작은 경우, 해당 값보다 큰 값 중 가장 작은 값을 대체함
 * -> 해당 위치를 찾는 방법: 이분 탐색 응용
 * @author 민정인
 * https://www.acmicpc.net/problem/12015
 */


public class BOJ_12015 {
	static List<Integer> list;
	static void changeVal(int val) {	// 값을 대체하는 함수
		int lo = 0;
		int hi = list.size();
		while(lo < hi) {				// lo가 hi보다 작은 경우 반복
			int mid = (lo + hi) / 2;	// 중간값 지정
			if(list.get(mid) < val) {	// 대체하려는 값이 현재 위치의 값보다 작으면
				lo = mid + 1;			// lo값을 mid + 1 로 변경
			} else {					// 대체하려는 값보다 크다면
				hi = mid;				// hi를 mid로 변경(점차 아래로 내려야 하므로)
			}							// 또한 현재 동일한 값을 찾는 것이 아닌
		}								// 자신보다 큰 수 중 가장 작은 값을 찾으므로 hi = mid
		list.set(lo, val);				// 최종적으로 나온 lo(hi와 같은 위치)를 대체
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		list = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			int val = Integer.parseInt(st.nextToken());
			if(list.isEmpty()) {
				list.add(val);
				continue;
			}
			if(list.get(list.size() - 1) > val) {
				changeVal(val);
			} else if(list.get(list.size() - 1) < val) {
				list.add(val);
			}
		}
		System.out.println(list.size());
	}
}

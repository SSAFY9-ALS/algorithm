package net.acmicpc;

import java.io.*;
import java.util.*;
import java.awt.*;
/**
 * 좋다  / 골드 4 / 70분
 * @author 민정인
 * https://www.acmicpc.net/problem/1253
 */

public class BOJ_1253 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list);	// 오름차순 정렬
		ArrayList<Integer> tList = new ArrayList<>();	// 선택된 수를 제외하고 진행하는 임시 리스트
		int result = 0;
		for(int i = 0; i < list.size(); i++) {
			int f = list.get(i);	// 좋은 수인지 확인하기 위한 값
			tList = (ArrayList<Integer>) list.clone();	// 임시 리스트에 값 저장
			tList.remove(i);		// 해당 값 삭제
			boolean find = false;
			for(int j = 0; j < tList.size() - 1; j++) {	// 해당 값을 지운 리스트 진행
				int val = tList.get(j);					// 초기값 저장 및 바로 다음 값의 합보다 찾는 값이 작으면 탈출
				if(val + tList.get(j+1) > f) {
					break;
				}
				for(int k = j + 1; k < tList.size(); k++) {	// 다음으로 더할 값들 탐색 및 합이 찾는 값보다 크면 탈출
					if(val + tList.get(k) == f) {			// 찾으면 결과 + 1
						find = true;
						result++;
						break;
					} else if(val + tList.get(k) > f) {
						break;
					}
				}
				if(find) {
					break;
				}
			}
		}
		System.out.println(result);
	}
}

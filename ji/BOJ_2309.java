package net.acmicpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 일곱 난쟁이 / 브론즈 1 / 18분
 * @author 민정인
 * https://www.acmicpc.net/problem/2309
 */
public class BOJ_2309 {
	static List<Integer> list = new ArrayList<>();
	static int[] result = new int[7];
	static void equalHundred(int cnt, int idx, List<Integer> tmp) {
		if(cnt == 7) {
			int sum = 0;
			for(int i = 0; i < 7; i++) {
				sum += tmp.get(i);				// 키의 합
			}
			if(sum == 100) {
				for(int i = 0; i < 7; i++) {
					result[i] = tmp.get(i);		// 100이라면 출력 배열에 저
				}
				return;
			}
			return;
		}
		for(int i = idx; i < 9; i++) {
			tmp.add(list.get(i));
			equalHundred(cnt + 1, i + 1, tmp);
			tmp.remove(tmp.indexOf(list.get(i)));
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < 9; i++) {
			list.add(sc.nextInt());
		}
		Collections.sort(list);
		equalHundred(0, 0, new ArrayList<>());
		for(int i = 0; i < 7; i++) {
			System.out.print(result[i] + " ");
		}
	}
}

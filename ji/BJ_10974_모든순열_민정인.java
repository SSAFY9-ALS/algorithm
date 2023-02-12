package com.ssafy.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BJ_10974_모든순열_민정인 {
	public static StringBuilder sb = new StringBuilder();		// 최대 8!만큼의 출력이 반복되기 때문에 한번에 출력하기 위한 StringBuilder
	public static List<Integer> list = new ArrayList<>();		// 순열을 저장하기 위한 ArrayList
	public static int n;										// 입력받은 값 + 재귀 탈출을 위한 cnt의 조건
	public static boolean[] chk;								// 해당 수를 사용했는지 확인하기 위한 boolean배열(chk[i]는 i를 사용했는지 확인하기 위함)
	public static void printDictionary(int cnt) {				// 재귀함수
		if(cnt == n) {											// cnt가 n일 때: 총 n번 돌면서 n까지의 수를 모두 순열에 추가하였으므로 재귀 탈출에 필요
			for(int i = 0; i < list.size(); i++) {				// 완성된 순열 리스트를 StringBuilder에 입력
				sb.append(list.get(i) + " ");
			}
			sb.append('\n');
			return;												// 재귀 탈출
		}
		for(int i = 1; i <= n; i++) {
			if(chk[i] == true) {								// 해당 순열에서 해당 수를 사용했는지 검사
				continue;										// 사용했을 경우 continue
			}
			chk[i] = true;										// 사용하지 않은 경우 해당 차례에 사용할 것이므로 true
			list.add(i);										// 순열 리스트에 추가
			printDictionary(cnt + 1);							// 재귀 진행
			list.remove(list.indexOf(i));						// 해당 수를 사용한 재귀가 종료되었으므로 리스트에서 제거
			chk[i] = false;										// 방문을 초기화
		}
	}															// 재귀함수 종료
	
	public static void main(String[] args) {					// 메인함수 시작
		Scanner sc = new Scanner(System.in);					// 입력을 위한 Scanner
		n = sc.nextInt();										// 순열의 크기 겸 순열에 들어갈 최대값
		chk = new boolean[n + 1];								// i의 사용여부를 chk[i]에서 확인하기 위해 n + 1 크기의 boolean 배열 선언
		printDictionary(0);										// 재귀함수 호출
		System.out.println(sb);									// 결과 출력
	}															// 메인함수 종료
}

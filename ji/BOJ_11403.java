package net.acmicpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * 경로 찾기 / 실버 1 / 29분
 * @author 민정인
 * https://www.acmicpc.net/problem/11403
 */
public class BOJ_11403 {
	static class Pair {													// 좌표 저장을 위한 클래스 Pair
		int st;
		int ar;
		public Pair() {}
		public Pair(int st, int ar) {
			this.st = st;
			this.ar = ar;
		}
	}
	static List<Pair> list = new ArrayList<>();							// 좌표 정보들을 저장하는 list
	static int[][] result;
	static boolean[] chk;
	static void cycle(int st, int mainSt) {								// 연결성을 확인하는 함수
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).st == st && chk[list.get(i).ar] == false) {	// 시작점이 일치하고 넘어가는 쪽 방문하지 않은 경우 재귀
				chk[list.get(i).ar] = true;
				result[mainSt][list.get(i).ar] = 1;
				cycle(list.get(i).ar, mainSt);
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] arr = new int[n][n];
		result = new int[n][n];
		chk = new boolean[n];											// 방문여부 확인을 위한 배열
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == 1) {
					Pair tmp = new Pair(i, j);
					list.add(tmp);
				}
			}
		}
		for(int i = 0; i < n; i++) {
			cycle(i, i);
			Arrays.fill(chk, false);									// 방문 여부 초기화
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
	}
}

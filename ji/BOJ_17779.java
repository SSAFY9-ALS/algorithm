package net.acmipc;

import java.io.*;
import java.util.*;
/**
 * 게리맨더링2 / 골드 3 / 100분
 * @author 민정인
 * https://www.acmicpc.net/problem/17779
 */
public class BOJ_17779 {
	static int n;
	static int[][] arr;
	static int[] val = new int[5];
	static boolean[][] chk;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		chk = new boolean[n][n];
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		getVal();
		System.out.println(result);
	}
	static void getVal() {	// 5번 구역 시작위치 정하기
		for(int i = 0; i < n - 1; i++) {
			for(int j = 1; j < n - 1; j++) {
				initD(i, j);
			}
		}
	}
	static int sr, sc, sd1, sd2;
	static void initD(int r, int c) {	// 5번 구역 범위 정하기
		for(int d1 = 1; c - d1 >= 0; d1++) {
			for(int d2 = 1; c + d2 < n; d2++) {
				if(r + d1 + d2 < n) {
					sr = r;
					sc = c;
					sd1 = d1;
					sd2 = d2;
					fillMap(r, c, d1, d2);
				}
			}
		}
	}
	static void fillMap(int r, int c, int d1, int d2) {	// 5번 구역 채우기(5번 구역을 boolean 배열에서 true로 선언)
		if(chk[r][c]) {	// 중심점을 한칸씩 내리면서 테두리를 true로 바꾸는 방식
			getMod();
//			System.out.println(Arrays.toString(val));
			Arrays.sort(val);	// 오름차순 정렬 후 최대값 - 최소값을 결과값에 갱신
			result = Math.min(result, val[4] - val[0]);
			Arrays.fill(val, 0);
			chk = new boolean[n][n];
			return;
		}
		chk[r][c] = true;
		val[4] += arr[r][c];
		for(int i = 1; i <= d1; i++) {
			if(!chk[r+i][c-i]) {
				val[4] += arr[r+i][c-i];
				chk[r+i][c-i] = true;
			}
			if(!chk[r+d2+i][c+d2-i]) {
				val[4] += arr[r+d2+i][c+d2-i];
				chk[r+d2+i][c+d2-i] = true;
			}
		}
		for(int i = 1; i <= d2; i++) {
			if(!chk[r+i][c+i]) {
				val[4] += arr[r+i][c+i];
				chk[r+i][c+i] = true;
			}
			if(!chk[r+d1+i][c-d1+i]) {
				val[4] += arr[r+d1+i][c-d1+i];
				chk[r+d1+i][c-d1+i] = true;
			}
		}
		fillMap(r + 1, c, d1 - 1, d2 - 1);
	}
	static void getMod() {	// 1, 2, 3, 4번 구역의 합 구하기(각각의 직사각형 범위 내 true인 부분은 5번 구역이므로 생략하고 값 계산)
		for(int i = 0; i < sr + sd1; i++) {
			for(int j = 0; j <= sc; j++) {
				if(!chk[i][j]) {
					val[0] += arr[i][j];
					chk[i][j] = true;
				}
			}
		}
		for(int i = 0; i <= sr + sd2; i++) {
			for(int j = sc + 1; j < n; j++) {
				if(!chk[i][j]) {
					val[1] += arr[i][j];
					chk[i][j] = true;
				}
			}
		}
		for(int i = sr + sd1; i < n; i++) {
			for(int j = 0; j < sc - sd1 + sd2; j++) {
				if(!chk[i][j]) {
					val[2] += arr[i][j];
					chk[i][j] = true;
				}
			}
		}
		for(int i = sr + sd2 + 1; i < n; i++) {
			for(int j = sc - sd1 + sd2; j < n; j++) {
				if(!chk[i][j]) {
					val[3] += arr[i][j];
					chk[i][j] = true;
				}
			}
		}
	}
	static int result = Integer.MAX_VALUE;
}

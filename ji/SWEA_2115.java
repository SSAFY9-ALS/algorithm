package net.acmicpc;

import java.io.BufferedReader;
/**
 * 
 * 벌꿀 채취 / 93분
 * 민정인
 * https://swexpertacademy.com/main/code/problem/problemDetail.do
 * 
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2115 {
	static int m;
	static int c;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t = 1; t <= test; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			int[][] map = new int[n][n];		// 벌꿀통 선언
			int[][] result = new int[n][n-m+1];	// 각 범위에서 나올 수 있는 최대 수익
			int val = 0;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i = 0; i < n; i++) {
				for(int j = 0; j <= n - m; j++) {
					int[] arr = Arrays.copyOfRange(map[i], j, j + m);	// 범위 내 벌꿀통 배열 복사
					result[i][j] = getValue(arr);
				}
			}
//			for(int i = 0; i < n; i++) {
//				for(int j = 0; j <= n - m; j++) {
//					System.out.print(result[i][j] + " ");
//				}
//				System.out.println();
//			}
			for(int x = 0; x < n; x++) {
				boolean[][] chk = new boolean[n][n-m+1];
				int value = 0;
				for(int y = 0; y <= n - m; y++) {
					value += result[x][y];
					for(int i = 0; i <= m - 1; i++) {
						if(y + i > n - m) {
							break;
						}
						chk[x][y+i] = true;
					}
					for(int i = 0; i <= m - 1; i++) {
						if(y - i < 0) {
							break;
						}
						chk[x][y-i] = true;
					}
					for(int i = 0; i < n; i++) {
						for(int j = 0; j <= n - m; j++) {
							if(chk[i][j] == false) {
								value += result[i][j];
								val = Math.max(val, value);
								value -= result[i][j];
							}
						}
					}
					value -= result[x][y];
				}
			}
			System.out.println("#" + t + " " + val);
		}
	}
	static int[] tmp;
	static int getValue(int[] arr) {	// 배열의 꿀통들을 조합하여 수익 확인 및 최대 수익 출력
		int maxVal = 0;
		int len = arr.length;
		tmp = new int[len];
		for(int i = 1; i <= len; i++) {
			Arrays.fill(tmp, 0);
			Arrays.fill(tmp, len - i, len, 1);
			do {
				boolean chk = true;
				int val = 0;
				int price = 0;
				for(int j = 0; j < len; j++) {
					if(tmp[j] == 1) {
						val += arr[j];
						if(val > c) {
							chk = false;
							break;
						}
						price += Math.pow(arr[j], 2);
					}
				}
				if(chk) {					
					maxVal = Math.max(maxVal, price);					
				}
			} while(np());
		}
		return maxVal;
	}
	static boolean np() {
		int i = tmp.length - 1;
		while(i > 0 && tmp[i-1] >= tmp[i]) --i;
		if(i == 0) return false;
		int j = tmp.length - 1;
		while(tmp[i - 1] >= tmp[j]) --j;
		swap(tmp, i - 1, j);
		int k = tmp.length - 1;
		while(i < k) swap(tmp, i++, k--);
		return true;
	}
	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}

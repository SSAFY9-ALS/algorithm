/**
 * 색종이 만들기 / 실버 2 / 40분
 * https://www.acmicpc.net/problem/2630
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_2630 {

	static int blue = 0;
	static int white = 0;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = parseInt(br.readLine());
		arr = new int[n][n]; // 한변의 길이
		
		for(int i=0;i<n;i++) { // 배열 입력 받음
			String[] tmp = br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				arr[i][j] = parseInt(tmp[j]);
			}
		}
		
		paper(n,0,0);
		System.out.println(white);
		System.out.println(blue);
	}
	
	private static void paper(int n, int x, int y) {
		
		if(check(x,y,n)) { // 해당 부분 같은 색으로 이루어졌는지 확인
			if(arr[x][y]==1) {  // 같을 경우 각 색++
				blue++;
			}
			else {
				white++;
			}
		}
		else {
			paper(n/2,x,y); // 2사분면
			paper(n/2,x,y+n/2); // 1사분면
			paper(n/2,x+n/2,y); // 3사분면
			paper(n/2,x+n/2,y+n/2); // 4사분면
		}
	}
	
	private static boolean check(int x,int y, int size) { // 해당 부분이 같은 색인지 확인
		// 시작좌표, 크기
		int color = arr[x][y];
		
		for(int i=x;i<x+size;i++) {
			for(int j=y;j<y+size;j++) {
				if(color != arr[i][j]) { // 해당 부분에서 색이다른 곳이 발견되면 false
					return false;
				}
			}
		}
		return true; // 전부다 같은 색이므로 색종이 완성
	}
}

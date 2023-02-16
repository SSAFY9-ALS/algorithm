/**
 * 쿼드트리 / 실버 1 / 25분
 * https://www.acmicpc.net/problem/1992
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_1992 {
	
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = parseInt(br.readLine()); // 영상의 크기
		arr = new int[n][n];
		
		for(int i=0;i<n;i++) { // 입력 배열 받음
			String[] tmp = br.readLine().split("");
			for(int j=0;j<n;j++) {
				arr[i][j] = parseInt(tmp[j]);
			}
		}
		quad(n,0,0);
		System.out.println(sb);
		
	}
	private static void quad(int n, int x, int y) {
		if(check(x,y,n)) { // 해당구역 전부 같은색
			sb.append(arr[x][y]);
		}
		else {
			sb.append("("); // 4구역으로 분할시 묶어주기 위해 사용되므로  4방향으로 분할시작시 ( 사용
			quad(n/2,x,y); // 왼 위 
			quad(n/2,x,y+n/2); // 오 위
			quad(n/2,x+n/2,y); // 왼 아래
			quad(n/2,x+n/2,y+n/2); // 오 아래
			sb.append(")"); // 4분할 다 마치고 돌아오면 ) 사용
		}
		
	}
	
	private static boolean check(int x, int y, int size) {
		int bw = arr[x][y];
		
		for(int i=x;i<x+size;i++) {
			for(int j=y;j<y+size;j++){
				if(bw != arr[i][j]) {
					return false; // 해당 구역 전부 같은 색 아님
				}
			}
		}
		return true; // 해당구역 전부 같은색
	}
}

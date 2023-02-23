package yeri.algorithm0214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 쿼드트리 / 실버1 / 20분
 * https://www.acmicpc.net/problem/1992
 */
public class BJ_1992_쿼드트리 {

	static char[][] arr ;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		for(int y = 0; y < N; y++) {
			String str = br.readLine();
			for(int x = 0; x < N; x++) {
				arr[y][x] = str.charAt(x); 
			}
		}
		quadTree(0,0,N);
		System.out.println(sb);

	}
	/**
	 * 
	 * @param r 시작위치 좌표
	 * @param c
	 * @param size 크기
	 */
	private static void quadTree(int r, int c, int size) {
//		sb.append("(");
		char curr = arr[r][c];
		for(int y = r; y<r+size;y++) {
			for(int x = c; x<c+size; x++) {
				if(curr != arr[y][x]) {
					sb.append("(");
					quadTree(r, c, size/2);
					quadTree(r, c+size/2, size/2);
					quadTree(r+size/2, c, size/2);
					quadTree(r+size/2, c+size/2, size/2);
					sb.append(")");
					return;
				}
			}
		}
		sb.append(curr);
//		sb.append(curr).append(")");
	}

}

package Divide_and_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 종이의 개수 / 실버 2 / 30분
 * https://www.acmicpc.net/problem/1780
 */

public class BJ_1780_종이의개수 {
	
	static int[][] map;
	static int[] papers;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		papers = new int[3];
		
		// 인덱스 0 : 숫자 1
		// 인덱스 1 : 숫자 0
		// 인덱스 2 : 숫자 -1
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divide(0, 0, N);

		for(int i = 2; i >= 0; i--) {
			System.out.println(papers[i]);
		}
	}
	
	public static void divide(int startX, int startY, int len) {
		if(len == 1) {
			// 길이가 1로 쪼개졌을 때 -1, 0, 1중 해당하는 값에 개수를 더해줌
			papers[1 - map[startX][startY]]++;
			return;
		}
		
		int first = map[startX][startY];
		int equal = 0;
		for(int i = startX; i < startX + len ; i++) {
			for(int j = startY; j < startY + len; j++) {
				if(first != map[i][j]) {
					equal = -1;
					// 첫번째 
					divide(startX, startY, len/3);
					// 두번째
					divide(startX, startY + len/3, len/3);
					// 세번째
					divide(startX, startY + (len/3)*2, len/3);
					// 네번째
					divide(startX + len/3, startY, len/3);
					// 다섯번째
					divide(startX + len/3, startY + len/3, len/3);
					// 여섯번째
					divide(startX + len/3, startY + (len/3)*2, len/3);
					// 일곱번째
					divide(startX + (len/3)*2, startY, len/3);
					// 여덟번째
					divide(startX + (len/3)*2, startY + len/3, len/3);
					// 아홉번째
					divide(startX + (len/3)*2, startY + (len/3)*2, len/3);
					break;
				}
			}
			if(equal == -1) {
				break;
			}
		}
		
		// 모두 동일한 숫자로 이루어져 있을 때
		if(equal == 0) {
			papers[1-first]++;
		}
	}
}

package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 색종이 만들기 / 실버2 / 1시간 30분
 * https://www.acmicpc.net/problem/2630
 */

// 어차피 입력되는 값은 0 or 1 -> 더 적은 바이트 크기를 차지하는 char로 만드는 게 효율적!
public class BOJ_2630 {
	static boolean checkColor(int[][] papers, int x, int y, int size) {
		int check = papers[x][y]; // 첫번째 값을 기준으로 검사
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(check != papers[x+i][y+j]) { // 정사각형 안에 기준값과 다른 값이 존재하면
					return false;
				}
			}
		}
		return true;
	}
	
	static int[] findSquare(int[][] papers, int x, int y, int size, int[] ans) {
		if(checkColor(papers, x, y, size)) { // 정사각형 값이 모두 같을 때
			if(papers[x][y] == 0) // 값이 흰색이라면
				return new int[] {1, 0}; // 흰색 1 증가
			return new int[] {0, 1}; // 아니면 파란색 1 증가
		}
		
		size /= 2; // 사이즈 절반으로 줄이기
//		int[] origin = ans.clone(); // 궁금한 점: clone()을 쓰면 깊은 복사가 되어 ans를 바꿔도 origin은 변하지 않아야 하는 거 아닌가?
		int[] result = new int[2];
		int num1 = ans[0];
		int num2 = ans[1];
		for(int i = 0; i < 2; i++) { // 현재 정사각형을 4개로 쪼개어 재귀 함수 반복
			for(int j = 0; j < 2; j++) {
				result = findSquare(papers, x + (size*i), y + (size*j), size, new int[] {num1, num2});
//				result = findSquare(papers, x + (size*i), y + (size*j), size, origin);
				ans[0] += result[0];
				ans[1] += result[1];
			}
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] papers = new int[n][n];
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				papers[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] result = findSquare(papers, 0, 0, n, new int[] {0, 0});
		System.out.printf("%d\n%d", result[0], result[1]);
	}
}

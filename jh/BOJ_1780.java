package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 종이의 개수 / 실버2 / 20분
 * https://www.acmicpc.net/problem/1780
 */

public class BOJ_1780 {
	static int[] count = new int[3]; // count 결과를 담을 배열 생성
	
	// 현재 영역에 존재하는 값들이 전부 같은지 판별하는 메서드
	static boolean isSame(char[][] paper, int startX, int startY, int size) {
		char check = paper[startX][startY]; // check 값 생성
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(paper[startX+i][startY+j] != check) // 만약 check와 다른 값이 존재한다면
					return false; // false 리턴
			}
		}
		return true; // true 리턴
	}
	
	static void countPaper(char[][] paper, int startX, int startY, int size) {
		if(isSame(paper, startX, startY, size)) { //현재 영역의 값이 모두 같다면
			count[Character.getNumericValue(paper[startX][startY])]++; // 해당하는 영역의 count 1 증가
		}
		else { // 값이 같지 않을 경우 -> 9가지의 영역으로 나누어 다시 함수 호출해야 함
			int findX, findY; // 찾을 시작 위치 변수
			size /= 3; // 9가지로 나누었을 때 size
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					findX = startX + (size * i); // 시작 위치
					findY = startY + (size * j); // 종료 위치
					countPaper(paper, findX, findY, size); // 다시 함수 호출
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(in.readLine()); // n 입력
		char[][] paper = new char[n][n]; // 종이 값을 담을 배열
		String sen;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				sen = st.nextToken();
				// -1, 0, 1 카운트 갯수를 하나의 배열로 만들어 관리 -> 인덱스를 값으로 바로 사용하기 위해  -1 -> 0, 0 -> 1, 1 -> 2로 변경
				if(sen.equals("-1"))
					paper[i][j] = '0';
				else
					paper[i][j] = (char) (sen.charAt(0) + 1); 
			}
		}
		countPaper(paper, 0, 0, n); // 함수 호출
		for(int num: count)
			System.out.println(num); // 결과 출력
	}
}

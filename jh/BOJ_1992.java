package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 쿼드트리 / 실버1 / 1시간 20분
 * https://www.acmicpc.net/problem/1992
 */

public class BOJ_1992 {
	static StringBuilder result = new StringBuilder(); // 영상 압축 결과를 담는 StringBuilder 생성

	// 탐색하는 부분을 압축할 수 있는지 판별하는 메서드
	static boolean findValid(char[][] video, int findX, int findY, int size) {
		char check = video[findX][findY]; // 탐색하는 부분을 체킹할 값
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (video[findX + i][findY + j] != check) { // 만약 check 변수의 값과 같지 않다면 -> 하나로 압축할 수 없는 경우
					return false; // false 반환
				}
			}
		}
		return true; // 여기까지 오면 하나로 압축할 수 있는 경우 -> true 반환
	}
	
	static void findQuadTree(char[][] video, int startX, int startY, int size) {
		if(size == 1) { // size가 1이면 -> 그 값이 곧 압축된 값이 됨
			result.append(video[startX][startY]); // result에 현재 값 append
			return;
		}
		
		if(findValid(video, startX, startY, size)) { // 탐색 부분을 하나로 압축할 수 있으면
			result.append(video[startX][startY]); // result에 시작 값 append
		}
		else { // 하나로 압축할 수 없다면
			result.append("(");
			size = size / 2; // size 절반으로 줄임
			int findX, findY;
			for (int w = 0; w < 2; w++) {
				for (int h = 0; h < 2; h++) {
					findX = startX + (size * w); // 총 네 가지 부분에 대한 시작 인덱스를 구함
					findY = startY + (size * h); // 총 네 가지 부분에 대한 시작 인덱스를 구함
					findQuadTree(video, findX, findY, size); // 각 부분에 대해 다시 함수 호출
				}
			}
			result.append(")");
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine()); // n 입력
		char[][] video = new char[n][n]; // 영상 담을 배열
		String sen;
		for (int i = 0; i < n; i++) {
			sen = in.readLine();
			for (int j = 0; j < n; j++)
				video[i][j] = sen.charAt(j); // 영상 내용 입력
		}
		findQuadTree(video, 0, 0, n); // 함수 호출
		System.out.println(result); // 결과 출력
	}
}
package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 랜선 자르기 / 실버2 / 1시간 20분
 * https://www.acmicpc.net/problem/1654
 */

public class BOJ_1654 {
	static int n;
	static long max = 0; // 최대 랜선 길이 담을 변수
	
	static void findMax(int[] lan, long start, long end) {
		if(start > end) // 종료 조건에 해당하면
			return; // 종료
		else {
			long middle = (start + end) / 2; // middle 값 생성
			long sum = 0;
			for(int i = lan.length - 1; i >= 0; i--) { // 뒤에서부터 탐색
				if(lan[i] < middle) // 오름차순 정렬된 배열의 뒤에서부터 검색했기 때문에 이 앞은 다 middle보다 작음
					break; // 반복문 종료
				sum += lan[i] / middle; // lan[i]에서 나올 수 있는 middle 길이의 랜선 sum에 더해줌
			}
			if(n <= sum) { // n개 이상 만들었을 때 -> 랜선 하나당 길이는 더 길어도 됨
				if(max < middle) // 현재 최대 길이보다 길면
					max = middle; // 값 갱신
				findMax(lan, middle + 1, end); // start값을 middle 뒤에 옮겨서 재귀
			}
			else // n개만큼 못 만들었을 때 -> 랜선 하나당 길이는 더 짧아야 함
				findMax(lan, start, middle - 1); // end값을 middle 앞에 옮겨서 재귀
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int k = Integer.parseInt(st.nextToken()); // k 입력
		n = Integer.parseInt(st.nextToken()); // n 입력
		int[] lan = new int[k]; // 랜선 입력값 받을 배열 생성
		for(int i = 0; i < k; i++)
			lan[i] = Integer.parseInt(in.readLine()); // 랜선 길이 입력
		Arrays.sort(lan); // 이분 탐색을 위해 정렬
		findMax(lan, 1l, (long)lan[k-1]); // 재귀 함수 호출
		System.out.println(max);
	}
}
package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 행복유치원 / 골드 5 / 1시간
 * https://www.acmicpc.net/problem/13164
 */
public class BJ_13164_행복유치원 {
	static int N, K;
	static int[] babiesDiff;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 유치원에 있는 원생의 수 : N, 나누려고 하는 조의 개수 : K
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 유치원생들 배열
		babiesDiff = new int[N-1];
		
		// 유치원생들 키
		st = new StringTokenizer(br.readLine());
		// 이전 키 값
		int beforeHeight = 0;
		// 차이 총 합
		int totalDiff = 0;
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			
			if(i > 0) {
				babiesDiff[i-1] = height-beforeHeight;
				totalDiff += babiesDiff[i-1];
			}
			beforeHeight = height;
		}
		
		// 키 차이 정렬
		Arrays.sort(babiesDiff);
		
		for (int i = N-2; i > N-1-K; i--) {
			totalDiff -= babiesDiff[i];
		}
		
		System.out.println(totalDiff);
	}
}


/*
 * 이진하의 힌트
 * - n명의 사람이 있을 때? 만들어지는 인접한 사람의 키 차이 배열은? n-1개
 * - ex) 1 4 6 8 13 25 48 -> 6개의 diff 만들어짐
 * - 우리가 만들어야 하는 그룹은 k개 -> () () ()
 * - 그렇다면 무시해야 하는 diff는 몇개가 되고 무엇을 무시해야 할까?
 */

package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 고층 건물 / 골드4 / 40분
 * https://www.acmicpc.net/problem/1027
 */

public class BOJ_1027 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int[] buildings = new int[n];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++)
			buildings[i] = Integer.parseInt(st.nextToken());
		
		// 두 빌딩이 볼 수 있으려면?
		// 1. 기준 빌딩보다 왼쪽에 있는 빌딩들에 대해서 -> 사이에 존재하는 빌딩과의 기울기보다 작으면 됨
		// 2. 기준 빌딩보다 오른쪽에 있는 빌딩들에 대해서 -> 사이에 존재하는 빌딩과의 기울기보다 크면 됨
		
		int cnt, maxCnt = 0;
		double min, max, s;
		for(int i = 0; i < n; i++) {
			cnt = 0;
			min = Integer.MAX_VALUE;
			for(int j = i-1; j >= 0; j--) {
				s = (buildings[i] - buildings[j]) / (double) (i - j);
				if(s < min) {
					cnt++;
					 min = s;
				}
			}
			max = Integer.MIN_VALUE;
			for(int j = i+1; j < n; j++) {
				s = (buildings[i] - buildings[j]) / (double) (i - j);
				if(s > max) {
					cnt++;
					max = s;
				}
			}
			if(maxCnt < cnt) {
				maxCnt = cnt;
			}
		}
		System.out.println(maxCnt);
	}
}

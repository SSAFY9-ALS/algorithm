package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 고층 건물 / 골드 4 /38분
 * @author 민정인
 * https://www.acmicpc.net/problem/1027
 */

public class BOJ_1027 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[num];
		for(int i = 0; i < num; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		float[][] height = new float[num][num];
		for(int i = 0; i < num; i++) {	// i번째 건물을 기준으로 모든 건물까지의 기울기
			for(int j = 0; j < num; j++) {
				if(i == j) continue;
				height[i][j] = (float) (arr[i] - arr[j]) / (i - j);
			}
		}
		int result = 0;
		if(num > 1) {	// 2개 이상 건물이 존재할 경우 기울기에 따라 비교
			for(int i = 0; i < num; i++) {
				int cnt = 0;
				if(i == 0 || i == num - 1) {
					cnt = 1;
				} else {
					cnt = 2;
				}
				// 현재 건물을 기준으로 왼쪽은 가장 낮은 건물이 +무한대부터 가장 높은 건물이 + 무한대까지 가능(정확히는 1000000000)
				// 오른쪽은 반대로 -부터 + 방향으로 높이가 높아짐
				// 이때 뒤쪽 건물을 보는 선의 기울기 내에 다른 건물이 끼어있을 경우(즉 이전 건물의 기울기와 동일하거나 왼쪽은 더 작거나 오른쪽은 더 큰 경우)
				// 뒤의 건물은 확인 불가
				float lVal = i == 0 ? Float.MAX_VALUE : height[i][i-1];
				float rVal = i == num - 1 ? Float.MIN_VALUE : height[i][i+1];
				for(int j = i - 2; j >= 0; j--) {
					if(lVal > height[i][j]) {
						lVal = height[i][j];
						cnt++;
					}
				}
				for(int j = i + 2; j < num; j++) {
					if(rVal < height[i][j]) {
						rVal = height[i][j];
						cnt++;
					}
				}
//			System.out.println(cnt);
				result = Math.max(result, cnt);
			}
		}
		System.out.println(result);
	}
}

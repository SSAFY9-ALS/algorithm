/**
 * RGB거리 / 실버 1 / 40분
 */
package algorithm_with_java.dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BOJ_1149 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine()); // 집의 개수
		int[][] arr = new int[num+1][3]; // 비용을 저장할 배열
		
		int[] dpR = new int[num+1]; // 마지막에 R 집을 선택하는 경우
		int[] dpG = new int[num+1]; // 마지막에 G 집을 선택하는 경우
		int[] dpB = new int[num+1]; // 마지막에 B 집을 선택하는 경우
		
		// 배열 
		for(int i=1;i<=num;i++) {
			String line = br.readLine();
			String[] tmp =line.split(" ");
			for(int j=0;j<3;j++) {
				arr[i][j]= Integer.parseInt(tmp[j]);
			}
		}
		
		dpR[1]=arr[1][0];
		dpG[1]=arr[1][1];
		dpB[1]=arr[1][2]; // 집을 1개만 선택하는 경우 초기화
		
		for(int i=2;i<=num;i++) {
			dpR[i] = Math.min(dpG[i-1]+arr[i][0], dpB[i-1]+arr[i][0]); // i번째 R집을 선택하는 경우 최소값
			dpG[i] = Math.min(dpR[i-1]+arr[i][1], dpB[i-1]+arr[i][1]); // i번째 G집을 선택하는 경우 최소값
			dpB[i] = Math.min(dpR[i-1]+arr[i][2], dpG[i-1]+arr[i][2]); // i번째 B집을 선택하는 경우 최소값
		}
		
		System.out.println(Math.min(dpR[num], Math.min(dpG[num], dpB[num])));
	}
}

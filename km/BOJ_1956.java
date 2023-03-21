/**
 * 운동 / 골드 4 / 40분
 * https://www.acmicpc.net/problem/1956
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_1956 {
	
	static int V; 
	static int E;
	static int[][] arr;
	static final int INF = 999999999;
	
	static void fw() { // 플로이드 워셜 알고리즘
		for(int k=1;k<V+1;k++) {
			for(int a=1;a<V+1;a++) {
				for(int b=1;b<V+1;b++) {
					arr[a][b] = Math.min(arr[a][b], arr[a][k]+arr[k][b]);
				}
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		V = parseInt(tmp[0]);
		E = parseInt(tmp[1]);
		int answer = INF;
		
		arr = new int[V+1][V+1];
		
		for(int i=1;i<=V;i++) { // 초기 테이블 초기화
			for(int j=1;j<=V;j++) {
				if(i!=j) arr[i][j] = INF;
			}
		}

		
		for(int i=0;i<E;i++) { // 나머지 테이블 초기화
			tmp = br.readLine().split(" ");
			arr[parseInt(tmp[0])][parseInt(tmp[1])] = parseInt(tmp[2]);
		}
		
		fw();
		
		for(int i=1;i<V+1;i++) {
			for(int j=1;j<V+1;j++) {
				if(i!=j) { // 자기 사진으로 바로 가는 것 제외
					if(arr[i][j]!=INF && arr[j][i]!=INF) { // 사이클 존재
						answer = Math.min(answer, arr[i][j]+arr[j][i]);
					}
				}
			}
		}

		
		if(answer==INF) {
			System.out.println(-1);
		}
		else {
			System.out.println(answer);
		}
	}

}

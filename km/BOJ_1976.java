/**
 * 여행 가자 / 골드 4 / 30분
 * https://www.acmicpc.net/problem/1976
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_1976 {
	
	static int N,M;
	static int[][] arr;
	static int[] travel;
	
	static void make() {
		for(int k=1;k<N+1;k++) {
			for(int i=1;i<N+1;i++) {
				if(i==k) continue;
				for(int j=1;j<N+1;j++) {
					if(i==j || j==k) continue;
					if(arr[i][k]==1 && arr[k][j]==1) arr[i][j]=1;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = parseInt(br.readLine());
		M = parseInt(br.readLine());
		
		arr = new int[N+1][N+1];
		travel = new int[M];
		
		for(int i=1;i<N+1;i++) {
			String[] tmp = br.readLine().split(" ");
			for(int j=1;j<N+1;j++) {
				arr[i][j] = parseInt(tmp[j-1]);
				if(i==j) arr[i][j]=1;
			}
		}
		
		String[] tmp = br.readLine().split(" ");
		for(int i=0;i<M;i++) {
			travel[i] = parseInt(tmp[i]);
		}
		
		make();
		
		for(int i=1;i<M;i++) {
			if(arr[travel[i-1]][travel[i]]==0) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
	}

}

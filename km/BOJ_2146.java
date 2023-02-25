/**
 * 다리 만들기 / 골드 3 / 100분
 * https://www.acmicpc.net/problem/2146
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_2146 {
	static int n;
	static int[][] arr;
	static int[][] check;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = parseInt(br.readLine());
		arr = new int[n][n];
		
		for(int i=0;i<n;i++) {
			String[] tmp = br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				arr[i][j] = parseInt(tmp[j]);
			}
		}
		
		check = new int[n][n];
		int num = 1;
		// 육지 구분 하기
		for(int i=0;i<n;i++) { // 육지 별로 번호 부여
			for(int j=0;j<n;j++) {
				if(arr[i][j]==1 && check[i][j]==0) {
					bfs1(num,i,j);
					num++;
				}
			}
		}
		
		check = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(arr[i][j]!=0) { // 육지일때 해당 육지가 아닌 다른 육지로 가는 빠른 경우 구함
					bfs2(arr[i][j],i,j);
//					dfs(arr[i][j],i,j,0);
					check = new int[n][n];
				}
			}
		}
		
		System.out.println(answer);
	}
	
	private static void bfs1(int num, int x, int y) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1}; // 탐색용 방향
		
		queue.add(new int[] {x,y});
		arr[x][y]=num;
		check[x][y]=1;
		
		while(!queue.isEmpty()) {
			int tmpx = queue.peek()[0];
			int tmpy = queue.peek()[1];
			queue.poll();
			for(int i=0;i<4;i++) {
				int nx = tmpx + dx[i];
				int ny = tmpy + dy[i];
				if(nx>=0 && ny>=0 && nx<n && ny<n) {
					if(arr[nx][ny]==1 && check[nx][ny]==0) {
						// 육지 구분
						arr[nx][ny]=num;
						check[nx][ny]=1;
						queue.add(new int[] {nx,ny});
					}
				}
			}
		}
	}
//	private static void dfs(int landnum, int x, int y,int count) {
//		if(arr[x][y]!=0 && arr[x][y]!=landnum) {
//			answer = Math.min(answer, count-1);
//			return;
//		}
//		
//		int[] dx = {-1,0,1,0};
//		int[] dy = {0,1,0,-1}; // 탐색용 방향
//		
//		for(int i=0;i<4;i++) {
//			int nx = x + dx[i];
//			int ny = y + dy[i];
//			if(nx>=0 && ny>=0 && nx<n && ny<n && check[nx][ny]==0 && arr[nx][ny]!=landnum) {
//				check[nx][ny]=1;
//				dfs(landnum,nx,ny,count+1);
//			}
//		}
//	}
	private static void bfs2(int landnum, int x, int y) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1}; // 탐색용 방향
		
		queue.add(new int[] {x,y,0}); // 좌표 + 다리 개수
		int startland = arr[x][y];
		check[x][y]=1;
		
		int count=0;
		
		while(!queue.isEmpty()) {
			int tmpx = queue.peek()[0];
			int tmpy = queue.peek()[1];
			int bridge = queue.peek()[2];
			queue.poll();
			for(int i=0;i<4;i++) {
				int nx = tmpx + dx[i];
				int ny = tmpy + dy[i];
				if(nx>=0 && ny>=0 && nx<n && ny<n && check[nx][ny]==0) {
					if(arr[nx][ny]==0) { // 바다인 경우 다리 놓아주기
						check[nx][ny]=1;
						queue.add(new int[] {nx,ny,bridge+1}); // 다리 개수 하나 증가
						
					}
					else if(arr[nx][ny]!=startland && arr[nx][ny]!=0){
						// 다른 육지에 도착한 경우 다리 개수 비교 하여 최소값 업데이트
						answer = Math.min(answer, bridge);
					}
				}
			}
		}
	}
}

/**
 * 유기농 배추 / 실버 2 / 30분
 */
package algorithm_with_java.dfs_bfs;

import java.io.*;

public class BOJ_1012 {
	public static int bx;  
	public static int by;
	public static int[][] arr;
	public static int[][] visited;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=0;t<tc;t++) {
			String[] tmp = br.readLine().split(" ");
			
			by = Integer.parseInt(tmp[0]);  // 배추 밭의 가로 길이
			bx = Integer.parseInt(tmp[1]); // 배추 밭의 세로 길이
			int bnum = Integer.parseInt(tmp[2]); // 배추 개수
			
			arr = new int[bx][by]; // 배추밭 생성
			visited = new int[bx][by]; // 방문 확인용 배열 1이면 방문
			
			for(int i=0;i<bnum;i++) { // 배추밭 채우기
				String[] p = br.readLine().split(" ");
				arr[Integer.parseInt(p[1])][Integer.parseInt(p[0])] = 1; // 배추 심은 곳 1로 체크
			}
			int answer = 0;
			for(int i=0;i<bx;i++) {
				for(int j=0;j<by;j++) {
					if(arr[i][j]==1 && visited[i][j]!=1) {
						dfs(i, j);
						answer += 1;
					}
				}
			}
			System.out.println(answer);
		}
		
	}
	public static void dfs(int x, int y) {
		// 확인할(x,y)  배주밭     방문 확인용 배열
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1}; // 사방탐색용  배열
		
		visited[x][y]=1;
		for(int i=0;i<4;i++) { // 사방탐색
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0 && nx<bx && ny>=0 && ny<by) { // 배열의 크기 벗어나지 않느지 확인
				if(arr[nx][ny]==1 && visited[nx][ny]==0) { // 배추가 심어져 있고 방문하지 않은곳이면 재귀 호출
					dfs(nx,ny);
				}
			}
		}
	}
}

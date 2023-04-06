/**
 * 연구소 / 골드 4 / 70분
 * https://www.acmicpc.net/problem/14502
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_14502 {
	
	static int n;
	static int m;
	static int answer = Integer.MIN_VALUE;
	static int arr[][];

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = parseInt(tmp[0]);
		m = parseInt(tmp[1]);
		
		arr = new int[n][m];
		for(int i=0;i<n;i++) {
			tmp = br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j]=parseInt(tmp[j]);
			}
		}
		
		makewall(0);
		System.out.println(answer);
	}
	
	private static void makewall(int num) {
		if(num==3) { // 벽 3개 다 만들었으면 바이러스 퍼트림
			makevirus();
			return;
		}
		
		for(int i=0;i<n;i++) { // 벽 만들어줌
			for(int j=0;j<m;j++){
				if(arr[i][j]==0) {
					arr[i][j]=1;
					makewall(num+1);
					arr[i][j]=0;
				}
			}
		}
	}
	
	private static void makevirus() {
		// 연구소 복제 후, 바이러스 퍼트림, 안전구역 게산
		Queue<int[]> queue = new LinkedList<>();
		
		// 연구소 복제 -> 벽의 위치 바뀔때 마다 계산해야함 원본 배열에 바이러스 퍼트리면 안됨
		int[][] map = new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j] = arr[i][j];
				if(arr[i][j]==2) {
					queue.add(new int[] {i,j});
				}
			}
		}
		
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1}; // 바이러스를 뿌리기 위한
		
		// 바이러스 퍼트림
		while(!queue.isEmpty()) {
			int[] virus = queue.poll();
			
			
			for(int i=0;i<4;i++) {
				int nx = virus[0] + dx[i];
				int ny = virus[1] + dy[i];
				if(nx>=0 && nx<n && ny>=0 && ny<m) {
					if(map[nx][ny]==0) {
						map[nx][ny]=2;
						queue.add(new int[] {nx,ny});
					}
				}
			}
		}
		
		safearea(map);
	}
	
	private static void safearea(int[][] arr) { // 안전 구역 계산
		int safe=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(arr[i][j]==0) {
					safe++;
				}
			}
		}
		answer = Math.max(answer, safe);
	}
	
}

/**
 * 마법사 상어와 토네이도 / 골드 3 / 130분
 * https://www.acmicpc.net/problem/20057
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_20057 {
	
	static int N;
	static int[][] arr;
	static int[] dx = {0,1,0,-1}; 
	static int[] dy = {-1,0,1,0}; // 방향 인덱스
	
	// 모래가 퍼지는 방향 & 그 비율
	static int[][] dsx = {{-1,1,-2,-1,1,2,-1,1,0},{-1,-1,0,0,0,0,1,1,2},{1,-1,2,1,-1,-2,1,-1,0},{1,1,0,0,0,0,-1,-1,-2}};
	static int[][] dsy = {{1,1,0,0,0,0,-1,-1,-2},{-1,1,-2,-1,1,2,-1,1,0},{-1,-1,0,0,0,0,1,1,2},{1,-1,2,1,-1,-2,1,-1,0}};
	static int[] ratio = {1,1,2,7,7,2,10,10,5};
	
	static int dir;
	static int answer;
	
	static void move() { // 토네이도 옮기기
		int x = N/2;
		int y = N/2; // 시작 좌표
		
		int cnt = 1; // 한 방향에서 옮길 칸수
		
		
		for(int k=0;k<N-1;k++) {
			for(int i=0;i<2;i++) {
				for(int j=0;j<cnt;j++) {
					x += dx[dir%4];
					y += dy[dir%4]; // 이동할 곳
					tornado(x,y); // 토네이도 발생
				}
				dir++;
			}
			cnt++;
		}
		
		for(int i=0;i<N-1;i++) { // 맨 마지막 1행에서 움직이는 경우
			x += dx[dir%4];
			y += dy[dir%4];
			tornado(x,y);
		}
		
	}
	
	static void tornado(int nx, int ny) { // x위치 -> y위치
		int sand = arr[nx][ny]; // 이동할 모래 양
		arr[nx][ny]=0; // 이동후 0으로
		
		int curdir = dir%4; // 이동 방향
		
		int sx = 0;
		int sy = 0; // 모래가 흩어질 좌표
		int aSand = sand; // a칸에 갈 모래 양
		
		for(int i=0;i<9;i++) {
			sx = nx + dsx[curdir][i];
			sy = ny + dsy[curdir][i];
			
			int amount = (sand * ratio[i])/100; // 해당칸으로 갈 모래 양
			check(sx,sy,amount);
			aSand -= amount;
		}
		
		// a칸으로 검사
		int ax = nx + dx[curdir];
		int ay = ny + dy[curdir];
		check(ax,ay,aSand);
		
	}
	
	static void check(int x, int y, int sand) {
		if(x<0 || x>=N || y<0 || y>=N ) {
			// 배열 벗어난 경우
			answer += sand;
		}
		else {
			// 벗어나지 않은 경우 모래 합치기
			arr[x][y] += sand;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = parseInt(br.readLine());
		arr = new int[N][N];
		
		for(int i=0;i<N;i++) {
			String[] tmp = br.readLine().split(" ");
			for(int j=0;j<N;j++) {
				arr[i][j] = parseInt(tmp[j]);
			}
		}
		
		move();
		
		System.out.println(answer);
	}

}

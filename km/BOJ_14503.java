/**
 * 로봇 청소기 / 골드 5 / 50분
 * https://www.acmicpc.net/problem/14503
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_14503 {

	static int[][] arr;
	static int count = 0;
	static int n;
	static int m;
	static int direction;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = parseInt(tmp[0]);
		m = parseInt(tmp[1]);
		arr = new int[n][m];
		
		tmp = br.readLine().split(" ");
		int x = parseInt(tmp[0]);
		int y = parseInt(tmp[1]); // 시작 좌표
		direction = parseInt(tmp[2]); // 방향
		if(direction==3) { // 반시계 방향으로 돌리는 방향을 맞추기 위해 동서 일경우 방향 바꿈
			direction=1;
		}else if(direction==1) {
			direction=3;
		}
		
		for(int i=0;i<n;i++) { // 방 정보 완성 // 1은 벽  0은 청소전 방  2는 청소후 방
			tmp = br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j] = parseInt(tmp[j]);
 			}
		}
		
		clear(x,y);
		System.out.println(count);
	}
	
	private static void clear(int x, int y) {
		int[] dx = {-1,0,1,0};
		int[] dy = {0,-1,0,1}; // 북 서 남 동 순, 반시계 방향
		if(arr[x][y]==0) { // 현재칸 청소
			arr[x][y]=2;
			count++;
		}
		
		boolean check = false; // 4칸 청소 되었느지 확인용, true 가 청소할 칸 있음
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0 && ny>=0 && nx<n && ny<m) {
				if(arr[nx][ny]==0) {
					check = true;
					break;
				}
			}
		}
		
		if(!check) { // 빈칸 없는경우
			if(arr[x-dx[direction%4]][y-dy[direction%4]]!=1) { // 후진 가능 확인
				x -= dx[direction%4];
				y -= dy[direction%4]; // 후진
				clear(x,y);
			}else {
				return;
			}
		}
		else {
			direction ++; // 방향 바꿈
			if(arr[x+dx[direction%4]][y+dy[direction%4]]==0) { // 전진후 청소 가능 확인
				x += dx[direction%4];
				y += dy[direction%4]; // 전진
			}
			clear(x,y);
		}
		
		
	}
}

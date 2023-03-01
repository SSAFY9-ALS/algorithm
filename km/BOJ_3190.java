/**
 * 뱀 / 골드 4 / 95분
 * https://www.acmicpc.net/problem/3190
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_3190 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = parseInt(br.readLine()); // 배열 크기
		int[][] arr = new int[n+2][n+2]; // 보드 정보 0 빈칸, 1 사과, 2벽, 3 뱀
		List<int[]> snake = new ArrayList<int[]>(); // 뱀정보
		
		for(int i=0;i<n+2;i++) {
			for(int j=0;j<n+2;j++) {
				// 벽 정보 채우기
				if(i==0 || j==0 || i==n+1 || j==n+1) arr[i][j]=2;
			}
		}
		
		
		int k = parseInt(br.readLine());
		for(int i=0;i<k;i++) {
			String[] tmp = br.readLine().split(" ");
			int a = parseInt(tmp[0]);
			int b = parseInt(tmp[1]);
			arr[a][b] = 1;// 사과정보 받기
		}
		k = parseInt(br.readLine()); // 방향 전환 횟수
		int[][] dir = new int[k][2];
		for(int i=0;i<k;i++) {
			String[] tmp = br.readLine().split(" ");
			int a = parseInt(tmp[0]);
			if(tmp[1].equals("D")) {
				dir[i][0] = a;
				dir[i][1] = 0; // 0이면 오른쪽으로 회전
			}
			else {
				dir[i][0] = a;
				dir[i][1] = 1; // 0이면 왼쪽으로 회전
			}
		}
		
		arr[1][1] = 3; // 뱀 시작 위치
		snake.add(new int[] {1,1}); 

		int answer=0;
		
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		
		int idx=0;
		int x = 1;
		int y = 1;
		int d = 1;
		
		while(true) {
			answer+=1;
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			// 벽이나 몸에 닿으면 종료
			if(arr[nx][ny]==2 || arr[nx][ny]==3) {
				break;
			}
			
			// 이동
			if(arr[nx][ny]==1) { // 사과인경우
				arr[nx][ny]=3; // 뱀
				snake.add(new int[] {nx,ny}); // 뱀 정보 추가
			}
			else {
				arr[nx][ny]=3;
				snake.add(new int[] {nx,ny}); // 뱀 정보 추가
				arr[snake.get(0)[0]][snake.get(0)[1]]=0;
				snake.remove(0); // 꼬리 지워주기
				
			}
			
			// 매초 방향 바뀌는지 확인해주기
			if(idx<k) {
				if(answer==dir[idx][0]) {
					if(dir[idx][1]==0) {
						d+=1;
						if(d==4) d=0;
					}
					else {
						d-=1;
						if(d==-1) d=3;
					}
					idx++;
				}
			}
			x=nx;
			y=ny;
		}
		
		System.out.println(answer);
	}

}

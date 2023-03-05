/**
 * 낚시왕 / 골드 1 / 300분
 * https://www.acmicpc.net/problem/17143
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_17143 {
	
	static int R;
	static int C;
	static int M;
	static Shark[][] arr;
	static int answer;
	
	static class Shark {
		int x;
		int y;
		int speed;
		int dir;
		int size;
		
		public Shark(int x, int y, int speed, int dir, int size) {
			super();
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	
	static void eat(int loc) {
		for(int i=0;i<R;i++) {
			if(arr[i][loc]!=null) {
				answer+= arr[i][loc].size;
				arr[i][loc]=null;
				break;
			}
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		R = parseInt(tmp[0]);
		C = parseInt(tmp[1]);
		M = parseInt(tmp[2]);

		arr = new Shark[R][C];
		
		for(int i=0;i<M;i++) { // 상어 위치 초기화
			tmp = br.readLine().split(" ");
			int x = parseInt(tmp[0])-1;
			int y = parseInt(tmp[1])-1; // 위치
			int s = parseInt(tmp[2]); // 속력
			int d = parseInt(tmp[3]); // 이동 방향
			int z = parseInt(tmp[4]); // 크기
			arr[x][y] = new Shark(x, y, s, d, z);
		}
		
		int[] dx = {0,-1,1,0,0};
		int[] dy = {0,0,0,1,-1};
		
		for(int c=0;c<C;c++) { // 행만큼 돌면서 먹음
			// 먹음
			eat(c);
			// 상어들 이동
			// 상어들 전부 큐에 집어넣고 꺼내 가면서 위치 바꿔줌
			
			Queue<Shark> sharkList = new ArrayDeque<>();
			
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					if(arr[i][j]!=null) {
						sharkList.add(new Shark(i, j, arr[i][j].speed, arr[i][j].dir, arr[i][j].size));
					}
				}
			}
			arr = new Shark[R][C]; // 새롭게 상어 위치 만들기 위해 생성
			
			while(!sharkList.isEmpty()) {
				Shark move = sharkList.poll();
				
				int distance = move.speed;
				if(move.dir==3 || move.dir==4) { // 좌우
					distance%=((C-1)*2);
				}
				else { // 상하
					distance%=((R-1)*2);
				}
				
				for(int i=0;i<distance;i++) {
					int nx = move.x + dx[move.dir];
					int ny = move.y + dy[move.dir];
					
					if(nx<0 || ny<0 || nx>=R || ny>=C) { // 범위 벗어남 => 방향 바꿔줌
						move.x -= dx[move.dir];
						move.y -= dy[move.dir];
						
						if(move.dir==1) move.dir+=1;
						else if(move.dir==2) move.dir-=1;
						else if(move.dir==3) move.dir+=1;
						else if(move.dir==4) move.dir-=1;
						continue;
					}
					move.x = nx;
					move.y = ny;
 				}
				// 상어 새로운 위치로 이동
				if(arr[move.x][move.y]==null) { // 빈곳이면 그냥 넣음
					arr[move.x][move.y] = new Shark(move.x, move.y, move.speed, move.dir, move.size);
				}
				else { // 비어있지 않으면 크기 비교후 큰 것 넣음
					if(arr[move.x][move.y].size < move.size) {
						arr[move.x][move.y] = new Shark(move.x, move.y, move.speed, move.dir, move.size);
					}
				}
			}
		}
		System.out.println(answer);
	}
	
}

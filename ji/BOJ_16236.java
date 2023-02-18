package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아기 상어 / 골드 3 / 177분
 * @author 민정인
 * https://www.acmicpc.net/problem/16236
 */

public class BOJ_16236 {
	static int n;
	static int sSize = 2;
	static int[][] map;
	static int[][] dMap;		// 현재 상어로부터 해당 위치의 거리
	static boolean[][] chk;		// 현재 상어로부터 해당 위치의 거리를 잴때 방문한 장소인지 확인
	static int[] dx = {-1, 0, 1, 0};	// 방향배열
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[n][n];
		dMap = new int[n][n];
		chk = new boolean[n][n];
		int eat = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0 && map[i][j] <= 6) {
					fish.add(new pair(i, j));
				} else if(map[i][j] == 9) {
					pos = new pair(i, j);
					map[i][j] = 0;
				}
			}
		}
		int result = 0;
		if(fish.size() != 0) {	// 물고기 배열이 없으면 이는 먹을 수 있는 물고기가 없다는 의미이므로 별도 연산 X
			while(!fish.isEmpty()) {	// 모든 물고기 배열을 없앨 경우까지 반복
				moving();				// 현재 상어의 위치로부터 모든 좌표의 거리
				int minCnt = Integer.MAX_VALUE;	// 최소 거리까지 걸린 시간(1칸 = 1초)
				pair tarPos = null;				// 가장 가까운 물고기의 좌표
				int idx = -1;					// 리스트에서 가장 가까운 물고기의 인덱스
				for(int i = 0; i < fish.size(); i++) {
					pair tmp = fish.get(i);
					if(map[tmp.x][tmp.y] < sSize && dMap[tmp.x][tmp.y] > 0) {	// 물고기의 크기가 상어보다 작고 거리가 1 이상일 때					
						if(minCnt > dMap[tmp.x][tmp.y]) {	// 최소 거리보다 더 거리가 작다면
							minCnt = dMap[tmp.x][tmp.y];	// 최소 거리 갱신 및 해당 인덱스 갱신
							tarPos = new pair(tmp.x, tmp.y);
							idx = i;
						}
					}
				}
				if(idx == -1) {	// 인덱스가 초기값일 경우 조건에 맞는 생선이 없으므로 상어는 물고기를 먹을 수 없다 -> while문 탈출
					break;
				}
				result += dMap[tarPos.x][tarPos.y];	// 현재 위치까지의 거리를 결과에 합산
				pos = new pair(tarPos.x, tarPos.y);	// 현재 상어의 위치는 지정한 물고기의 위치로 이동
				map[tarPos.x][tarPos.y]= 0;			// 물고기를 잡아먹었으므로 해당 위치는 0으로 갱신
				eat++;								// 상어가 먹은 물고기의 양 증가
				if(eat == sSize) {					// 먹은 양과 상어의 사이즈가 동일한 경우 먹은 물고기 0으로 초기화 및 상어의 크기 증가
					eat = 0;
					sSize++;
				}
				fish.remove(idx);					// 해당 물고기를 리스트에서 삭제
//				for(int i = 0; i < n; i++) {
//					for(int j = 0; j < n; j++) {
//						System.out.print(dMap[i][j] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
			}
		} 
		System.out.println(result);					// 결과 출력
	}
	
	static class pair{
		int x;
		int y;
		public pair() {}
		public pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static List<pair> fish = new ArrayList<>();
	static pair pos;
	static void moving() {
		Queue<int[]> q = new LinkedList<>();	// 모든 방향에 나오는 값을 확인하기 위한 queue
		chk = new boolean[n][n];				// 해당 위치에 가장 먼저 방문한 값을 기준으로 잡기 위해 chk배열 초기화
		dMap = new int[n][n];					// 상어의 위치에 따라 거리가 매번 달라지므로 dMap배열 초기화
		q.add(new int [] {pos.x, pos.y, 0});	// 현재 상어의 위치를 넣고 시작
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int x = tmp[0];
			int y = tmp[1];
			int dist = tmp[2];
			for(int i = 0; i < 4; i++) {		// 맵 밖으로 나가거나 이미 방문했거나 현재 위치(이전에 이동한 위치)에 상어보다 큰 물고기가 있을 경우: q에 넣지 않음
				if(x + dx[i] < 0 || x + dx[i] >= n || y + dy[i] < 0 || y + dy[i] >= n || chk[x + dx[i]][y + dy[i]]
						|| map[x][y] > sSize) {
					continue;
				}
				chk[x + dx[i]][y + dy[i]] = true;	// 현재 위치를 방문하였다는 의미로 chk 표시
				dMap[x + dx[i]][y + dy[i]] = dist + 1;	// 현재 위치는 이전에 방문한 위치까지의 거리 + 1
				q.add(new int[] {x + dx[i], y + dy[i], dist + 1});	// 갱신된 위치와 거리를 q에 다시 넣음
			}
		}
	}
}

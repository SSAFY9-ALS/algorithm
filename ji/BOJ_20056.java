package net.acmicpc;

import java.util.*;
import java.io.*;

/**
 * 마법사 상어와 파이어볼 / 골드 4 / 103분
 * @author 민정인
 * https://www.acmicpc.net/problem/20056
 */

public class BOJ_20056 {
	static int n, m, k;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};	// 방향 배열
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static class Fire {	// 파이어볼 정보를 담는 클래스
		int x;
		int y;
		int m;
		int spd;
		int dir;
		public Fire(int x, int y, int m, int spd, int dir) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.spd = spd;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "x=" + x + ", y=" + y + ", m=" + m + ", spd=" + spd + ", dir=" + dir + "]";
		}
		
	}
	static Queue<Fire> q = new LinkedList<>();	// 파이어볼 저장 큐
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			Fire f = new Fire(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			q.add(f);
		}
		for(int i = 0; i < k; i++) {
			diffuse();
		}
		int result = 0;
		while(!q.isEmpty()) {
			Fire f = q.poll();
			result += f.m;
		}
		System.out.println(result);
	}
	
	static void diffuse() {
		ArrayList<Fire> list = new ArrayList<>();		// 다음 파이어볼 정보를 임시로 저장하는 리스트
		ArrayList<Fire>[][] map = new ArrayList[n+1][n+1];	// 각 위치에 담긴 파이어볼들 저장하는 리스트 배열
		boolean[][] chkMap = new boolean[n+1][n+1];
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j <= n; j++) {
				map[i][j] = new ArrayList<Fire>();
			}
		}
		while(!q.isEmpty()) {
			Fire f = q.poll();
			int nextX = f.x + dx[f.dir] * f.spd;
			while(nextX <= 0) {	// 범위 밖으로 나가는 좌표들 범위 내로 넣기
				nextX += n;
			}
			while(nextX > n) {
				nextX -= n;
			}
			int nextY = f.y + dy[f.dir] * f.spd;
			while(nextY <= 0) {
				nextY += n;
			}
			while(nextY > n) {
				nextY -= n;
			}
			Fire next = new Fire(nextX, nextY, f.m, f.spd, f.dir);
			map[nextX][nextY].add(next);	// 다음 위치에 놓인 파이어볼들 맵에 저장 및 리스트에 저장
			list.add(next);
		}
		for(int i = 0; i < list.size(); i++) {		// 리스트를 돌면서 해당 위치에 파이어볼이 하나면 바로 큐에 추가
			Fire f = list.get(i);
			if(map[f.x][f.y].size() == 1) {
				q.add(f);
			} else {								// 2개 이상 파이어볼이 겹친 경우 해당 위치는 이 인덱스에 한번에 처리하므로 다음부터 처리하지 않도록 세팅
				if(chkMap[f.x][f.y]) {
					continue;
				}
				chkMap[f.x][f.y] = true; 
				int mSum = 0;
				int sSum = 0;
				int d = -1;
				int chk = 0;
				for(int j = 0; j < map[f.x][f.y].size(); j++) {	// 파이어볼의 질량의 합과 속도의 합을 구함
					mSum += map[f.x][f.y].get(j).m;
					sSum += map[f.x][f.y].get(j).spd;
					if(d == -1) {								// 초기값이 유지되었으면 방향 확인용 값으로 변경(전부 홀수거나 짝수이므로)
						d = map[f.x][f.y].get(j).dir % 2;
					} else {
						if(map[f.x][f.y].get(j).dir % 2 != d) {	// 초기값 이후의 값과 홀수 혹은 짝수인 점이 다르다면 chk값 갱신
							chk = 1;
						}
					}
				}
				mSum /= 5;
				if(mSum == 0) {						// 질량이 0이면 해당하는 4개의 파이어볼 전부 소멸(질문게시판 참고)
					continue;
				}
				sSum /= map[f.x][f.y].size();
				Fire next1 = new Fire(f.x, f.y, mSum, sSum, 0 + chk);	// 모두 홀수 혹은 짝수라면 0246, 아니라면 1357이므로 chk값(0 or 1)을 더해 주어 표시
				Fire next2 = new Fire(f.x, f.y, mSum, sSum, 2 + chk);
				Fire next3 = new Fire(f.x, f.y, mSum, sSum, 4 + chk);
				Fire next4 = new Fire(f.x, f.y, mSum, sSum, 6 + chk);
				q.add(next1);						// 나뉜 파이어볼들 큐에 추가
				q.add(next2);
				q.add(next3);
				q.add(next4);
			}
		}
	}
}

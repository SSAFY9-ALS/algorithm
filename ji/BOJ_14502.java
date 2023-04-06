package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 연구소 / 골드 4 / 41분
 * @author 민정인
 * https://www.acmicpc.net/status?user_id=mji420908&problem_id=14502&from_mine=1
 */

public class BOJ_14502 {
	static int n, m;
	static int[][] map;
	static int[][] tmpMap;
	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		tmpMap = new int[n][m];					// 맵의 정보를 받아올 임시 맵
		List<Pair> list = new ArrayList<>();	// 빈칸의 리스트
		List<Pair> virus = new ArrayList<>();	// 바이러스의 리스트
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					list.add(new Pair(i, j));
				} else if(map[i][j] == 2) {
					virus.add(new Pair(i, j));
				}
			}
		}
		int[] idx = new int[list.size()];
		int maxVal = Integer.MIN_VALUE;
		Arrays.fill(idx, list.size() - 3, list.size(), 1);
		do {									// 벽의 위치의 조합
			for(int i = 0; i < n; i++) {		// 임시 맵에 정보 입력
				tmpMap[i] = map[i].clone();
			}
			for(int i = 0; i < idx.length; i++) {
				if(idx[i] == 1) {				// 벽의 위치의 조합에 맞게 벽 생성
					tmpMap[list.get(i).x][list.get(i).y] = 1;
				}
			}
			for(int i = 0; i < virus.size(); i++) {	// 바이러스들의 감염 진행
				infection(virus.get(i));
			}
			maxVal = Math.max(maxVal, count());		// 남은 공간의 수 최대값 비교
		} while(np(idx));
		System.out.println(maxVal);					// 결과 출력
	}
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int count() {
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(tmpMap[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	static void infection(Pair p) {	// bfs를 통해 감염 진행
		Queue<Pair> q = new LinkedList<>();
		q.add(p);
		while(!q.isEmpty()) {
			Pair tmp = q.poll();
			for(int i = 0; i < 4; i++) {
				if(tmp.x + dx[i] < 0 || tmp.x + dx[i] >= n || tmp.y + dy[i] < 0 || tmp.y + dy[i] >= m || tmpMap[tmp.x + dx[i]][tmp.y + dy[i]] != 0) {
					continue;
				}
				tmpMap[tmp.x + dx[i]][tmp.y + dy[i]] = 2;
				q.add(new Pair(tmp.x + dx[i], tmp.y + dy[i]));
			}
		}
	}
	static boolean np(int[] idx) {	// 조합을 위한 진행
		int i = idx.length - 1;
		while(i > 0 && idx[i-1] >= idx[i]) --i;
		if(i == 0) return false;
		int j = idx.length - 1;
		while(idx[i-1] >= idx[j]) --j;
		swap(idx, i - 1, j);
		int k = idx.length - 1;
		while(i < k) swap(idx, i++, k--);
		return true;
	}
	static void swap(int[] idx, int i, int j) {
		int tmp = idx[i];
		idx[i] = idx[j];
		idx[j] = tmp;
	}
}

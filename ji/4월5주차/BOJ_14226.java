package net.acmicpc;

import java.util.*;

/**
 * 이모티콘 / 골드 4 / 47분
 * @author 민정인
 * https://www.acmicpc.net/problem/14226
 */

public class BOJ_14226 {
	static int n;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		bfs();
		System.out.println(result);
	}
	
	static void bfs() {	// bfs 진행
		Queue<int[]> q = new ArrayDeque<>();
		int[] start = {1, 1, 1};
		q.add(start);
		int[][] arr = new int[2001][2001];	// [현재 길이][클립보드에 저장한 길이] 위치까지 걸린 시간
		for(int i = 0; i < 2001; i++) {
			Arrays.fill(arr[i], Integer.MAX_VALUE);	// 최대값 저장
		}
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			if(tmp[0] == n) {		// 현재 위치에 가장 먼저 도달한 경우
				result = tmp[1];
				return;
			}
			// 다음 좌표(더해진 길이, 클립보드 길이) 위치에 도달한 값보다 걸린 시간 + 1이 더 작다면 큐에 추가
			if(tmp[0]+tmp[2] <= 2000 && arr[tmp[0]+tmp[2]][tmp[2]] > tmp[1]+1) {
				q.add(new int[] {tmp[0] + tmp[2], tmp[1] + 1, tmp[2]});
				arr[tmp[0]+tmp[2]][tmp[2]] = tmp[1]+1;
			}
			if(tmp[0] != tmp[2]) {				
				q.add(new int[] {tmp[0], tmp[1] + 1, tmp[0]});
			}
			if(tmp[0] > 0 && arr[tmp[0] - 1][tmp[2]] > tmp[1] + 1) {				
				q.add(new int[] {tmp[0] - 1, tmp[1] + 1, tmp[2]});
				arr[tmp[0] - 1][tmp[2]] = tmp[1] + 1;
			}
		}
	}
}

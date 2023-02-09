package net.acmicpc;

import java.util.Scanner;
/**
 * 바이러스 / 실버 3 / 14분
 * @author 민정인
 * https://www.acmicpc.net/problem/2606
 */
public class BOJ_2606 {
	static int[][] arr;		// 연결 정보 배열
	static int m;			// 간선의 수 정보
	static int cnt = 0;		// 감염되는 컴퓨터 수
	static boolean[] com;	// 지나온 컴퓨터 확인
	static void findCom(int k) {
		int chk = 0;
		for(int i = 0; i < m; i++) {		// 양방향성 연결이므로 arr[i][0]과 arr[i][1] 모두 시작점으로 인지하고 진
			if(arr[i][0] == k && com[arr[i][1]] == false) {
				cnt++;
				com[arr[i][1]] = true;
				findCom(arr[i][1]);
			} else if(arr[i][1] == k && com[arr[i][0]] == false) {
				cnt++;
				com[arr[i][0]] = true;
				findCom(arr[i][0]);
			} else {
				chk++;
			}
		}
		if(chk == m) {						// 연결된 점이 없거나 이미 모두 지나온 경우 리턴
			return;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[m][2];
		com = new boolean[n + 1];
		for(int i = 0; i < m; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		com[1] = true;						// 1번에서 시작하므로 com[1]은 true
		findCom(1);							// 함수 호출
		System.out.println(cnt);			// 결과 출력
	}
}

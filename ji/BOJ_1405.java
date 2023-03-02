package net.acmicpc;

import java.io.*;
import java.util.*;
import java.awt.*;

/**
 * 미친 로봇 / 골드 4 / 46분
 * @author 민정인
 * https://www.acmicpc.net/problem/1405
 */

public class BOJ_1405 {
	static class Percent{	// 현재 위치와 이동 횟수, 확률 저장하는 클래스
		Point p;
		int cnt;
		double percent;
		public Percent(Point p, int cnt, double percent) {
			this.p = p;
			this.cnt = cnt;
			this.percent = percent;
		}
	}
	static int n;
	static boolean[][] chk;	// 방문여부
	static double[] dir;	// 이동 방향별 확률
	static double result;	// 총 확률
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		dir = new double[4];
		for(int i = 0; i < 4; i++) {	// 동, 서, 남, 북
			int val = sc.nextInt();
			dir[i] = (double) val / 100;	// 퍼센트 단위이므로 100으로 나눔
		}
		Point cur = new Point(n, n);
		chk = new boolean[n * 2 + 1][n * 2 + 1];
		chk[n][n] = true;
		dfs(cur, 0, 1);
		System.out.println(result);
	}
	static void dfs(Point p, int cnt, double d) {	// 각 방향별 dfs를 진행하고 이동 횟수만큼 도달하면 결과 확률에 더한 후 리턴
		if(cnt == n) {
			result += d;
			return;
		}
		if(chk[p.x][p.y + 1] == false) {
			chk[p.x][p.y + 1] = true;
			dfs(new Point(p.x, p.y + 1), cnt + 1, d * dir[0]);
			chk[p.x][p.y + 1] = false;
		}
		if(chk[p.x][p.y - 1] == false) {
			chk[p.x][p.y - 1] = true;
			dfs(new Point(p.x, p.y - 1), cnt + 1, d * dir[1]);
			chk[p.x][p.y - 1] = false;
		}
		if(chk[p.x + 1][p.y] == false) {
			chk[p.x + 1][p.y] = true;
			dfs(new Point(p.x + 1, p.y), cnt + 1, d * dir[2]);
			chk[p.x + 1][p.y] = false; 
		}
		if(chk[p.x - 1][p.y] == false) {
			chk[p.x - 1][p.y] = true;
			dfs(new Point(p.x - 1, p.y), cnt + 1, d * dir[3]);
			chk[p.x - 1][p.y] = false; 
		}
	}
}

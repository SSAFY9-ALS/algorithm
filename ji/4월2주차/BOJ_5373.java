package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 큐빙 / 플레 5 / 105분
 * @author 민정인
 * https://www.acmicpc.net/problem/5373
 */

public class BOJ_5373 {
	//   B
	// L U R
	//   F
	//   D
	
	static char[][] tCube = {
			{' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' '},
			{' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' '},
			{' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' '},
			{'g', 'g', 'g', 'w', 'w', 'w', 'b', 'b', 'b'},
			{'g', 'g', 'g', 'w', 'w', 'w', 'b', 'b', 'b'},
			{'g', 'g', 'g', 'w', 'w', 'w', 'b', 'b', 'b'},
			{' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' '},
			{' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' '},
			{' ', ' ', ' ', 'y', 'y', 'y', ' ', ' ', ' '},
			{' ', ' ', ' ', 'y', 'y', 'y', ' ', ' ', ' '},
			{' ', ' ', ' ', 'y', 'y', 'y', ' ', ' ', ' '}
	};
	static char[][] cube;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i < t; i++) {
			cube = new char[12][9];
			for(int j = 0; j < 12; j++) {
				cube[j] = tCube[j].clone();
			}
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				String s = st.nextToken();
				char side = s.charAt(0);
				char dir = s.charAt(1);
				moveCube(side, dir);
//				for(int l = 3; l < 6; l++) {
//					for(int k = 3; k < 6; k++) {
//						System.out.print(cube[l][k]);
//					}
//					System.out.println();
//				}
//				System.out.println();
			}
			for(int j = 3; j < 6; j++) {
				for(int k = 3; k < 6; k++) {
					System.out.print(cube[j][k]);
				}
				System.out.println();
			}
//			System.out.println();
		}
	}
	static void moveCenter(int r, int c, char dir) {
		Deque<Character> dq = new ArrayDeque<>();
		dq.addLast(cube[r-1][c-1]);
		dq.addLast(cube[r-1][c]);
		dq.addLast(cube[r-1][c+1]);
		dq.addLast(cube[r][c+1]);
		dq.addLast(cube[r+1][c+1]);
		dq.addLast(cube[r+1][c]);
		dq.addLast(cube[r+1][c-1]);
		dq.addLast(cube[r][c-1]);
		if(dir == '+') {
			cube[r-1][c+1] = dq.pollFirst();
			cube[r][c+1] = dq.pollFirst();
			cube[r+1][c+1] = dq.pollFirst();
			cube[r+1][c] = dq.pollFirst();
			cube[r+1][c-1] = dq.pollFirst();
			cube[r][c-1] = dq.pollFirst();
			cube[r-1][c-1] = dq.pollFirst();
			cube[r-1][c] = dq.pollFirst();
		} else {
			cube[r+1][c-1] = dq.pollFirst();
			cube[r][c-1] = dq.pollFirst();
			cube[r-1][c-1] = dq.pollFirst();
			cube[r-1][c] = dq.pollFirst();
			cube[r-1][c+1] = dq.pollFirst();
			cube[r][c+1] = dq.pollFirst();
			cube[r+1][c+1] = dq.pollFirst();
			cube[r+1][c] = dq.pollFirst();
		}
	}
	static void moveCube(char side, char dir) {
		Deque<Character> dq1 = new ArrayDeque<>();
		Deque<Character> dq2 = new ArrayDeque<>();
		Deque<Character> dq3 = new ArrayDeque<>();
		Deque<Character> dq4 = new ArrayDeque<>();
		switch(side) {
		case 'U':
			moveCenter(4, 4, dir);
			for(int i = 3; i < 6; i++) {
				dq1.addLast(cube[2][i]);
				dq2.addLast(cube[i][6]);
				dq3.addLast(cube[6][8-i]);
				dq4.addLast(cube[8-i][2]);
			}
			if(dir == '+') {
				for(int i = 3; i < 6; i++) {
					cube[2][i] = dq4.pollFirst();
					cube[i][6] = dq1.pollFirst();
					cube[6][8-i] = dq2.pollFirst();
					cube[8-i][2] = dq3.pollFirst();
				}
			} else {
				for(int i = 3; i < 6; i++) {
					cube[2][i] = dq2.pollFirst();
					cube[i][6] = dq3.pollFirst();
					cube[6][8-i] = dq4.pollFirst();
					cube[8-i][2] = dq1.pollFirst();
				}
			}
			break;
		case 'D':
			moveCenter(10, 4, dir);
			for(int i = 3; i < 6; i++) {
				dq1.addLast(cube[8][i]);
				dq2.addLast(cube[8-i][8]);
				dq3.addLast(cube[0][8-i]);
				dq4.addLast(cube[i][0]);
			}
			if(dir == '+') {
				for(int i = 3; i < 6; i++) {
					cube[8][i] = dq4.pollFirst();
					cube[8-i][8] = dq1.pollFirst();
					cube[0][8-i] = dq2.pollFirst();
					cube[i][0] = dq3.pollFirst();
				}
			} else {
				for(int i = 3; i < 6; i++) {
					cube[8][i] = dq2.pollFirst();
					cube[8-i][8] = dq3.pollFirst();
					cube[0][8-i] = dq4.pollFirst();
					cube[i][0] = dq1.pollFirst();
				}
			}
			break;
		case 'F':
			moveCenter(7, 4, dir);
			for(int i = 3; i < 6; i++) {
				dq1.addLast(cube[5][i]);
				dq2.addLast(cube[5][i+3]);
				dq3.addLast(cube[9][8-i]);
				dq4.addLast(cube[5][i-3]);
			}
			if(dir == '+') {
				for(int i = 3; i < 6; i++) {
					cube[5][i] = dq4.pollFirst();
					cube[5][i+3] = dq1.pollFirst();
					cube[9][8-i] = dq2.pollFirst();
					cube[5][i-3] = dq3.pollFirst();
				}
			} else {
				for(int i = 3; i < 6; i++) {
					cube[5][i] = dq2.pollFirst();
					cube[5][i+3] = dq3.pollFirst();
					cube[9][8-i] = dq4.pollFirst();
					cube[5][i-3] = dq1.pollFirst();
				}
			}
			break;
		case 'B':
			moveCenter(1, 4, dir);
			for(int i = 3; i < 6; i++) {
				dq1.addLast(cube[11][i]);
				dq2.addLast(cube[3][11-i]);
				dq3.addLast(cube[3][8-i]);
				dq4.addLast(cube[3][5-i]);
			}
			if(dir == '+') {
				for(int i = 3; i < 6; i++) {
					cube[11][i] = dq4.pollFirst();
					cube[3][11-i] = dq1.pollFirst();
					cube[3][8-i] = dq2.pollFirst();
					cube[3][5-i] = dq3.pollFirst();
				}
			} else {
				for(int i = 3; i < 6; i++) {
					cube[11][i] = dq2.pollFirst();
					cube[3][11-i] = dq3.pollFirst();
					cube[3][8-i] = dq4.pollFirst();
					cube[3][5-i] = dq1.pollFirst();
				}
			}
			break;
		case 'L':
			moveCenter(4, 1, dir);
			for(int i = 0; i < 12; i++) {
				dq1.addLast(cube[i][3]);
			}
			if(dir == '+') {
				for(int i = 3; i < 12; i++) {
					cube[i][3] = dq1.pollFirst();
				}
				for(int i = 0; i < 3; i++) {
					cube[i][3] = dq1.pollFirst();
				}
			} else {
				for(int i = 9; i < 12; i++) {
					cube[i][3] = dq1.pollFirst();
				}
				for(int i = 0; i < 9; i++) {
					cube[i][3] = dq1.pollFirst();
				}
			}
			break;
		case 'R':
			moveCenter(4, 7, dir);
			for(int i = 11; i >= 0; i--) {
				dq1.addLast(cube[i][5]);
			}
			if(dir == '+') {
				for(int i = 8; i >= 0; i--) {
					cube[i][5] = dq1.pollFirst();
				}
				for(int i = 11; i >= 9; i--) {
					cube[i][5] = dq1.pollFirst();
				}
			} else {
				for(int i = 2; i >= 0; i--) {
					cube[i][5] = dq1.pollFirst();
				}
				for(int i = 11; i >= 3; i--) {
					cube[i][5] = dq1.pollFirst();
				}
			}
			break;
		}
	}
}

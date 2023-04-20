package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 큐빙 / 플래티넘5 / 2시간(솔루션)
 * https://www.acmicpc.net/problem/5373
 */

public class BOJ_5373 {
	static char[][][] cube;

	// 큐브 초기화 메서드
	static void initCube() {
		char[] color = { 'w', 'y', 'r', 'o', 'g', 'b' };
		cube = new char[6][3][3];
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 3; j++) {
				for(int k = 0; k < 3; k++) {
					cube[i][j][k] = color[i];
				}
			}
		}
	}

	// 큐브를 회전시키는 메서드
	static void changeCube(int f, int u, int l, int d, int r, char direction) {
		char[][] tmp = new char[3][3];
		char[] tmp2 = new char[3];

		if(direction == '+') {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					tmp[i][j] = cube[f][2-j][i];
				}
			}
			for(int i = 0; i < 3; ++i)
				tmp2[i] = cube[u][i][0];
			for(int i = 0; i < 3; ++i)
				cube[u][i][0] = cube[l][0][2-i];
			for(int i = 0; i < 3; ++i)
				cube[l][0][2 - i] = cube[d][2][i];
			for(int i = 0; i < 3; ++i)
				cube[d][2][i] = cube[r][2-i][2];
			for(int i = 0; i < 3; ++i)
				cube[r][2-i][2] = tmp2[i];
		}
		else {
			for(int i = 0; i < 3; ++i) {
				for(int j = 0; j < 3; ++j) {
					tmp[i][j] = cube[f][j][2-i];
				}
			}
			for(int i = 0; i < 3; ++i)
				tmp2[i] = cube[u][i][0];
			for(int i = 0; i < 3; ++i) //
				cube[u][i][0] = cube[r][2-i][2];
			for(int i = 0; i < 3; ++i)
				cube[r][2-i][2] = cube[d][2][i];
			for(int i = 0; i < 3; ++i)
				cube[d][2][i] = cube[l][0][2-i];
			for(int i = 0; i < 3; ++i)
				cube[l][0][2-i] = tmp2[i];
		}
		cube[f] = tmp;
	}

	// 큐브 회전값 구하는 메서드
	static void turnCube(char plane, char d) {
		switch (plane) {
		case 'U':
			changeCube(0, 4, 2, 5, 3, d);
			break;
		case 'D':
			changeCube(1, 3, 5, 2, 4, d);
			break;
		case 'F':
			changeCube(2, 0, 4, 1, 5, d);
			break;
		case 'B':
			changeCube(3, 5, 1, 4, 0, d);
			break;
		case 'L':
			changeCube(4, 2, 0, 3, 1, d);
			break;
		case 'R':
			changeCube(5, 1, 3, 0, 2, d);
			break;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(in.readLine());

		for(int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(in.readLine());
			initCube();
			st = new StringTokenizer(in.readLine());
			for(int c = 0; c < n; c++) {
				String sen = st.nextToken();
				turnCube(sen.charAt(0), sen.charAt(1));
			}
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++)
					System.out.print(cube[0][j][2-i]);
				System.out.println();
			}
		}
	}
}
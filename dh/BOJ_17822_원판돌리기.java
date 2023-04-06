import java.util.*;
import java.awt.Point;
import java.io.*;
/**
 * 
 * 원판 돌리기 / 골드 2 / 100분
 * https://www.acmicpc.net/problem/17822
 * 
 */
public class Main {
	static int n;
	static int m;
	static int t;
	static int[][] onepan;
	static int[][] rotation;
	static int[][] test;
	static boolean[][] visited;
	static int answer;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		onepan = new int[n + 1][m + 1];
		rotation = new int[t][3];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				onepan[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				rotation[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve();
		System.out.println(answer);
	}

	static void solve() {
		for (int i = 0; i < t; i++) {
			rot(i);
			// 회전
			delete();
			// 삭제 및 원판 처리
		}
		calc();
		// 원판에 적힌 수의 합 계산
	}

	static void rot(int num) {
		int x = rotation[num][0];
		int d = rotation[num][1];
		int k = rotation[num][2];
		int count = 1;
		int row = x;
		while (row <= n) {
			int temp = 0;
			if (d == 0) {
				for (int i = 0; i < k; i++) {
					temp = onepan[row][m];
					for (int l = m; l > 1; l--) {
						onepan[row][l] = onepan[row][l - 1];
					}
					onepan[row][1] = temp;
					// 시계방향으로 회전
				}

			} else {
				for (int i = 0; i < k; i++) {
					temp = onepan[row][1];
					for (int l = 1; l < m; l++) {
						onepan[row][l] = onepan[row][l + 1];
					}
					onepan[row][m] = temp;
					// 반시계방향으로 회전
				}
			}
			count++;
			row = x * count;
		}

	}

	static void delete() {
		Queue<Point> q = new ArrayDeque<>();
		test = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if(onepan[i][j] != 0) {
					for (int d = 0; d < 4; d++) {
						int lx = i + dx[d];
						int ly = j + dy[d];
						if (ly == 0) {
							ly = m;
						} else if (ly == m + 1) {
							ly = 1;
						}
						if (lx >= 1 && lx <= n) {
							if (onepan[i][j] == onepan[lx][ly]) {
								test[i][j] = 1;
								test[lx][ly] = 1;
								// test 임시 배열에 지울 숫자 기록
							}
						}
					}
				}
				
			}
		}
		boolean flag = false;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (test[i][j] == 1) {
					flag = true;
					onepan[i][j] = 0;
					// 바꿔야 할 숫자가 있으면 flag변경 후 0으로 설정
				}
			}
		}
		
		if (!flag) {
			// 바뀔 숫자가 없다면 평균 계산하여 원판 처리
			int count = 0;
			int sum = 0;
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					if (onepan[i][j] != 0) {
						count++;
						sum += onepan[i][j];
						// 평균 계산
					}
				}
			}
			double avg = sum / (double)count;
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					if (onepan[i][j] != 0) {
						if(onepan[i][j] > avg) {
							onepan[i][j]--;
							// 1 감소
						}
						else if(onepan[i][j] < avg) {
							onepan[i][j]++;
							// 1 증가
						}
					}
				}
			}
		}

	}

	static void calc() {
		// 원판에 적힌 수의 합 계산
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				answer += onepan[i][j];
			}
		}

	}

}

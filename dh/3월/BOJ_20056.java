import java.io.*;
import java.util.*;

/**
 * 
 * 마법사 상어와 파이어볼 / 골드4 / 70분
 * https://www.acmicpc.net/problem/20056
 *
 */

class Fireball {
	int x;
	int y;
	int m;
	int s;
	int d;

	public Fireball(int x, int y, int m, int s, int d) {
		super();
		this.x = x;
		this.y = y;
		this.m = m;
		this.s = s;
		this.d = d;
	}
}

public class Main {
	static int N, M, K;
	static ArrayList<Fireball>[][] map;
	static ArrayList<Fireball>[][] copy;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new ArrayList[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[x][y].add(new Fireball(x, y, m, s, d));
		}

		for (int index = 0; index < K; index++) {
			copy = new ArrayList[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					copy[i][j] = new ArrayList<>();
				}
			}
			// 변경된 사항을 저장할 copy
			move();
			// 파이어볼 이동
			settingFireball();
			// 이동한 파이어볼 중 2개이상의 파이어볼 세팅
			map = copy.clone();
			// 변경된 사항을 map에 clone
		}
		calc();
		// 질량계산
		System.out.println(answer);
	}

	static void move() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 0; k < map[i][j].size(); k++) {
					// 리스트에 들어있는 수 만큼 반복
					Fireball tmp = map[i][j].get(k);
					int lx = tmp.x;
					int ly = tmp.y;
					for (int l = 0; l < tmp.s; l++) {
						lx += dx[tmp.d];
						if (lx < 1) {
							lx = N;
						} else if (lx > N) {
							lx = 1;
						}
						ly += dy[tmp.d];
						if (ly < 1) {
							ly = N;
						} else if (ly > N) {
							ly = 1;
						}
					}
					// 한 파이어볼씩 꺼내서 방향에 맞게 이동
					// 첫 행과 열이 마지막 행과 열이 붙어있으므로 이어줌
					copy[lx][ly].add(new Fireball(lx, ly, tmp.m, tmp.s, tmp.d));
					// copy에 이동된 파이어볼 저장
				}
			}
		}
	}

	static void settingFireball() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (copy[i][j].size() > 1) {
					// 2개 이상의 파이어볼만 변경
					int sum_m = 0;
					int sum_s = 0;
					int count = copy[i][j].size();
					int even = 0;
					int odd = 0;
					boolean check = false;
					// 짝수 혹은 홀수만 있는지 판별하는 check
					int[] dirT = { 0, 2, 4, 6 };
					// 짝수 혹은 홀수만 있을때의 방향
					int[] dirF = { 1, 3, 5, 7 };
					// 그 외의 방향
					for (int k = 0; k < count; k++) {
						Fireball tmp = copy[i][j].get(k);
						sum_m += tmp.m;
						sum_s += tmp.s;
						// 각각의 질량 속력 더해주기
						if (tmp.d % 2 == 0)
							even++;
						// 짝수 갯수 체크
						else
							odd++;
						// 홀수 갯수 체크
					}
					if (even == 0 || odd == 0) {
						check = true;
						// 짝수 혹은 홀수로만 이루어져 있으면 check 변경
					}
					copy[i][j].clear();
					// 리스트 안에 있는 값 삭제
					int sum = sum_m/5;
					// 질량 구하기
					if(sum > 0) {
						// 질량이 0이 아닐때 추가
						for(int k = 0; k < 4; k++) {
							// 방향에 맞게 4개의 파이어볼 추가
							if(check) {
								copy[i][j].add(new Fireball(i, j, sum, sum_s/count, dirT[k]));
							}
							else {
								copy[i][j].add(new Fireball(i, j, sum, sum_s/count, dirF[k]));
							}
						}
					}
				}
			}
		}
	}
	
	static void calc() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for(int k = 0; k < map[i][j].size(); k++) {
					answer += map[i][j].get(k).m;
					// 파이어볼의 질량 구하기
				}
			}
		}
	}
}

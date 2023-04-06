import java.util.*;
import java.io.*;
/**
 * 
 * 마법사 상어와 파이어스톰 / 골드 3 / 150분
 * https://www.acmicpc.net/problem/20058
 * 
 */
public class Main {
	static int n;
	static int q;
	static int sum;
	static int big;
	static int temp;
	static int[][] map;
	static int[][] tempmap;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// 1. 단계 별로 배열 돌리기
		// 2. 인접한 얼음이 2개 이하인 얼음칸은 1 삭제
		// 3. 모든 단계가 실행되면 전체 배열의 합, 가장 큰 덩어리가 차지하는 칸의 개수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		n = (int) Math.pow(2, n);
		q = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		tempmap = new int[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < q; i++) {
			int l = Integer.parseInt(st.nextToken());
//			l = (int) Math.pow(2, l);
//			for (int x = 0; x < n; x += l) {
//				for (int y = 0; y < n; y += l) {
//					rotation(x, y, l);
//				}
//			}
			fireStorm(0, 0, l);
			// 회전하기
			tempmap = map.clone();
			melting();
			map = tempmap.clone();
//			for(int a = 0; a < n; a++) {
//				for(int j = 0; j < n; j++) {
//					System.out.print(tempmap[a][j] + " ");
//				}System.out.println();
//			}
//			System.out.println();
		}

		calcSum();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (tempmap[i][j] > 0 && !visited[i][j]) {
					calcBic(i, j);
				}
				big = Math.max(big, temp);
				temp = 0;
			}
		}

		System.out.println(sum);
		System.out.println(big);

	}

//	private static void rotation(int x, int y, int l) {
//		for (int i = 0; i < l; i++) {
//			for (int j = 0; j < l; j++) {
//				tempmap[y + j][x + l - 1 - i] = map[y + i][x + j];
//			}
//		}
//	}

	
	private static void fireStorm(int startX, int startY, int L) {
        // startX, startY부터 startX+2^L, startY+2^L까지가 범위
        // 그걸 돌리고,
        // startX+2^L, startY+2^L 둘다 mapSize-1 이면, 재귀안해
        // 둘다 mapSize-1이 아니면, fireStorm(startX+2^L, startY+2^L, L호출)
        int tmpSize = (int)(Math.pow(2, L));
        int[][] tmp = new int[tmpSize][tmpSize];
        for(int i = 0; i < tmpSize; i++) {
            for(int j = 0 ; j< tmpSize; j++) {
                tmp[i][j] = map[i+startX][j+startY]; 
            }
        }
        for(int i = 0; i < tmpSize; i++) {
            for(int j = 0 ; j< tmpSize; j++) {
                map[i+startX][j+startY] = tmp[tmpSize-j-1][i]; 
            }
        }
        if(startY+tmpSize != n ) {
            fireStorm(startX, startY+tmpSize, L);
        }else if(startX+tmpSize != n) {
            fireStorm(startX+tmpSize, 0, L);
        }
    }
	
	
	private static void melting() {
		// 얼음 없애는 함수
		int[][] temptempmap = new int [n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				temptempmap[i][j] = tempmap[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int count = 0;
				if (tempmap[i][j] > 0) {
					for (int index = 0; index < 4; index++) {
						int lx = i + dx[index];
						int ly = j + dy[index];
						if (lx >= 0 && lx < n && ly >= 0 && ly < n) {
							if (tempmap[lx][ly] > 0) {
								count++;
							}
						}
					}
					if (count < 3) {
						temptempmap[i][j]--;
					}
				}
			}
		}
		tempmap = temptempmap.clone();
	}

	private static void calcSum() {
		//
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sum += tempmap[i][j];
			}
		}

	}

	private static void calcBic(int i, int j) {
		// 얼음 덩어리 계산하는 함수 ( dfs )
		temp++;
		visited[i][j] = true;
		for (int k = 0; k < 4; k++) {
			int lx = i + dx[k];
			int ly = j + dy[k];
			if (lx >= 0 && lx < n && ly >= 0 && ly < n && tempmap[lx][ly] > 0 && !visited[lx][ly]) {
				visited[lx][ly] = true;
				calcBic(lx, ly);
			}
		}

	}
}

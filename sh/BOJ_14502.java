import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 연구소 / 골드 4 / 2시간
 * @author 소수현
 * https://www.acmicpc.net/problem/14502
 */
public class BJ_14502_연구소 {
	
	static final int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}}; 
	// virus 개수
	static int virusCnt;
	static int N,M;
	static int[][] map;
	static boolean[][] isSpread;
	static LinkedList<int[]> originalVirus;
	static LinkedList<int[]> virus; 
	// 벽을 세울 선택한 위치
	static int[][] wall = new int[3][2];
	// 결과
	static int maxSafe = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 지도 세로 크기 : N, 가로 크기 : M
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 지도
		map = new int[N][M];
		
		// 바이러스 원인 좌표
		originalVirus = new LinkedList<>();
		// 퍼진 바이러스 좌표 리스트
		virus = new LinkedList<>();
		
		// 지도 정보 받아오기
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				
				if(map[n][m] == 2) {
					originalVirus.add(new int[] {n, m});
				} else if(map[n][m] == 0) {
					virus.add(new int[] {n, m});
					virusCnt++;
				}
			}
		}
		
		pickSpace(0, 0);

		System.out.println(maxSafe);
	}
	
	/** 벽을 세울 공간 구하는 메소드 : 조합 이용 */
	public static void pickSpace(int cnt, int start) {
		if(cnt == 3) {
			// 벽을 세운 곳에는 벽을 세웠다고 표시
			isSpread = new boolean[N][M];
			for(int i = 0; i < 3; i++) {
				isSpread[wall[i][0]][wall[i][1]] = true;
			}
			// 벽을 세우고 바이러스가 퍼지게 하기
			for(int i = 0; i < originalVirus.size(); i++) {
				int x = originalVirus.get(i)[0];
				int y = originalVirus.get(i)[1];
				
				spreadVirus(x, y);
			}
			// 안전공간 계산하기
			calDistance();
			return;
		}
		
		// 벽 3개 구하기
		for(int i = start; i < virusCnt; i++) {
			wall[cnt][0] = virus.get(i)[0];
			wall[cnt][1] = virus.get(i)[1];
			pickSpace(cnt + 1, i + 1);
		}
	}
	
	/** 바이러스 퍼지게 하는 메소드 */
	public static void spreadVirus(int dx, int dy) {
		for(int d = 0; d < 4; d++) {
			int nx = dx + directions[d][0];
			int ny = dy + directions[d][1];
			
			if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
				if(map[nx][ny] == 1) {
					continue;
				}
				if(isSpread[nx][ny]) {
					continue;
				}
				isSpread[nx][ny] = true;
				spreadVirus(nx, ny);
			} else {
				continue;
			}
		}
	}
	
	/** 안전공간 구하는 메소드 */
	public static void calDistance() {
		int count = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				// map의 값이 0이고 퍼지지 않은 공간일 때 값 더하기
				if(map[i][j] == 0 && !isSpread[i][j]) {
					count++;
				}
			}
		}
		maxSafe = Math.max(count, maxSafe);
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_2115_벌꿀채취 {
	
	static int N;
	static int M;
	static int C;
	static int[][] beehives;
	static boolean[][] visited;
	static boolean[][] rePickVisited;
	static int maxProfit;
	static Deque<Integer>[] rePick;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			// N : 벌통들의 크기, M : 선택할 수 있는 벌통의 개수, C : 꿀을 채취할 수 있는 최대 양
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N][N];
			rePick = new LinkedList[2];
			rePickVisited = new boolean[2][M];
			maxProfit = 0;
			
			rePick[0] = new LinkedList<>();
			rePick[1] = new LinkedList<>();
			
			// 벌통
			beehives = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					beehives[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			pickBeehive(0, 0);
			
			sb.append("#").append(t).append(" ");
			sb.append(maxProfit).append("\n");
		}
		System.out.println(sb);
	}
	
	/**
	 * 벌통들 고르는 메소드 : 슬라이딩 윈도우 사용
	 */
	public static void pickBeehive(int cnt, int start) {
		for(int i = 0; i < N-1; i++) {
			rePick[0] = new LinkedList<>();
			// step1. 초기값 저장
			for(int j = 0; j < M; j++) {
				rePick[0].addLast(beehives[i][j]);
			}
			
			// 다른 하나의 벌통을 반복문 돌려가면서 진행
			for(int k = i+1; k < N; k++) {
				rePick[1] = new LinkedList<>();
				for(int m = 0; m < M; m++) {
					rePick[1].addLast(beehives[k][m]);					
				}
				pickSum(0,0);
				for(int m = M; m < N; m++) {
					rePick[1].addLast(beehives[k][m]);
					rePick[1].poll();
					
					pickSum(0,0);
				}
			}
			
			// step2. 초기값 다 저장한 후 
			for(int j = M; j < N; j++) {
				rePick[0].addLast(beehives[i][j]);
				rePick[0].poll();
				// 다른 하나의 벌통을 반복문 돌려가면서 진행
				for(int k = i+1; k < N; k++) {
					rePick[1] = new LinkedList<>();
					for(int m = 0; m < M; m++) {
						rePick[1].addLast(beehives[k][m]);					
					}
					pickSum(0,0);
					for(int m = M; m < N; m++) {
						rePick[1].addLast(beehives[k][m]);
						rePick[1].poll();
						
						pickSum(0,0);
					}
				}
			}
		}
	}
	
	/**
	 * 고른 벌통들에서 또 조합으로 합 구하기 위한 메소드
	 */
	public static void pickSum(int cnt, int recur) {
		if(cnt >= M) {
			if(recur == 0) {
				pickSum(0, recur + 1);
			} else if(recur == 1) {
				sum();
				return;
			}
			return;
		}
		
		rePickVisited[recur][cnt] = true;
		pickSum(cnt + 1, recur);
		
		rePickVisited[recur][cnt] = false;
		pickSum(cnt + 1, recur);
	}
	
	/**
	 * 고른 조합에서 최적의 답을 내기 위한 메소드
	 */
	public static void sum() {
		// 두개의 합이 C를 넘기는 지 확인하기 위한 변수
		int total1 = 0;
		int total2 = 0;
		
		// 최대수익을 판단하기 위한 변수
		int sum1 = 0;
		int sum2 = 0;
		
		int res = 0;
		for(int i = 0; i < M; i++) {
			// 고른 벌통일 때
			if(rePickVisited[0][i]) {
				// 값을 빼와서 합과 제곱값을 구한다.
				res = rePick[0].poll();
				total1 += res;
				sum1 += Math.pow(res, 2);
				// 뺀 값을 다시 집어 넣음
				rePick[0].add(res);
			} else {
				res = rePick[0].poll();
				rePick[0].add(res);
			}
		}
		
		for(int i = 0; i < M; i++) {
			if(rePickVisited[1][i]) {
				res = rePick[1].poll();
				total2 += res;
				sum2 += Math.pow(res, 2);
				rePick[1].add(res);
			} else {
				res = rePick[1].poll();
				rePick[1].add(res);
			}
		}
		
		// 둘의 합이 C를 넘기지 않을때
		if(total1 <= C && total2 <= C) {
			maxProfit = Math.max(maxProfit, sum1 + sum2);
		}
	}
	
}

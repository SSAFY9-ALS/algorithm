import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 드래곤커브 / 골드4 / 2시간
 * @author 소수현
 * https://www.acmicpc.net/problem/15685
 */

public class BJ_15685_드래곤커브 {
	
	static final int[][] directions = {{0,1} , {1,1}, {1,0}};
	static boolean[][] map = new boolean[101][101];
	static LinkedList<int[]> dragons;
	static int count = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 드래곤 커브의 개수
		int N = Integer.parseInt(br.readLine());
		
		
		// 드래곤 커브의 정보 x , y : 드래곤 커브의 시작 점, d : 시작 방향, g : 세대
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			dragons = new LinkedList<>();
			// {1,2,3}; 1,2 : 좌표값, 3 : 현재 방향

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			dragons.add(new int[] {x, y, d});
			map[x][y] = true;
			if(d == 0) {
				dragons.add(new int[] {x+1, y, d});
				if(!map[x+1][y]) {
					map[x+1][y] = true;
				}
			} else if(d==1) {
				dragons.add(new int[] {x, y-1, d});
				if(!map[x][y-1]) {
					map[x][y-1] = true;
				}
			} else if(d==2) {
				dragons.add(new int[] {x-1, y, d});
				if(!map[x-1][y]) {
					map[x-1][y] = true;
				}
			} else {
				dragons.add(new int[] {x, y+1, d});
				if(!map[x][y+1]) {
					map[x][y+1] = true;					
				}
			}
			dragonCurve(g);
		}
		
		for(int i = 0;i < 101; i++) {
			for(int j = 0; j < 101; j++) {
				if(map[i][j]) {
					int num = 0;
					
					// 사각형의 왼쪽 위의 점에서 1. 오른쪽 위 2. 오른쪽 아래 3. 왼쪽 아래 이 세점이 존재하는지 파악해 사각형이 있는지 판단한다.
					for(int k = 0; k < 3; k++) {
						int dx = i + directions[k][0];
						int dy = j + directions[k][1];
						
						if(dx >= 0 && dx <= 100 && dy >= 0 && dy <= 100) {
							if(map[dx][dy]) {
								num++;
							}
						}
					}
					if(num == 3) {
						count++;
					}
				}
			}
		}
		System.out.println(count);
	}
	
	/** 
	 * 드래곤 커브 그리는 메소드
	 * @param g : 몇 세대인지 인자로 넘겨줌
	 */
	public static void dragonCurve(int g) {
		for(int generation = 0; generation < g; generation++) {
			int size = dragons.size() - 1;
			// 마지막 좌표는 고정좌표이니까 고정좌표 이전의 좌표들의 개수만큼 돌면서 위치 조정을 해준다.
			for(int i = size; i > 0; i--) {
				int index = dragons.size() - 1;
				int x = dragons.get(index)[0];
				int y = dragons.get(index)[1];
				
				// 현재 방향이 오른쪽 화살표이면 y값 감소, 위쪽 화살표로 바뀜
				if(dragons.get(i)[2] == 0) {
					if(y-1 >= 0) {
						dragons.add(new int[] {x, y-1, 1});
						if(!map[x][y-1]) {
							map[x][y-1] = true;
						}
					}
				}
				// 현재 방향이 위쪽 화살표이면 x값 감소, 왼쪽 화살표로 바뀜
				else if(dragons.get(i)[2] == 1) {
					if(x-1 >= 0) {
						dragons.add(new int[] {x-1, y, 2});
						if(!map[x-1][y]) {
							map[x-1][y] = true;
						}
					}
				}
				// 현재 방향이 왼쪽 화살표이면 y값 증가, 아래쪽 화살표로 바뀜
				else if(dragons.get(i)[2] == 2) {
					if(y+1 < 101) {
						dragons.add(new int[] {x, y+1, 3});
						if(!map[x][y+1]) {
							map[x][y+1] = true;
						}
					}
				}
				// 현재 방향이 아래쪽 화살표이면 x값 증가, 오른쪽 화살표로 바뀜
				else {
					if(x+1 < 101) {
						dragons.add(new int[] {x+1, y, 0});
						if(!map[x+1][y]) {
							map[x+1][y] = true;
						}
					}					
				}
			}
		}
	}

}

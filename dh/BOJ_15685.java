import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 
 * 드래곤 커브 / 골드4 / 100분
 * https://www.acmicpc.net/problem/15685
 *
 */
class Dragon {
	public int x;
	public int y;

	public Dragon(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int n;
	static boolean[][] matrix;
	static List<Dragon> dragonlist;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		matrix = new boolean[101][101];
		// 점의 존재를 저장하는 2차원 배열
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			dragonlist = new ArrayList<>(); // 리스트 초기화
			dragon(x, y, d, g); // 드래곤 커브 만드는 메서드
		}
		find_Req(); // 사각형 구하는 메서드
		System.out.println(answer);
	}

	static void dragon(int x, int y, int d, int g) {
		dragonlist.add(new Dragon(x, y));
		// 시작점 리스트에 추가
		matrix[y][x] = true;
		// 시작점 존재 표시
		dragonlist.add(new Dragon(x + dx[d], y + dy[d]));
		// 시작점을 주어진 방향으로 옮긴 0세대 드래곤 커브 점 리스트에 추가
		matrix[y + dy[d]][x + dx[d]] = true;
		// 0세대 드래곤 커브 존재 표시
		for (int i = 0; i < g; i++) {
			// 주어진 세대만큼 반복
			int size = dragonlist.size(); // 리스트 크기
			int standx = dragonlist.get(size - 1).x; // 리스트 마지막 객체의 x좌표
			int standy = dragonlist.get(size - 1).y; // 리스트 마지막 객체의 y좌표
			for (int j = size - 2; j >= 0; j--) {
				Dragon tmp = curv(standx, standy, dragonlist.get(j).x, dragonlist.get(j).y);
				// curve한 위치를 저장하는 객첵를 tmp에 저장
				if (tmp.x >= 0 && tmp.x <= 100 && tmp.y >= 0 && tmp.y <= 100) {
					dragonlist.add(tmp);
					matrix[tmp.y][tmp.x] = true;
					// 범위 내에 있으면 리스트 추가 후 존재 표시
				}
			}
		}
	}

	static Dragon curv(int standx, int standy, int x, int y) {
		// standx, standy를 기준으로 x, y를 시계방향으로 90도 회전
		int curveX = standx + standy - y;
		int curveY = standy + x - standx;
		return new Dragon(curveX, curveY);
	}

	static void find_Req() {
		// 사각형 갯수 구할 때 오른쪽 밑을 기준으로 양옆 대각선의 좌표가 true면 1개 증가
		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				if (matrix[i][j] && matrix[i - 1][j] && matrix[i][j - 1] && matrix[i - 1][j - 1]) {
					answer++;
				}
			}
		}
	}
}
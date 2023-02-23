package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 꽃길 / 실버2 / 1시간
 * https://www.acmicpc.net/problem/14620
 */

public class BOJ_14620 {
	static int n, min = Integer.MAX_VALUE;
	static int[][] cost;
	static boolean[][] visited;
	
	// 꽃잎 존재 여부를 바꾸는 메서드
	static int changeVisited(int x, int y, int num) {
		if(num == -1) {
			visited[x][y] = true;
			int sum = cost[x][y]; // 연산을 한번에 하기 위해 꽃잎을 추가하는 경우는 sum을 통해 비용값도 구함
			for(int i = -1; i <= 1; i += 2) {
				visited[x+i][y] = true;
				visited[x][y+i] = true;
				sum += cost[x+i][y] + cost[x][y+i];
			}
			return sum; // 구한 sum 값 반환
		}
		else {
			visited[x][y] = false;
			for(int i = -1; i <= 1; i += 2) {
				visited[x+i][y] = false;
				visited[x][y+i] = false;
			}
			return 0; // 꽃잎을 제거하는 경우는 sum할 필요 없음 -> 0 반환
		}
	}
	
	// 꽃에 의해 차지된 영역인지 판별하는 메서드
	static boolean isVisited(int x, int y) {
		// 중심, 상하좌우가 모두 false라면 -> 꽃잎이 존재하지 않는 것
		if(!visited[x][y] && !visited[x-1][y] && !visited[x+1][y] && !visited[x][y-1] && !visited[x][y+1])
			return true; // true 반환
		return false; // 위의 경우가 아니라면 false 반환
	}
	
	// 최소 비용을 찾는 메서드: 완전 탐색 구현
	static void findMinCost(int r, int sum) {
		if(r == 0) { // 3개만큼 뽑았으면
			if(min > sum) // min과 비교를 통해 값 갱신
				min = sum;
			return;
		}
		int add;
		for(int i = 1; i < n - 1; i++) {
			for(int j = 1; j < n - 1; j++) {
				if(isVisited(i, j)) { // 현재 좌표를 중심으로 하는 꽃잎 만들기가 가능하다면
					add = changeVisited(i, j, -1); // 방문 여부 true로 변경
					findMinCost(r-1, sum + add); // 다시 함수 호출
					changeVisited(i, j, 1); // 다른 좌표들 탐색을 위해 다시 방문 여부를 false로 변경
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine()); // n 입력
		cost = new int[n][n]; // 비용을 저장하는 배열
		visited = new boolean[n][n]; // 꽃에 의해 차지되는 영역인지 판단하는 배열
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++)
				cost[i][j] = Integer.parseInt(st.nextToken()); // 비용 입력
		}
		findMinCost(3, 0); // 최소 비용 찾는 함수 호출
		System.out.println(min); // 결과 출력
	}
}

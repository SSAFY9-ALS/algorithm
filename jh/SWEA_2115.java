package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 벌꿀 채취 / 모의 SW 역량테스트 / 2시간
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu
 */

public class SWEA_2115 {
	static int c;
	static int max; // 각 열마다 최댓값을 받을 변수
	
	static void findCombination(int[] array, int r, int idx, int count, int sum, int money) {
		if(r == 0) { // r개만큼 뽑았을 때
			if(sum <= c && max < money) // c이하이고 계산한 수익이 현재 최댓값보다 크면
				max = money; // 최댓값 갱신
		}
		else { // 아직 덜 뽑았을 때
			for(int i = idx; i < array.length; i++) // 현재 인덱스 이후를 탐색해서
				findCombination(array, r-1, i+1, count+1, sum + array[i], money + array[i] * array[i]); // 다시 재귀 함수 호출
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
		int n, m, result;
		int[] array;
		int[][] honey;
		PriorityQueue<Integer> money; // 최댓값 2개를 뽑기 위해 우선순위 큐 생성
		for(int tc = 1; tc <= t; tc++) { // 테스크 테이스만큼 반복
			StringTokenizer st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken()); // n 입력
			m = Integer.parseInt(st.nextToken()); // m 입력
			c = Integer.parseInt(st.nextToken()); // c 입력
			honey = new int[n][n];
			money = new PriorityQueue<Integer>((o1, o2) -> { // 우선순위 큐의 정렬 조건을 오름차순으로 변경
				return o2 - o1;
			});
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < n; j++)
					honey[i][j] = Integer.parseInt(st.nextToken()); // 벌꿀 입력
			}
			for(int i = 0; i < n; i++) { // 각 행마다 최댓값을 구하기 위한 반복문 시작
				max = 0; // 최댓값 초기화
				array = new int[m]; // m개만큼 뽑은 숫자를 담을 배열 생성
				for(int j = 0; j <= n - m; j++) { // 한 칸씩 오른쪽으로 움직일 때, 가능한 시작 위치의 최대 인덱스 수만큼 반복 
					for(int l = 0; l < m; l++) { // 시작 위치에서 m개를 뽑기 위한 반복문
						array[l] = honey[i][j+l]; // 배열에 넣어줌
					}
					// m개를 뽑은 다음에, 그 안에서 수익이 최댓값이 되기 위해서는 또 1 ~ m개의 벌통을 뽑는 경우를 생각해야 함
					for(int x = 1; x <= m; x++) {  // 1 ~ m개를 뽑는 반복문 시작
						findCombination(array, x, 0, 0, 0, 0); // 재귀 함수 호출
					}
				}
				money.add(max); // 각 행마다의 최댓값을 우선순위 큐에 add
			}
			result = money.poll(); // 가장 큰 수익 뽑아서 result에 저장
			result += money.poll(); // 두 번째로 큰 수익 뽑아서 result에 add
			System.out.printf("#%d %d\n", tc, result); // 결과 출력
		}
		
	}
}
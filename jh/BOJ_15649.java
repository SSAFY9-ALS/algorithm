package algorithm;

import java.util.Scanner;

public class BOJ_15649 {
	static void search(boolean[] visited, int now, int count, String sen) {
		if(now == count) { // m번 숫자를 더해왔으면 그만
			System.out.println(sen);
		}
		for(int i = 1; i < visited.length; i++) {
			if(!visited[i]) { // 현재 숫자가 방문하지 않은 숫자라면
				visited[i] = true; // 배열값을 true로 변경하고
				search(visited, now+1, count, sen + i + " "); // 갱신한 visited, now를 가지고 함수 호출 
				visited[i] = false; // 방문값 다시 false로 변경
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		boolean[] visited = new boolean[n+1]; // 방문한 숫자 판별하기 위해 boolean 배열 생성
		search(visited, 0, m, ""); // 완전 탐색 시작
	}
}

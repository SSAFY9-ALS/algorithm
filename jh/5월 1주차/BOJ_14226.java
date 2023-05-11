package may;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * 이모티콘 / 골드4 / 1시간 20분 / 5월 9일
 * https://www.acmicpc.net/problem/14226
 */

public class BOJ_14226 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();
		
		boolean[][] visited = new boolean[2001][1001]; // 방문 여부 배열 => 이모티콘의 길이와 클립보드에 저장된 이모티콘의 길이 
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {1, 0, 0});
		visited[1][0] = true;

		int result = 0;
		int[] temp;
		while(!queue.isEmpty()) { // 큐가 비어 있지 않을 때
			temp = queue.poll();
			
			// 종료 조건 확인
			if(temp[0] + temp[2] == s) {
				result = temp[1] + 1;
				break;
			}
			else if(temp[0] - 1 == s) {
				result = temp[1] + 1;
				break;
			}
			else if(2 * temp[0] == s) {
				result = temp[1] + 2;
				break;
			}
			
			// 이모티콘 중 하나를 삭제하는 경우
			if(temp[0] - 1 >= 0 && !visited[temp[0]-1][temp[2]]) {
				queue.offer(new int[] {temp[0] - 1, temp[1] + 1, temp[2]});
				visited[temp[0]-1][temp[2]] = true;
			}
			// 클립보드의 이모티콘을 붙여넣기 하는 경우
			if(temp[0] + temp[2] <= 2000 && !visited[temp[0] + temp[2]][temp[2]]) {
				queue.offer(new int[] {temp[0] + temp[2], temp[1] + 1, temp[2]});
				visited[temp[0] + temp[2]][temp[2]] = true;
			}
			// 화면의 이모티콘을 복사하는 경우
			if(temp[0] <= 1000 && !visited[temp[0]][temp[0]])
				queue.offer(new int[] {temp[0], temp[1] + 1, temp[0]});
		}
		
		System.out.println(result); // 결과 출력
	}
}

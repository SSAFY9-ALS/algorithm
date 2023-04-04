import java.io.*;
import java.util.*;
/**
 * 
 * 회전초밥 / 실버1 / 70분
 * https://www.acmicpc.net/problem/2531
 * 
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] belt = new int[n];
		for(int i = 0; i < n; i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}
		
		int answer = 0;
		int start = 0;
		int end = k;
		
		int[] isSelected = new int[d + 1];
		int count = 0;
		for(int i = 0; i < end; i++) {
			isSelected[belt[i]]++;
			if(isSelected[belt[i]] == 1) {
				count++;
			}
			// 0부터 k개를 연속해서 먹을 때 나오는 초밥의 종류 계산
		}
		if(isSelected[c] == 0) {
			count++;
			// 쿠폰 번호의 종류가 들어가지 않았으면 1증가
		}
		answer = Math.max(answer, count);
		
		for(int i = 0; i < n; i++) {
			isSelected[belt[start]]--;
			// 맨 뒤의 접시 제외
			if(isSelected[belt[start]] == 0) {
				count--;
				// 맨 뒤의 접시를 제외 했을 때 유일한 접시였다면 종류 1 감소
				if(belt[start] == c)
					// 쿠폰 종류였다면 복구
					count++;
			}
			
			isSelected[belt[end]]++;
			// 맨 앞의 접시 추가
			if(isSelected[belt[end]] == 1) {
				count++;
				// 맨 앞의 접시를 추가 했을 때 유일한 접시면 종류 1 증가
				if(belt[end] == c)
					// 쿠폰 종류였다면 복구
					count--;
			}
			
			start++;
			end = (end+1) % n;
			// 1칸씩 증가
			answer = Math.max(answer, count);
			// 최소값 저장
		}
		System.out.println(answer);
	}
	
}
import java.io.*;
import java.util.*;
/**
 * 
 * 팰린드롬? / 골드 4 / 120분
 * https://www.acmicpc.net/problem/10942
 * 
 */
public class Main {
	static int n;
	static int[] num;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		num = new int[n+2];
		dp = new int[n+2][n+2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		// 입력
		
		num[0] = -1;
		num[n] = -1;
		// 비교 불가능한 숫자로 초기화
		for(int i = 1; i <= n; i++) {
			dp[i][i] = 1;
			// 한자리 수는 팬린드롬
		}
		for(int i = 1; i < n; i++) {
			pan(i, i);
			// 홀수개일 때 탐색
			pan(i, i+1);
			// 짝수개일 때 탐색
		}
		
		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int temp = dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
			sb.append(temp).append("\n");
		}
		System.out.println(sb);
		// 시간 초과를 해결하기 위해 StringBuilder 사용
	
	}
	static void pan(int i, int j) {
		while(true) {
			if(i < 1 && j > n) {
				// 범위를 넘어가면 리턴
				return;
			}
			if(num[i] != num[j]) {
				// 팰린드롬이 아니면 리턴
				return;
			}
			dp[i][j] = 1;
			// 1로 설정
			i--;
			j++;
			// 탐색 범위 앞뒤로 한칸씩 증가
		}
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 퇴사 / 실버 3 / 30분
 * https://www.acmicpc.net/problem/14501
 */
public class BJ_14501_퇴사 {
	
	static int N;
	static int[][] consulting;
	static int maxRevenue = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// N일동안 상담
		N = Integer.parseInt(br.readLine());
		
		// 상담 일자와 받을 수 있는 금액
		consulting = new int[N+1][2];
		
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			// 0번째 인덱스 : 상담을 완료하는데 걸리는 기간
			// 1번째 인덱스 : 상담을 했을 때 받을 수 있는 금액
			consulting[n][0] = Integer.parseInt(st.nextToken());
			consulting[n][1] = Integer.parseInt(st.nextToken());
		}
		
		recur(1, 0);
		
		System.out.println(maxRevenue);
	}
	
	static void recur(int day, int money) {
		
		for (int i = day; i <= N; i++) {
			// 상담 날짜가 기간을 넘어가지 않는 경우 
			if(i + consulting[i][0] <= N) {
				recur(i + consulting[i][0], money + consulting[i][1]);
			}
			// 마지막 날짜가 상담 기간이 하루일 때 그 날짜까지 더 해줌
			else if(i + consulting[i][0] == N+1) {
				maxRevenue = Math.max(maxRevenue, money + consulting[i][1]);
				continue;
			}
			// 상담 날짜가 기간을 넘어가는 경우 최댓값 저장
			else {
				maxRevenue = Math.max(maxRevenue, money);
				continue;
			}
		}
	}
}

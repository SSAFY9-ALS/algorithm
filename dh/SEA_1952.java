import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * 수영장 / _ / 100분
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq&&
 *
 */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			int[] price = new int[4];
			// 1일 1달 3달 1년권 가격
			int[] month = new int[12];
			// 각 달의 수영장 이용한 날의 수
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			int[] min = new int[12];
			// 각 달 만을 봤을 때 최소값 저장 배열
			int sum = 0;
			for(int i = 0; i < 12; i++) {
				month[i] = Integer.parseInt(st.nextToken());
				min[i] = month[i] * price[0];
				// 1일권만을 사용했을 때 최소값
				if(month[i] > 0) {
					min[i] = Math.min(min[i], price[1]);
					sum += min[i];
					// 각 달에 이용했으면 1일권만을 사용했을때와 1달권의 가격을 비교하여 각 달의 최소값 저장
					// 각 달의 최솟값을 sum에 더하기
				}
			}
			int[] three_month_min = new int[10];
			// 세 달 기준의 최소값 저장 배열
			for(int i = 0; i < 10; i++) {
				if(min[i] + min[i+1] + min[i+2] > 0) {
					three_month_min[i] = Math.min(min[i] + min[i+1] + min[i+2], price[2]);
					// 3달 중 한달이라도 이용했으면 3달의 최솟값과 3달권의 가격의 최솟값 저장
				}
			}
			
			int answer = price[3];
			// 1년권으로 초기화
			for(int i = 0; i < 10; i++) {
				answer = Math.min(answer, sum - min[i] - min[i+1] - min[i+2] + three_month_min[i]);
				// 12개월 중 한번만 3달권을  사용했을 때의 경우의 수에 대한 최소값 저장
			}
			for(int i = 0; i < 7; i++) {
				for(int j = i + 3; j < 10; j++) {
					answer = Math.min(answer, sum - min[i] - min[i + 1] - min[i+2] - min[j] - min[j+1] - min[j+2]
							+three_month_min[i] + three_month_min[j]);
					// 12개월 중 두 번만 3달권을  사용했을 때의 경우의 수에 대한 최소값 저장
				}
			}
			for(int i = 0; i < 4; i++) {
				for(int j = i+3; j < 7; j++) {
					for(int k = j+3; k < 10; k++) {
						answer = Math.min(answer, sum - min[i] - min[i + 1] - min[i+2] - min[j] - min[j+1] - min[j+2]
								 - min[k] - min[k+1] - min[k+2]
								+three_month_min[i] + three_month_min[j] + three_month_min[k]);
						// 12개월 중 세 번만 3달권을  사용했을 때의 경우의 수에 대한 최소값 저장
					}
				}
			}
			answer = Math.min(answer, three_month_min[0] + three_month_min[3] + three_month_min[6] + three_month_min[9]);
			// 모두 3달권을  사용했을 때의 경우의 수에 대한 최소값 저장
			System.out.println("#" + test_case + " " + answer);
		}
	}
}
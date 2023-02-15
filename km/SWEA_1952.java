/**
 * 수영장 / / 70분
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq&
 */
package swea;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class SWEA_1952 {
	
	static int[] price;
	static int[] month;
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = parseInt(br.readLine());
		
		for(int t=1;t<=tc;t++) {
			String[] tmp = br.readLine().split(" ");
			
			// 이용권 가격  1일 1달 3달 1년
			price = new int[4];
			for(int i=0;i<4;i++) {
				price[i] = parseInt(tmp[i]);
			}
			
			// 1년치 이용 계획
			tmp = br.readLine().split(" ");
			month = new int[12];
			for(int i=0;i<12;i++) {
				month[i] = parseInt(tmp[i]);
			}
			min = Integer.MAX_VALUE;
			
			dfs(0,0);
			min = Math.min(min, price[3]);
			
			System.out.println("#" + t + " " +min);
			
		}
	}
	
	private static void dfs(int mon, int sum) { // 모든 경우 탐색
		if(mon>=12) { // 범위 초과시 최소값 계산
			min = Math.min(min, sum);
			return;
		}
		
		if(month[mon]==0) { // 이용권 안사는 달도 존재
			dfs(mon+1, sum);
		}
		else {
			dfs(mon+1, sum+(month[mon]*price[0])); // 1일 이용권 구매
			dfs(mon+1, sum+price[1]); // 1달 이용권 구매
			dfs(mon+3, sum+price[2]); // 3달 이용권 구매
		}
	}
}


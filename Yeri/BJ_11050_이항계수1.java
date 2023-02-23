package yeri.algorithm0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이항계수1 / 브론즈1 / 10분
 * https://www.acmicpc.net/problem/11050
 */
public class BJ_11050_이항계수1 {

	static int result = 0;	//경우의 수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// n, k 입력받음
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		//1개일 때
		if(n==1) {
			result=1;
		}else {
			result = combi(n,k);	//n이 2 이상일때
		}
		System.out.println(result);
	}
	private static int combi(int n, int k) {
		if(k==0 || (n==k)) {		//nC0 이거나 nCn이면 1
			return 1;
		}
		return combi(n-1,k-1)+combi(n-1,k);	//조합의 성질?
	}

}

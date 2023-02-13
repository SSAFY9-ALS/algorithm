package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 수 이어 쓰기 2 / 골드 5 / 80
 * @author 민정인
 * https://www.acmicpc.net/problem/1790
 */

public class BOJ_1790 {
	static long k;			// 현재 k값을 수정해주기 위함
	static long val;		// 자리수
	static void inRange(long st, long tmp) {	// 자리수를 파악하여 수의 범위를 파악하기 위한 함수. 자릿수가 하나 늘어날 떄마다 하나의 수가 나열되는 길이가 늘어나기 때문에
		if(k < 9 * Math.pow(10, st) * tmp) {
			val = st + 1;
			return;
		}
		k -= 9 * Math.pow(10, st) * tmp;
		inRange(st + 1, tmp + 1);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Long.parseLong(st.nextToken());
		long length = 0;
		long mul = 1;
		while(n > 0) {			// n까지의 수 나열시 길이 측정
			length += Math.min(n, 9 * Math.pow(10, mul - 1)) * mul;
			n -= 9 * Math.pow(10, mul - 1);
			mul++;
		}
		k = Long.parseLong(st.nextToken());
		long result = -1;
		if(length < k) {		// k값이 n까지 나열한 수의 길이보다 길때는 -1 출력
			System.out.println(result);
		} else {			
			inRange(0, 1);
			if(val == 0) {			
				System.out.println(k);		// k값이 10보다 작을 때에는 각 수당 한자리씩만 차지하므로 별도 출력
			} else {
				long tmp = k % val;			// k값을 자릿수만큼 나눈 나머지(현재 수의 몇번째 자릿수가 나오는지 확인하기 위함)
				long tmp2 = k / val;		// k값을 자릿수만큼 나눈 몫(현재 수가 10, 100, 1000... 부터 몇이나 더해야 하는 확인하기 위함)
				if(tmp == 0) {				// 나머지가 0일 때에는 tmp2 - 1번째 수의 일의 자리 수이므로 tmp와 tmp2의 값을 변경
					tmp = val;
					tmp2--;
				}
				long ans = (long) Math.pow(10, val - 1) + tmp2;		// 해당되는 값 구하기(val은 자릿수이므로 10의 val - 1승이 되어야 함)
				for(long i = 0; i < val - tmp; i++) {				// 자릿수 확인을 위해 반복적으로 10으로 나눔
					ans /= 10;
				}
				result = ans % 10;									// 나눠진 수에서 10으로 나눈 나머지가 결과값
				System.out.println(result);							// 결과 출력
			}
		}
	}
}

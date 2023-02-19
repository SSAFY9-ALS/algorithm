/**
 * 잃어버린 괄호 / 실버 2 / 30분
 * https://www.acmicpc.net/problem/1541
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_1541 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] formula  = br.readLine().split("-"); // -를 기준으로 분리하여  뺴는 부분을 만듦
		int sum = Integer.MAX_VALUE; //  왜 이걸로 해야함?
		
		
		for(int i=0;i<formula.length;i++) {
			String[] sub = formula[i].split("\\+"); // 나눠진 식에서 +로 나누어 ()안 식 계산
			
			int tmp = 0;
			for(int j=0;j<sub.length;j++) {
				tmp += parseInt(sub[j]);
			}
			
			if(sum==Integer.MAX_VALUE) { // 첫번째 값이 경우는 +를 해야함
				sum=tmp;
			}
			else { // 나머지 경우 다 빼줌
				sum -= tmp;
			}
		}
		System.out.println(sum);
		
	}

}

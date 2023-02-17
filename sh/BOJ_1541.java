package Divide_and_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 잃어버린 괄호 / 실버 2 / 15분
 * @author 소수현
 * https://www.acmicpc.net/problem/1541
 */

public class BJ_1541_잃어버린괄호 {
	
	public static void main(String[] args) throws IOException {
		// 양수, +, -, (, )
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "-");
		LinkedList<Integer> sumFirst = new LinkedList<>();

		while(st.hasMoreElements()) {
			int sum = 0;
			String s = st.nextToken();
			StringTokenizer st2 = new StringTokenizer(s, "+");
			while(st2.hasMoreElements()) {
				sum += Integer.parseInt(st2.nextToken());
			}
			sumFirst.add(sum);
		}
		
		int total = sumFirst.get(0);
		for(int i = 1; i < sumFirst.size(); i++) {
			total -= sumFirst.get(i);
		}
		
		System.out.println(total);
	}
	
}

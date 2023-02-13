package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 숫자 카드 2 / 실버4 / 15분
 * https://www.acmicpc.net/problem/10816
 */

public class BOJ_10816 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine()); // n 입력
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); // count를 저장할 map 생성
		int num;
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			num = Integer.parseInt(st.nextToken()); // num 입력
			if(map.containsKey(num)) // map에 num이 key로 존재하면
				map.put(num, map.get(num) + 1); // 기존 count를 1증가시킴
			else // 존재하지 않다면
				map.put(num, 1); // 새로운 키 생성
		}
		int m = Integer.parseInt(in.readLine()); // m 입력
		st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder(); // 결과 출력할 StringBuilder
		for(int i = 0; i < m; i++) {
			num = Integer.parseInt(st.nextToken()); // count 구할 숫자 입력
			if(map.containsKey(num)) // key로 존재한다면 그 숫자 카드를 가지고 있다는 뜻
				sb.append(map.get(num) + " "); // map의 value 출력
			else // 아니라면 숫자 카드를 가지지 않는다는 뜻
				sb.append(0 + " "); // 0 출력
		}
		System.out.println(sb);
	}
}

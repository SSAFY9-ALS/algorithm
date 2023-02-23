package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

/**
 * 가희와 키워드 / 실버2 / 10분
 * https://www.acmicpc.net/problem/22233
 */

public class BOJ_22233 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // n 입력
		int m = Integer.parseInt(st.nextToken()); // m 입력
		LinkedHashMap<String, Boolean> map  = new LinkedHashMap<String, Boolean>(); // 메모장 키워드를 저장하기 위한 map 생성
		StringBuilder result = new StringBuilder(); // 결과를 출력할 StringBuilder 생성
		int count = 0; // 남아 있는 메모장 키워드 개수 초기화
		String keyword;
		for(int i = 0; i < n; i++) {
			keyword = in.readLine(); // 키워드 입력
			if(!map.containsKey(keyword)) { // map에 키워드가 있지 않다면
				map.put(keyword, true); // map에 추가
				count++; // count 1 증가
			}
		}
		String[] keywords;
		for(int i = 0; i < m; i++) {
			keywords = in.readLine().split(","); // 쉼표를 기준으로 블로그에 포함된 키워드 받음
			for(String key: keywords) {
				if(map.containsKey(key)) { // 각 키워드가 메모장에 있다면
					map.remove(key); // 키워드 제거
					count--; // count 1 증가
				}
			}
			result.append(count + "\n"); // sb에 추가
		}
		System.out.println(result); // 결과 출력
	}
}

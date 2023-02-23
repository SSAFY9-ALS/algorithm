package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 가희와 키워드 / 실버 2 / 22분
 * @author 민정인
 * https://www.acmicpc.net/problem/22233
 */

public class BOJ_22233 {
	static int n, m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		Map<String, Integer> map = new HashMap<>();	// 키워드 정보 저장을 위한 맵
		List<Integer> sizeL = new ArrayList<>();	// 남은 키워드 수 저장을 위한 리스트
		for(int i = 0; i < n; i++) {
			map.put(br.readLine(), 0);				// 키워드 입력
		}
		for(int i = 0; i < m; i++) {
			String s = br.readLine();
			String[] str = s.split(",");			// ,로 나누기
			for(int j = 0; j < str.length; j++) {
				map.remove(str[j]);					// 현재 있는 키워드 중 해당 키 삭제
			}
			sizeL.add(map.size());					// 남은 키워드 수 저장
		}
		for(int i = 0; i < sizeL.size(); i++) {
			System.out.println(sizeL.get(i));		// 출력
		}
	}
}

package net.acmicpc;

import java.util.*;
import java.io.*;

/**
 * 전화번호 목록 / 골드 4 / 62분
 * @author 민정인
 * https://www.acmicpc.net/problem/5052
 */

public class BOJ_5052 {
	static class Trie {
		Map<Character, Trie> map = new HashMap<>();	// 트리에 알고리즘을 위한 클래스
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < test; t++) {
			int n = Integer.parseInt(br.readLine());
			ArrayList<String> list = new ArrayList<>();	// 점검을 위해 입력한 전화번호들 별도 저장
			Trie root = new Trie();
			for(int i = 0; i < n; i++) {
				String s = br.readLine();
				list.add(s);
				Trie node = root;
				for(int j = 0; j < s.length(); j++) {
					Trie next = new Trie();				// 다음 노드 선언
					char c = s.charAt(j);				// 현재 문자열의 문자 하나 추가
					if(node.map.get(c) == null) {		// 현재 노드에 해당 문자가 없다면 노드의 맵에 추가
						node.map.put(c, next);
					} else {							// 해당 문자가 있다면 다음 노드는 해당 노드로 선언
						next = node.map.get(c);
					}
					node = next;						// 다음 노드로 이동
				}
			}
			boolean isEmpty = true;
			for(int i = 0; i < list.size(); i++) {
				String s = list.get(i);
				Trie node = root;
				for(int j = 0; j < s.length(); j++) {	// 문자의 경로를 찾아가기
					char c = s.charAt(j);
					Trie next = node.map.get(c);
					node = next;
				}
				if(!node.map.isEmpty()) {				// 해당 문자열이 종료되었음에도 마지막 노드의 맵이 비어있지 않다면
					isEmpty = false;					// 접두어가 다른 문자열이므로 일관성이 없음
					break;
				}
			}
			sb.append(isEmpty == true ? "YES" : "NO").append("\n");
		}
		System.out.println(sb.toString());
	}
}

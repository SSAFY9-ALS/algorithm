package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 개미굴 / 골드 3 / 110분
 * @author 민정인
 * https://www.acmicpc.net/problem/14725
 */

public class BOJ_14725 {
	static class Trie implements Comparable<Trie>{	// Trie 알고리즘 사용을 위한 클래스(정렬을 위한 Comparable 사용)
		ArrayList<Trie> list;
		String name;
		public Trie(String name) {
			this.name = name;
			this.list = new ArrayList<>();
		}
		@Override
		public int compareTo(Trie o) {
			return this.name.compareTo(o.name);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Trie root = new Trie("");	// 초기 Trie 선언
		Trie node;					// 현재 위치를 위한 노드
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int deep = Integer.parseInt(st.nextToken());
			node = root;
			for(int j = 0; j < deep; j++) {
				String child = st.nextToken();
				Trie next = new Trie(child);
				int idx = -1;
				for(int k = 0; k < node.list.size(); k++) {	// 클래스 내의 이름들 중 이미 중복된 경우가 있는지 확인
					if(node.list.get(k).name.equals(child)) {
						idx = k;
						break;
					}
				}
				if(idx == -1) {		// 현재 단계 노드에서 겹치는 부분이 없다면 현재 노드 입력 후 입력한 노드(리스트의 마지막 노드)를 다음으로 진행
					node.list.add(next);
					node = node.list.get(node.list.size() - 1);
				} else {			// 겹친다면 겹친 노드를 다음 위치로 지정
					node = node.list.get(idx);
				}
			}
		}
		getVal(root, -1);
	}
	static void getVal(Trie tr, int idx) {
		Collections.sort(tr.list);		// 노드 이름순 정렬
		if(tr.name != "") {				// 빈 노드(루트노드)가 아닌 경우 이름 출력
			for(int i = 0; i < idx; i++) {	// 계층 출력
				System.out.print("--");
			}
			System.out.println(tr.name);
		}
		for(int i = 0; i < tr.list.size(); i++) {
			getVal(tr.list.get(i), idx + 1);
		}
	}
}

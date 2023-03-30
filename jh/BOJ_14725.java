package march;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 개미굴 / 골드3 / 1시간 15분
 * https://www.acmicpc.net/problem/14725
 */

public class BOJ_14725 {
	// 노드 클래스 정의
	static class Node implements Cloneable, Comparable<Node> {
		String element; // 먹이 정보
		ArrayList<Node> childNodes = new ArrayList<>(); // 자식 노드 집합
		
		// 생성자 정의
		public Node() { }
		public Node(String element) {
			this.element = element;
		}
		
		// clone 메서드 오버라이딩 -> 깊은 복사를 위해 필요
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
		
		// 같은 층에 있을 때 사전 순서 출력을 위해 오버라이딩
		@Override
		public int compareTo(Node o) {
			return this.element.compareTo(o.element);
		}
	}
	
	static Node root, parent, now;
	
	// 개미굴 구조를 만드는 메서드
	static void addElement(String element) throws CloneNotSupportedException {
		boolean flag = true;
		Node node = null;
		for(Node no: parent.childNodes) { // 현재 노드의 부모 노드의 자식 중에
			if(no.element.equals(element)) { // 같은 먹이가 있다면
				node = (Node)no.clone(); // 그 노드의 정보를 가져옴
				flag = false;
			}
		}
		if(flag) { // 같은 먹이가 존재하지 않다면 -> 새로 추가해야 함
			node = new Node(element); // 새로운 노드 생성
			parent.childNodes.add(node); // 부모노드에 추가해 줌
		}
		parent = (Node)node.clone(); // 부모 노드를 현재 노드로 갱신
	}
	
	// 구조를 출력하는 메서드
	static void printFormat(int depth) throws CloneNotSupportedException {
		for(int i = 0; i < depth; i++) // 깊이만큼
			System.out.print("--"); // -- 출력
		if(depth != -1) // root 노드가 아닐 때
			System.out.println(now.element); // 먹이 정보 출력
		Collections.sort(now.childNodes); // 사전 순 출력을 위해 정렬
		for(Node child: now.childNodes) { // 자식 노드를 순회하며
			now = (Node)child.clone();
			printFormat(depth+1); // 다시 함수 호출
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(in.readLine()); // n 입력
		root = new Node(); // 루트 노드 생성
		for(int i = 0; i < n; i++) { // n만큼 반복
			st = new StringTokenizer(in.readLine());
			int k = Integer.parseInt(st.nextToken()); // 먹이 정보 개수 입력
			parent = (Node)root.clone(); // 부모 노드를 root로 설정
			for(int j = 0; j < k; j++) { // k만큼 반복
				String element = st.nextToken();
				addElement(element); // 먹이를 개미굴에 추가해 줌
			}
		}
		now = (Node)root.clone(); // 출력을 위해 root 노드부터
		printFormat(-1); // 출력 함수 호출
	}
}

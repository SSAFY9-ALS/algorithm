package march;

/**
 * 전화번호 목록 / 골드4 / 2시간
 * https://www.acmicpc.net/problem/5052
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ_5052 {
	// Node 클래스 정의
	static class Node implements Cloneable {
		int num; // 번호의 값을 저장하는 변수
		Node parentNode; // 부모 노드
		HashSet<Node> childNodes = new HashSet<>(); // 자식 노드 목록
		
		// 생성자
		public Node() { }
		public Node(int num) {
			this.num = num;
		}
		
		// clone 메서드 오버라이딩 -> 깊은 복사를 위해 필요
		public Object clone() throws CloneNotSupportedException {
	        return super.clone();
	    }
	}
	
	static Node root; // 루트 노드
	
	// 일관성을 검사하는 메서드
	static boolean isValid(String number) throws CloneNotSupportedException {
		Node parent = (Node)root.clone(); // 문자열이 시작될 때, 부모 노드는 루트 노드가 됨
		// 필요한 변수들
		int num;
		Node node = null;
		boolean flag;
		for(int i = 0; i < number.length(); i++) { // 전체 문자열을 이루는 번호들에 대해 반복
			num = Character.getNumericValue(number.charAt(i)); // 값을 가져옴
			flag = true;
			for(Node no: parent.childNodes) { // 현재 부모 노드의 자식 중에 
				if(no.num == -1) // -1이 있다면? -> 그 위치에서 끝나는 전화번호가 존재한다면
					return false; // 일관성이 없는 것이기 때문에 메서드 종료
				else if(no.num == num) { // 현재 번호과 같은 것이 존재한다면
					flag = false;
					node = (Node)no.clone(); // 새로운 노드를 추가하지 않고 그 노드를 가져옴
				}
			}
			if(flag) { // 위의 경우가 아닐 경우 -> 새로운 노드 추가 필요
				node = new Node(num);
				node.parentNode = parent;
				parent.childNodes.add(node);
			}
			parent = (Node)node.clone(); // 부모 노드를 현재 노드로 갱신
		}
		// 문자열이 끝날 때
		node = new Node(-1); // 종료 조건을 확인하기 위한 -1 값을 노드 끝에 달아줌
		node.parentNode = parent;
		if(parent.childNodes.size() > 0) // -1이 입력될 때 그 깊이에 다른 노드가 존재한다면? -> 일관성이 없는 것
			return false; // 메서드 종료
		parent.childNodes.add(node); // 아닐 경우 -1을 넣어줌
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n;
		String number, result;
		int tc = Integer.parseInt(in.readLine()); // tc 입력
		
		for(int t = 0; t < tc; t++) { // tc만큼 반복	
			n = Integer.parseInt(in.readLine()); // n 입력
			result = "YES";
			// 루트 노드 생성
			root = new Node();
			root.parentNode = null;
			
			for(int i = 0; i < n; i++) { // 전화번호 개수만큼 반복
				number = in.readLine(); // 전화번호 입력
				if(!isValid(number)) { // 일관성을 판단하는 함수 호출 -> 결과가 false일 때
					result = "NO"; // 출력값 
				}
				
			}
			System.out.println(result); // 결과 출력
		}
	}
}
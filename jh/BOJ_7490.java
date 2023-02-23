package algorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 0 만들기 / 골드5 / 1시간 30분
 * https://www.acmicpc.net/problem/7490
 */

public class BOJ_7490 {
	static int[] nums;
	static StringBuilder sb, result = new StringBuilder();
	static PriorityQueue<String> queue;
	
	// 숫자를 찾는 메서드
	static ArrayList<Integer> findNumber(String exp) {
		String num = "";
		char c;
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for(int i = 0; i < exp.length(); i++) { // 표현식으 전부 탐색하며
			c = exp.charAt(i);
			if(c != '+' && c != '-') // 연산자가 아니면
				num += String.valueOf(c); // String에 concat-> (공백 연산자로 인해 두 자릿수 이상의 숫자가 나올 수 있음
			else { // 연산자면 -> 숫자가 종료된 것
				nums.add(Integer.parseInt(num)); // concat한 문자열을 숫자로 변환해 리스트에 add
				num = ""; // 문자열 저장 변수 초기화
			}
		}
		nums.add(Integer.parseInt(num)); // 마지막 값을 리스트에 add
		return nums; // 구한 리스트 반환
	}
	
	// 연산자를 찾는 메서드
	static ArrayList<Character> findOperator(String exp) {
		char c;
		ArrayList<Character> ops = new ArrayList<Character>();
		for(int i = 0; i < exp.length(); i++) { // 포현식을 전부 탐색하며
			c = exp.charAt(i);
			if(c == '+' || c == '-') // 연산자면
				ops.add(c); // 리스트에 add
		}
		return ops; // 구한 리스트 반환
	}
	
	// 계산 메서드
	static int calc(ArrayList<Integer> nums, ArrayList<Character> ops, int idx, int sum) {
		if(idx == ops.size()) // 연산자를 전부 확인한 경우
			return sum; // 계산 결과 반환
		if(ops.get(idx) == '+') // 플러스 연산자면
			return calc(nums, ops, idx+1, sum + nums.get(idx+1)); // 이전값에 다음값을 더해 함수 호출
		else // 마이너스 연산자면
			return calc(nums, ops, idx+1, sum - nums.get(idx+1)); // 이전값에 다음 값을 빼 함수 호출
	}
	
	// 가능한 수식을 찾는 메서드
	static void findZero(int idx, int end) {
		if(idx == end) { // n만큼 뽑았으면
			String exp = sb.toString().replace(" ", ""); // 공백 문자을 제거해 숫자를 이어 붙임
			ArrayList<Integer> nums = findNumber(exp); // 찾은 식에서 숫자를 뽑아냄
			ArrayList<Character> ops = findOperator(exp); // 찾은 식에서 연산자를 뽑아냄
			int sum = calc(nums, ops, 0, nums.get(0)); // 계산 함수 호출
			if(sum == 0) // 계산 값이 0이면
				queue.add(sb.toString()); // queue에 추가
		    return; // 종료
		}
		
		// 플러스 하는 경우
		sb.append("+" + nums[idx]); // + 연산자와 숫자 추가
		findZero(idx+1, end); // 다시 함수 호출
		sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1); // 다음 탐색을 위해 추가한 문자를 삭제해 줌
		
		// 마이너스 하는 경우
		sb.append("-" + nums[idx]); // - 연산자와 숫자 추가
		findZero(idx+1, end); // 다시 함수 호출
		sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1); // 다음 탐색을 위해 추가한 문자를 삭제해 줌
		
		// 이어 붙이는 경우
		sb.append(" " + nums[idx]); // 공백 연산자와 숫자 추가
		findZero(idx+1, end); // 다시 함수 호출
		sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1); // 다음 탐색을 위해 추가한 문자를 삭제해 줌
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt(); // 테스트 케이스 개수 입력
		int n;
		nums = new int[9]; // 최대 n이 9이기 때문에 1~9 값이 들어있는 배열을 미리 만듦
		for(int i = 0; i < 9; i++)
			nums[i] = i+1; // nums 값 할당
		for(int tc = 0; tc < t; tc++) { // 테스트 케이스만큼 반복
			n = sc.nextInt();
			sb = new StringBuilder(); // 연산자를 추가한 문자열을 담을 StringBuilder
			queue = new PriorityQueue<String>(); // 아스키 순서로 출력해야 함 -> 우선순위 큐 생성해 결과값을 담음
			sb.append(nums[0]); // 첫번째 숫자는 무조건 고정
			findZero(1, n); // 가능한 수식을 찾는 함수 호출
			while(!queue.isEmpty()) // 0이 되는 수식을
				result.append(queue.poll() + "\n"); // 아스키 순서로 뽑아 결과 변수에 append
			result.append("\n");
		}
		System.out.println(result); // 결과 출력
	}
}
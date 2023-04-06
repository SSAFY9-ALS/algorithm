package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 톱니바퀴 / 골드5 / 1시간
 * https://www.acmicpc.net/problem/14891
 */

public class BOJ_14891 {
	static HashMap<Integer, ArrayList<Integer>> gear;
	
	// 톱니바퀴를 회전시키는 메서드
	static void doRotate(int num, int d) {
		ArrayList<Integer> rotate;
		switch(d) {
		case 1: // 시계방향이라면
			rotate = gear.get(num); // 회전시킬 톱니바퀴를 받아옴
			rotate.add(0, rotate.remove(7)); // 맨 뒤에 것을 빼 맨 앞으로 추가
			gear.put(num, rotate); // 회전시킨 값으로 갱신
			break;
		case -1: // 반시계방향이라면
			rotate = gear.get(num); // 회전시킬 톱니바퀴를 받아옴
			rotate.add(rotate.remove(0)); // 맨 앞에 것을 빼 맨 뒤로 추가
			gear.put(num, rotate); // 회전시킨 값으로 갱신
		}
	}
	
	// 왼쪽 톱니바퀴를 확인하는 메서드
	static void isLeftRotate(int now, int next, int d) {
		boolean flag = next > 0 && gear.get(now).get(6) != gear.get(next).get(2); // 현재 것에 연결된 왼쪽 톱니바퀴가 회전할 수 있는지 확인하는 변수
		
		if(next - 1 > 0 && flag) // 연결된 왼쪽이 회전할 수 있고, 그것과 연결된 왼쪽 톱니바퀴(현재 - 2단계)가 존재한다면
			isLeftRotate(next, next - 1, d > 0? -1: 1); // 다시 왼쪽을 확인하는 함수 호출
		if(flag) // 왼쪽에 존재한 톱니바퀴를 회전할 수 있다면
			doRotate(next, d); // 회전을 실행하는 함수 호출
	}
	
	// 오른쪽 톱니바퀴를 확인하는 메서드
	static void isRightRotate(int now, int next, int d) {
		boolean flag = next < 5 && gear.get(now).get(2) != gear.get(next).get(6); // 현재 것에 연결된 오른쪽 톱니바퀴가 회전할 수 있는지 확인하는 변수
		
		if(next + 1 < 5 && flag) // 연결된 오른쪽이 회전할 수 있고, 그것과 연결된 오른쪽 톱니바퀴(현재 - 2단계)가 존재한다면
			isRightRotate(next, next + 1, d > 0? -1: 1); // 다시 오른쪽을 확인하는 함수 호출
		if(flag) // 오른쪽에 존재한 톱니바퀴를 회전할 수 있다면
			doRotate(next, d); // 회전을 실행하는 함수 호출
	}
	
	// 톱니바퀴 회전 기능 구현 메서드
	static void rotateGear(int num, int d) {
		int newD = d > 0? -1: 1; // 현재 톱니바퀴에 연결된 다른 톱니바퀴가 회전 가능할 때, 회전해야 될 방향
		isLeftRotate(num, num - 1, newD); // 왼쪽 톱니바퀴가 회전할 수 있는지 확인
		isRightRotate(num, num + 1, newD); // 오른쪽 톱니바퀴가 회전할 수 있는지 확인
		doRotate(num, d); // 현재 톱니바퀴 회전
		
	}
	
	// 점수 계산 메서드
	static int countScore() {
		int num, sum = 0;
		for(int i = 0; i < 4; i++) { // 각 톱니바퀴를 탐색
			num = gear.get(i+1).get(0); // 12 방향의 값을 가져옴
			if(num == 1) // S극이라면
				sum += Math.pow(2, i); // 점수 계산
		}
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		gear = new HashMap<Integer, ArrayList<Integer>>(); // 톱니바퀴 정보를 저장할 map 생성
		String sen;
		for(int i = 0; i < 4; i++) {
			sen = in.readLine();
			gear.put(i+1, new ArrayList<Integer>()); // 새로운 톱니바퀴가 시작될 때, 톱니바퀴에 연결된 리스트를 생성
			for(int j = 0; j < 8; j++) {
				gear.get(i+1).add(Character.getNumericValue(sen.charAt(j))); // 입력값을 톱니바퀴에 추가
			}
		}
		int k = Integer.parseInt(in.readLine()); // k 입력
		int num, d;
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(in.readLine());
			num = Integer.parseInt(st.nextToken()); // 회전시킨 톱니바퀴 번호 입력
			d = Integer.parseInt(st.nextToken()); // 회전 방향 입력
			rotateGear(num, d); // 회전 메서드 호출
		}
		int result = countScore(); // 점수를 계산하는 메서드 호출
		System.out.println(result); // 결과 출력
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 톱니바퀴 / 골드5 / 1시간 20분
 * @author 소수현
 * https://www.acmicpc.net/problem/14891
 */
public class BJ_14891_톱니바퀴 {
	
	static LinkedList<Integer>[] gear;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int result = 0;
		
		// 톱니바퀴 리스트 : 4개
		gear = new LinkedList[4];
		
		for(int g = 0; g < 4; g++) {
			String str = br.readLine();
			gear[g] = new LinkedList<>();
			// 톱니바퀴 값 8개 받아오기 - N극 : 0, S극 : 1
			for(int s = 0; s < 8; s++) {
				gear[g].add(Integer.parseInt(String.valueOf(str.charAt(s))));
			}
		}
		
		// 회전 횟수 : K
		int K = Integer.parseInt(br.readLine());
		
		// 회전시킨 방법 K개 받아오기
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			// 회전시킨 톱니바퀴 번호 - 1부터 시작
			int number = Integer.parseInt(st.nextToken());
			// 방향 - 1 : 시계 방향, -1 : 반시계 방향
			int direction = Integer.parseInt(st.nextToken());
			
			rotateGear(number, direction);
		}
		result += gear[0].get(0) + (2*gear[1].get(0)) + (4*gear[2].get(0)) + (8*gear[3].get(0));
		System.out.println(result);
	}
	
	/** 
	 * 톱니바퀴 돌리는 메소드
	 * @param number : 회전시키는 톱니바퀴 번호
	 * @param direction : 회전방향
	 */
	public static void rotateGear(int number, int direction) {
		// 마주보고 있는 바퀴가 서로 다른 극인지 확인하는 변수 - true : 서로 다른 극, false : 서로 같은극
		boolean[] NS = new boolean[3]; 
		
		// 마주보고 있는 바퀴가 서로 다른 극인지 확인 
		for(int g = 0; g < 3; g++) {
			// 왼쪽에 있는 톱니바퀴와 오른쪽에 있는 톱니바퀴가 서로 다른 극이면 서로 다른 방향으로 회전한다.
			if(gear[g].get(2) != gear[g+1].get(6)) {
				// 서로 다른 극이란 뜻으로 true를 넣음
				NS[g] = true;
			}
		}
		
		// 톱니바퀴 회전
		int tempD = rotate(number-1, direction);
		
		// 첫번째 톱니바퀴일 때
		if(number == 1) {
			int idx = 0;
			for(int c = 1; c < 4; c++) {
				// 만약 서로 다른 극이라면 회전하도록
				if(NS[idx]) {
					tempD = rotate(c, tempD);
					if(idx != 2) idx++;
				} else {
					break;
				}
			}
		}
		// 두번째 톱니바퀴일 때
		else if(number == 2) {
			int idx = 1;
			for(int c = 2; c < 4; c++) {
				// 만약 서로 다른 극이라면 회전하도록
				if(NS[idx]) {
					tempD = rotate(c, tempD);
					if(idx != 2) idx++;
				} else {
					break;
				}
			}
			// 첫번째 톱니바퀴하고 연결되어 있으면 회전
			if(NS[0]) {
				rotate(0, -direction);
			}
		}
		// 세번째 톱니바퀴일 때
		else if(number == 3) {
			int idx = 1;
			for(int c = 1; c >= 0; c--) {
				// 만약 서로 다른 극이라면 회전하도록
				if(NS[idx]) {
					tempD = rotate(c, tempD);
					if(idx != 0) idx--;
				} else {
					break;
				}
			}
			// 마지막 톱니바퀴하고 연결되어 있으면 회전
			if(NS[2]) {
				rotate(3, -direction);
			}
		}
		// 마지막 톱니바퀴일 때
		else {
			int idx = 2;
			for(int c = 2; c >= 0; c--) {
				// 만약 서로 다른 극이라면 회전하도록
				if(NS[idx]) {
					tempD = rotate(c, tempD);
					if(idx != 0) idx--;
				} else {
					break;
				}
			}
		}
	}
	
	/**
	 * 실제로 톱니바퀴 돌리는 메소드
	 * @param c : 회전하는 톱니바퀴 번호
	 * @param tempD : 톱니바퀴 회전 방향
	 * @return 회전후 바뀐 방향
	 */
	public static int rotate(int c, int tempD) {
		// 시계방향으로 회전
		if(tempD == 1) {
			int last = gear[c].get(gear[c].size() - 1);
			gear[c].add(0, last);
			// 마지막 원소 지워주기
			gear[c].remove(gear[c].size()-1);
			return -1;
		}
		// 반시계 방향으로 회전
		else {
			int first = gear[c].get(0);
			gear[c].addLast(first);
			// 첫번째 원소 지워주기
			gear[c].remove(0);
			return 1;
		}
	}
}

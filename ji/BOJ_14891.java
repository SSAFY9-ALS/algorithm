package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 톱니바퀴 / 골드 5 / 58분
 * @author 민정인
 * https://www.acmicpc.net/problem/14891
 */

public class BOJ_14891 {
	static List[] gList = new List[4];	// 톱니바퀴 배열
	static boolean[] chk = new boolean[4];	// 방문여부 확인
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 4; i++) {	// 톱니바퀴 정보 입력
			String s = br.readLine();
			List<Integer> list = new ArrayList<>();
			for(int j = 0; j < 8; j++) {
				int val = s.charAt(j) - '0';
				list.add(val);
			}
			gList[i] = list;
		}
		int k = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i < k; i++) {	// 명령어 입력 및 실행
			Arrays.fill(chk, false);	// 방문여부 초기화
			st = new StringTokenizer(br.readLine());
			changeGear(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		int sum = 0;
		for(int i = 0; i < 4; i++) {
			sum += (int) gList[i].get(0) * Math.pow(2, i);
		}
		System.out.println(sum);
	}
	
	static void changeGear(int num, int dir) {
		chk[num-1] = true;	// 방문한 톱니바퀴 확인
		int rightVal = (int) gList[num-1].get(2);	// 오른쪽 톱니 값
		int leftVal = (int) gList[num-1].get(6);	// 왼쪽 톱니 값
		if(dir == 1) {		// 지시 방향에 따른 움직임
			swapList(gList[num-1]);
		} else {
			swapCounter(gList[num-1]);
		}
		if(num < 4) {		// 오른쪽 톱니바퀴의 9시 방향과 현재 톱니바퀴의 3시 방향 비교
			if((int) gList[num].get(6) != rightVal && chk[num] == false) {
				changeGear(num + 1, dir * -1);	// 다른 극성 및 미방문시 오른쪽 톱니바퀴로 이동
			}
		}
		if(num > 1) {		// 왼쪽 톱니바퀴의 3시 방향과 현재 톱니바퀴의 9시 방향 비교
			if((int) gList[num - 2].get(2) != leftVal && chk[num-2] == false) {
				changeGear(num - 1, dir * -1);	// 다른 극성 및 미방문시 왼쪽 톱니바퀴로 이동
			}
		}
	}
	static void swapCounter(List<Integer> list) {	// 반시계방향 회전
		list.add(list.remove(0));
	}
	static void swapList(List<Integer> list) {		// 시계 방향 회전
		int val = list.get(7);
		list.remove(7);
		Collections.reverse(list);
		list.add(val);
		Collections.reverse(list);
	}
}

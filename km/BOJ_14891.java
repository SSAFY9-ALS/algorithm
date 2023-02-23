/**
 * 톱니바퀴 / 골드 5 / 110분
 * https://www.acmicpc.net/problem/14891
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_14891 {
	public static LinkedList<Integer> cogwheel1 = new LinkedList<>();
	public static LinkedList<Integer> cogwheel2 = new LinkedList<>();
	public static LinkedList<Integer> cogwheel3 = new LinkedList<>();
	public static LinkedList<Integer> cogwheel4 = new LinkedList<>(); // 각 톱니바퀴 정보 연결리스트로 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;
		
		String[] tmp1 = br.readLine().split("");
		String[] tmp2 = br.readLine().split("");
		String[] tmp3 = br.readLine().split("");
		String[] tmp4 = br.readLine().split("");
		
		cogwheel1.add(parseInt(tmp1[0]));
		cogwheel2.add(parseInt(tmp2[0]));
		cogwheel3.add(parseInt(tmp3[0]));
		cogwheel4.add(parseInt(tmp4[0]));
		
		for(int i=7;i>0;i--) { 
			cogwheel1.add(parseInt(tmp1[i]));
			cogwheel2.add(parseInt(tmp2[i]));
			cogwheel3.add(parseInt(tmp3[i]));
			cogwheel4.add(parseInt(tmp4[i]));
		}
		
		int n = parseInt(br.readLine()); // 회전 횟수
		
		for(int j=0;j<n;j++) {
			String[] tmp = br.readLine().split(" ");
			turn(parseInt(tmp[0]),parseInt(tmp[1]));
		}
		
		if(cogwheel1.get(0)==1) {
			answer+=1;
		}
		if(cogwheel2.get(0)==1) {
			answer+=2;
		}
		if(cogwheel3.get(0)==1) {
			answer+=4;
		}
		if(cogwheel4.get(0)==1) {
			answer+=8;
		}
		
		System.out.println(answer);
	}

	private static void turn(int wheel, int dir) { // 몇번 톱니 바퀴 / 방향 어디 
		int[][] info = new int[4][2]; // 톱니 바퀴 회전 여부  및 방향
		
		switch(wheel) {
		case 1:
			info[0][0] = 1;
			info[0][1] = dir;
			if(cogwheel1.get(6)==cogwheel2.get(2)) { // 1번 회전  234회전xx
				info[1][0]=0;
				info[2][0]=0;
				info[3][0]=0;
			}
			else { // 1,2번 회전 나머지 ???
				info[1][0] = 1;
				info[1][1] = (-1)*dir; // 방향 반대로
				if(cogwheel2.get(6)==cogwheel3.get(2)) { // 3번,4번 회전 x
					info[2][0]=0;
					info[3][0]=0;
				}
				else {// 1,2,3번 회전 , 나머지??
					info[2][0]=1;
					info[2][1]=dir; // 방향은 1번과 같이
					if(cogwheel3.get(6)==cogwheel4.get(2)) { // 4번 회전 x
						info[3][0]=0;
					}
					else { // 4번 회전
						info[3][0]=1;
						info[3][1]=info[1][1];
					}
				}
			}
			break;
		case 2:
			info[1][0]=1;
			info[1][1]=dir;
			if(cogwheel2.get(2)==cogwheel1.get(6)) { // 2번 회전 o 1번 회전 x
				info[0][0]=0;
			}
			else { // 2, 1번 회전
				info[0][0] = 1;
				info[0][1] = (-1)*dir;
			}
			if(cogwheel2.get(6)==cogwheel3.get(2)) { // 3번 회전 X
				info[2][0]=0;
				info[3][0]=0;
			}
			else {
				info[2][0]=1;
				info[2][1]=(-1)*dir; // 3번 회전
				if(cogwheel3.get(6)==cogwheel4.get(2)) { // 4번 회전 X
					info[3][0]=0;
				}
				else {// 4번 회전 
					info[3][0]=1;
					info[3][1]=dir;
				}
			}
			break;
		case 3:
			info[2][0]=1;
			info[2][1]=dir; // 3번 회전 
			if(cogwheel3.get(6)==cogwheel4.get(2)) { // 4번 회전 X
				info[3][0]=0;
			}else {
				info[3][0]=1;
				info[3][1]=(-1)*dir; // 4번 회전 
			}
			if(cogwheel3.get(2)==cogwheel2.get(6)) { // 2번 회전 X
				info[1][0]=0;
				info[0][0]=0;
			}else {
				info[1][0]=1;
				info[1][1]=(-1)*dir; // 2번 회전 
				if(cogwheel2.get(2)==cogwheel1.get(6)) { // 1번 회전 X
					info[0][0]=0;
				}
				else {// 1 번 회전 
					info[0][0]=1;
					info[0][1]=dir;
				}
			}
			break;
		case 4:
			info[3][0]=1;
			info[3][1]=dir;// 4번 회전 
			if(cogwheel4.get(2)==cogwheel3.get(6)) {// 3번 회전 X
				info[2][0]=0;
				info[1][0]=0;
				info[0][0]=0;
			}
			else {
				info[2][0]=1;
				info[2][1]=(-1)*dir;
				if(cogwheel3.get(2)==cogwheel2.get(6)) { // 2번 회전 X
					info[1][0]=0;
					info[0][0]=0;
				}
				else {
					info[1][0]=1;
					info[1][1]=dir; // 2번 회전 
					if(cogwheel2.get(2)==cogwheel1.get(6)) {// 1번 회전 X
						info[0][0]=0;
					}
					else {// 1번 회전
						info[0][0]=1;
						info[0][1]=(-1)*dir;
					}
				}
			}
			break;
		}
		
		if(info[0][0]==1) { // 도는 경우
			if(info[0][1]==1) { // 시계방향으로
				cogwheel1.add(cogwheel1.removeFirst());
			}
			else {// 반시계방향으로
				cogwheel1.add(0,cogwheel1.removeLast());
			}
		}
		if(info[1][0]==1) { // 도는 경우
			if(info[1][1]==1) { // 시계방향으로
				cogwheel2.add(cogwheel2.removeFirst());
			}
			else {// 반시계방향으로
				cogwheel2.add(0,cogwheel2.removeLast());
			}
		}
		if(info[2][0]==1) { // 도는 경우
			if(info[2][1]==1) { // 시계방향으로
				cogwheel3.add(cogwheel3.removeFirst());
			}
			else {// 반시계방향으로
				cogwheel3.add(0,cogwheel3.removeLast());
			}
		}
		if(info[3][0]==1) { // 도는 경우
			if(info[3][1]==1) { // 시계방향으로
				cogwheel4.add(cogwheel4.removeFirst());
			}
			else {// 반시계방향으로
				cogwheel4.add(0,cogwheel4.removeLast());
			}
		}
	}
}

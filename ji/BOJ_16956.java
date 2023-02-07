package net.acmicpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * 늑대와 양 / 실버 3 / 70분
 * @author 민정인
 * https://www.acmicpc.net/problem/16956
 */
public class BOJ_16956 {
	public static char[][] map;											
	public static int r, c;	
	public static int result;
	public static void makeFence(List<int[]> wList) {					//울타리 치는 함수
		for(int i = 0; i < wList.size(); i++) {
			int posX = wList.get(i)[0];
			int posY = wList.get(i)[1];
			char mX = map[Math.max(posX-1, 0)][posY];					// 각각 행이나 열이 0 or r-1(posX), c-1(posY) 범위를 넘어가지 않아야 하므로
			char mY = map[posX][Math.max(posY-1, 0)];					// Math.min과 Math.max를 활용
			char pX = map[Math.min(posX+1, r-1)][posY];
			char pY = map[posX][Math.min(posY+1, c-1)];
			if(mX == 'S' || mY == 'S' || pX == 'S' || pY == 'S') {		// 늑대 옆에 양이 있다면 울타리를 치는 의미가 없으므로 result = 0 후 리턴
				result = 0;
				return;
			}
			if(mX == '.') {
				map[Math.max(posX-1, 0)][posY] = 'D';
			}
			if(mY == '.') {
				map[posX][Math.max(posY-1, 0)] = 'D';
			}
			if(pX == '.') {
				map[Math.min(posX+1, r-1)][posY] = 'D';
			}
			if(pY == '.') {
				map[posX][Math.min(posY+1, c-1)] = 'D';
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt();
		c = sc.nextInt();
		map = new char[r][c];
		List<int[]> wList = new ArrayList<>();
		for(int i = 0; i < r; i++) {
			String s = sc.next();
			for(int j = 0; j < c; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'W') {									// 늑대의 좌표들 저장
					int[] pos = new int[2];
					pos[0] = i;
					pos[1] = j;
					wList.add(pos);
				}
			}
		}
		result = 1;

		makeFence(wList);
		System.out.println(result);
		if(result == 1) {												// result = 0은 곧 울타리의 의미가 없고 출력하지 않음
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < r; i++) {
				for(int j = 0; j < c; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
			System.out.println(sb);
		}
	}
}

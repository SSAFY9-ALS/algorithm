package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 공항 / 골드 2 / 45분
 * @author 민정인
 * https://www.acmicpc.net/problem/10775
 */

public class BOJ_10775 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int g = Integer.parseInt(br.readLine());
		int p = Integer.parseInt(br.readLine());
		int[] arr = new int[g+1];
		boolean[] fChk = new boolean[g+1];
		for(int i = 0; i <= g; i++) {
			arr[i] = i;
		}
		int result = 0;
		boolean chk = false;
		for(int i = 0; i < p; i++) {	
			int gi = Integer.parseInt(br.readLine());
			boolean fill = false;
			if(chk) {							// 빈 게이트가 없어 도킹하지 못한 비행기가 발생하면 이후로는 전부 continue
				continue;
			}
			for(int j = arr[gi]; j > 0; j--) {	// 각 비행기가  도킹 가능한 게이트의 최대 번호부터 1씩 감소시키며 빈 칸을 확인
				if(!fChk[j]) {					// 비행기가 도킹하지 않은 게이트 발견시 도킹 및 결과 + 1, 해당 번호를 가진 비행기의 도킹 가능 게이트는 현재 게이트 - 1
					fChk[j] = true;
					arr[gi] = j - 1;
					result++;
					fill = true;
					break;
				}
			}
			if(!fill) {
				chk = true;
			}
		}
		System.out.println(result);
	}
	
}

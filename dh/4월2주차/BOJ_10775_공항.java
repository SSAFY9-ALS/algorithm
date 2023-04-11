import java.util.*;
import java.io.*;

/**
 * 
 * 공항 / 골드 2 / 100분
 * https://www.acmicpc.net/problem/10775
 * 
 */

public class Main {
	static int g;
	static int p;
	static boolean[] gate;
	static int[] plane;
	static int answer;
	static int[] parents;

	static int find(int x) {
		if(parents[x] == 0) {
			return 0;
			// 0을 가리키고 있다면 0 리턴
		}
		if(parents[x] == x) {
			return parents[x]--;
			// 자기 자신을 가리키고 있다면 이전 값 가리키기
		}
		return parents[x] = find(parents[x]);
		// 자신의 루트 값을 해당 parents값에 저장 후 리턴
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		g = Integer.parseInt(br.readLine());
		p = Integer.parseInt(br.readLine());
		gate = new boolean[g+1];
		plane = new int[p+1];
		parents = new int[g+1];
		for(int i = 1; i <= p; i++) {
			plane[i] = Integer.parseInt(br.readLine());
		}
		// 입력 받기
		
		for(int i = 0; i <= g; i++) {
			parents[i] = i;
			// 유니온 초기화
		}
		
		for(int i = 1; i <= p; i++) {
			if(find(plane[i]) == 0) {
				// 0을 가리키고 있다면 비행기가 어느 게이트에도 도킹할 수 없으므로 break;
				break;
			}
			answer = i;
			// 도착한 비행기 저장
		}
		
		System.out.println(answer);
	}
}

/**
 * 골드바흐의 추측 / 실버 1 / 30분
 * https://www.acmicpc.net/problem/6588
 */
package baekjoon;

import java.io.*;
import static java.lang.Integer.parseInt;

public class BOJ_6588 {
	
	static final int MAX = 1000000;
	static boolean[] prime;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		prime = new boolean[MAX+1]; // false가 소수
		prime[1]=true;
		
		for(int i=2;i<=MAX;i++) { // 소수 판별
			if(prime[i]) continue;
			for(int j=i*2;j<=MAX;j+=i) {
				prime[j]=true;
			}
		}
		
		while(true) {
			int num = parseInt(br.readLine());
			if(num==0) break;
			boolean check=false;
			
			for(int i=1;i<=num/2;i++) {
				if(!prime[i] && !prime[num-i]) { // 소수로 이루어진 경우 찾으면 끝
					sb.append(num).append(" = ").append(i).append(" + ").append(num-i).append("\n");
					check=true;
					break;
				}
			}
			if(!check) {// 만들수 없는 경우
				sb.append("Goldbach's conjecture is wrong.").append("\n");
			}
			
		}
		System.out.println(sb);

	}

}

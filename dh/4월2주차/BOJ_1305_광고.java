import java.util.*;
import java.io.*;

/**
 * 
 * 광고 / 플레티넘 4 / 60분
 * https://www.acmicpc.net/problem/1305
 * 
 */
public class Main {
	static int l;
	static char[] pattern;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		l = Integer.parseInt(br.readLine());
		pattern = br.readLine().toCharArray();

		int[] pi = new int[l];
		// 부분 일치 테이블 만들기

		for (int i = 1, j = 0; i < pattern.length; i++) {
			while (j > 0 && pattern[i] != pattern[j]) {
				j = pi[j - 1];
			}
			if (pattern[i] == pattern[j]) {
				pi[i] = ++j;
			} else {
				pi[i] = 0;
			}
		}
		System.out.println(l - pi[l - 1]);
	}
}

package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 중복 빼고 정렬하기 / 실버5 / 7분
 * https://www.acmicpc.net/problem/10867
 */

public class BOJ_10867 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < n; i++)
			set.add(Integer.parseInt(st.nextToken()));
		ArrayList<Integer> list = new ArrayList(set);
		Collections.sort(list);
		for(int num: list)
			System.out.print(num + " ");
	}
}

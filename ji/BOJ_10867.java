package net.acmicpc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 중복 빼고 정렬하기 / 실버 5 / 7분
 * @author 민정인
 * https://www.acmicpc.net/problem/10867
 *
 */

public class BOJ_10867 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		TreeSet<Integer> ts = new TreeSet<>();
		for(int i = 0; i < n; i++) {
			ts.add(Integer.parseInt(st.nextToken()));
		}
		Iterator iter = ts.iterator();
		while(iter.hasNext()) {
			sb.append(iter.next() + " ");
		}
		bw.append(sb.toString());
		bw.flush();
		bw.close();
	}
}

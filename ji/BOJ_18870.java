package net.acmicpc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;
/**
 * 좌표 압축 / 실버 2 / 70분
 * https://www.acmicpc.net/problem/18870
 * @author 민정인
 *
 */
public class BOJ_18870 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		Map<Integer, Integer> map = new HashMap<>();				// 각 값들에 대한 좌표압축값을 저장하기 위해 만든 
		StringTokenizer st = new StringTokenizer(br.readLine());
		TreeSet<Integer> s = new TreeSet<>();						// 정렬되는 set. 입력한 좌표들이 중복없이 존재하게 하기 위해 만든 트리셋
		for(int i = 0; i < n; i++) {
			int val = Integer.parseInt(st.nextToken());
			arr[i] = val;											// 각 위치에 맞는 좌표들을 넣어야 하므로 배열에 저
			s.add(val);												// 입력된 데이터들의 자동 정렬 및 중복 제거
		}
		Iterator iter = s.iterator();
		int cnt = 0;
		while(iter.hasNext()) {
			map.put((int) iter.next(), cnt++);						// 좌표의 값이 가장 낮은 경우 0에서 시작하여 그보다 큰 좌표들일수록 1씩 증가하므로
		}															// 이를 맵에 <좌표값, 압축좌표값> 형태로 저
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < n; i++) {
			str.append(map.get(arr[i]) + " ");						// 배열 내에 존재하는 좌표들에 해당되는 압축좌표값들을 출력하면 되기 때문에
		}															// StringBuilder에 바로 입
		bw.write(str + "\n");
		bw.flush();
		bw.close();
	}
}

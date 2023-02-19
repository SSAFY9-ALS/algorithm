/**
 * 회의실 배정 / 실버 1 / 20분
 * https://www.acmicpc.net/problem/1931
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_1931 {
	
	private static class Meeting implements Comparable<Meeting>{
		int start;
		int end;
		
		public Meeting(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) { // 종료시간 오름차순 정렬 / 같을 시 시작시간 오름차순
			return this.end != o.end ? this.end - o.end: this.start -o.start;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = parseInt(br.readLine());
		
		Meeting[] arr= new Meeting[n]; // 회의실 배열
		
		for(int i=0;i<n;i++) {
			String[] tmp = br.readLine().split(" ");
			
			arr[i] = new Meeting(parseInt(tmp[0]),parseInt(tmp[1]));
		}
		
		Arrays.sort(arr);
		
		List<Meeting> result = new ArrayList<Meeting>(); 
		
		int answer = 0;
		int now_end = 0;
		
		for(int i=0;i<n;i++) {
			if(arr[i].start >= now_end) { // 가장 최근ㄴ에 끝난종료시간보다 현재 시작시간이 같거나 크면 추가
				now_end = arr[i].end;
				answer++;
			}
		}
		System.out.println(answer);
	}

}

/**
 * AC / 골드5 / 110분
 * https://www.acmicpc.net/problem/5430
 */
package algorithm_with_java.datastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_5430 {

	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());// 테스트케이스
		
		for(int t=0;t<tc;t++) {
			String func = br.readLine(); // 명령어 입력 받기
			int num = Integer.parseInt(br.readLine()); //배열 개수 입력 받기
			String tmp = br.readLine(); // 배열 문자열로 입력 받음
			StringBuilder answer = new StringBuilder();
			
			// int 배열로 만들기 만들기
			ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
			String[] arr = new String[num];
			int index = 0;
			tmp = tmp.replace(",", " ");
			tmp = tmp.replace("[", "");
			tmp = tmp.replace("]", "");
			arr = tmp.split(" ");
			for(int i=0;i<num;i++) {
				deque.add(Integer.parseInt(arr[i]));
			}
			
			// error 뜨는 경우 먼저 확인
			// 배열의 개수 < 명령어중 D 의 개수
			int dCount = func.length() - func.replace("D", "").length();
			int rCount  = 0;
			int start = 0;
			int end = arr.length-1;
			
			if(dCount > num) {
				System.out.println("error");
				continue;
			}
			else { // 나머지 경우
				for(int i=0;i<func.length();i++) {
					if(func.charAt(i)=='R') {
						rCount += 1;
						continue;
					}
					else {
						if(rCount%2==1 && !deque.isEmpty()) { // 홀수 맨뒤 제거
							deque.removeLast();
						}
						else if(rCount%2==0 && !deque.isEmpty()) { // 짝수 맨 앞 제거
							deque.removeFirst();
						}
					}
				}
				
				// 출력
				answer.append("[");
				if(!deque.isEmpty()) {
					if(rCount%2==0) { // 짝수 앞부터 출력
						answer.append(deque.pollFirst());
						while(!deque.isEmpty()) {
							answer.append(',').append(deque.pollFirst());
						}
					}
					else {  // 홀수  뒤부터 출력
						answer.append(deque.pollLast());

						while(!deque.isEmpty()) {
							answer.append(',').append(deque.pollLast());
						}
					}
				}
				//answer.deleteCharAt(answer.length()-1);
				answer.append("]");
			}
			System.out.println(answer);
		}
		
		
	}

}

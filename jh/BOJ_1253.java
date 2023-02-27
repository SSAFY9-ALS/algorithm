package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 좋다 / 골드4 / 1시간
 * https://www.acmicpc.net/problem/1253
 */

public class BOJ_1253 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine()); // n 입력
		int[] nums = new int[n]; // 입력된 숫자를 저장할 배열
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++)
			nums[i] = Integer.parseInt(st.nextToken()); // 숫자 입력
		Arrays.sort(nums); // 정렬
		
		int result = 0; // 결과 변수
		int search, start, end, sum;
		for(int i = 0; i < n; i++) {
			search = nums[i]; // 두 수의 합으로 나타내고 싶은 값
			start = 0; // 시작 인덱스 초기화
			end = n-1; // 끝 인덱스 초기화
			while(start < end) {
				if(i == start) { // start 값이 현재 나타내고 싶은 값일 때
					start++; // 다음으로 넘어가고
					continue; // 무시
				}
				else if(i == end) { // end 값이 현재 나타내고 싶은 값일 때
					end--; // 다음으로 넘어가고
					continue; // 무시
				}
				sum = nums[start] + nums[end]; // start, end가 가리키는 두 수의 합
				if(sum == search) { // 그 합이 search와 같으면
					result++; // 결과 1 증가
					break; // 반복문 종료
				}
				else if(sum < search) // 합이 search보다 작으면
					start++; // 정렬되어 있기 때문에 start를 1 증가시켜 다음 수를 봄
				else // 합이 search보다 크면
					end--; // 정렬되어 있기 때문에 end를 1 감소시켜 다음 수를 봄
			}
		}
		System.out.println(result); // 결과 출력
	}
}

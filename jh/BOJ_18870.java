package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 좌표 압축 / 실버2 / 1시간 20분
 * https://www.acmicpc.net/problem/18870
 */

public class BOJ_18870 {
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 시간을 줄이기 위해 사용
		int n = Integer.parseInt(br.readLine());
		int num;
		int[] point = new int[n]; // n 크기만큼 배열 생성
		HashSet<Integer> set = new HashSet<Integer>(); // 입력된 값 중 중복값을 거를 set 생성
		
		String[] arr = br.readLine().split(" ");
		for(int i = 0; i < n; i++) {
			num = Integer.parseInt(arr[i]);
			point[i] = num;
			set.add(num);
		}
		
		Integer[] sortedNum = set.toArray(new Integer[0]); // set을 정렬시키기 위해 다시 Integer 배열로 변환
		Arrays.sort(sortedNum); // 정렬 수행
		
		// 정렬되었기 때문에 배열의 인덱스가 그 값의 좌표 압축 결과라고 할 수 있음 -> 입력된 값을 키로 한 map을 생성해 결과를 담음
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < sortedNum.length; i++) { //
			map.put(sortedNum[i], i);
		}
		
		for(int p: point) {
			sb.append(map.get(p) + " "); // System.out.print()를 수행하면 시간 초과 -> 시간 단축을 위해 사용
		}
		System.out.println(sb);
		br.close();
	}
}

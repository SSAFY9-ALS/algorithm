package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 나무 자르기 / 실버2 / 1시간 
 * https://www.acmicpc.net/problem/2805
 */

public class BOJ_2805 {
	static long findHeight(long[] tree, long start, long end, long m) {
		if(start > end) { // 종료 조건이면
			return end; // 종료
		}
		long count = 0;
		long mid = (start + end) / 2; // 설정할 높이 구함
		for(int i = tree.length - 1; i >= 0; i--) { // 오름차순으로 정렬되었기 때문에 뒤에서부터 탐색
			if(tree[i] > mid) // 현재 높이보다 크면
				count += tree[i] - mid; // count에 더해줌
			else // 오름차순 정렬이기 때문에 현재 확인하는 값이 높이보다 작다는 건 앞으로 탐색할 것들 역시 작다는 뜻
				break; // 종료
			if(count > m) // 중간에 m을 넘어갈 때도 바로 종료 가능
				break;
		}
		if(count == m) // count된 것이 m과 같다면
			return mid; // 현재 높이 반환
		if(count < m) // count된 것이 m보다 작으면 -> 더 낮은 높이에서 탐색해야 함
			return findHeight(tree, start, mid- 1, m); // 앞 부분은 탐색
		else // count된 것이 m보다 크면 -< 더 높은 높이에서 탐색해야 함
			return findHeight(tree, mid + 1, end, m); // 뒷 부분 탐색
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		long m = Integer.parseInt(st.nextToken()); // 최대 20억이기 때문에 long으로 선언
		long[] tree = new long[n]; // 높이 또한 long으로 선언
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++)
			tree[i] = Long.parseLong(st.nextToken()); // tree들 입력
		Arrays.sort(tree); // 이분 탐색을 위해 정렬
		System.out.println(findHeight(tree, 1, tree[n-1], m)); // 함수 호출
	}
}

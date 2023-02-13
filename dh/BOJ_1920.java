import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 
 * 수 찾기 / 실버4 / 40분
 * https://www.acmicpc.net/problem/1920
 *
 */

public class Main {
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		// 입력받은 배열을 이분탐색을 위해 오름차순 정렬
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			System.out.println(binarysearch(Integer.parseInt(st.nextToken())));
			// 이분탐색함수를 통해 해당 입력값의 존재여부를 출력
		}
		
	}
	static int binarysearch(int num) {
		int low = 0;
		// 왼쪽 끝 인덱스
		int high = arr.length -1;
		// 오른쪽 끝 인덱스
		
		while(true) {
			int mid = (low + high) / 2;
			// 중간 인덱스
			if(arr[mid] == num)
				// 중간 인덱스 위치의 배열 값이 num과 같으면 num이 존재하므로 1 리턴
				return 1;
			
			else if(arr[mid] > num)
				high = mid - 1;
			// mid위치의 값이 num 보다 크면 num은 mid기준으로 왼쪽에 있으므로 오른쪽 끝을 mid-1로 설정
			else if(arr[mid] < num)
				low = mid + 1;
			// mid위치의 값이 num 보다 작으면 num은 mid기준으로 오른쪽에 있으므로 왼쪽 끝을 mid+1로 설정
			if(high < low)
				// 기저조건 오른쪽 끝이 왼쪽 끝보다 왼쪽에 있으면 탐색을 완료했으므로 while문 탈출 후 0 리턴
				break;
		}
		return 0;
	}
}
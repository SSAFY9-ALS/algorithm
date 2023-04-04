import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * 행복 유치원 / 골드 5 / 40분
 * https://www.acmicpc.net/problem/13164
 * 
 */

public class Main {
	static int n;
	static int k;
	static int[] tall;
	static List<Integer> sub;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		tall = new int[n];
		sub = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		tall[0] = Integer.parseInt(st.nextToken());
		// 원생 키 저장
		for(int i = 1; i < n; i++) {
			tall[i] = Integer.parseInt(st.nextToken());
			sub.add(tall[i] - tall[i-1]);
			// 앞뒷사람의 키 차이 저장
		}
		Collections.sort(sub);
		// 오름차순 정렬
		
		for(int i = 0; i < n-k; i++) {
			answer += sub.get(i);
			// 앞뒤사람의 키 차이가 큰 값을 기준으로 조를 만들면 비용이 최소가 된다.
		}
		System.out.println(answer);
	}

}

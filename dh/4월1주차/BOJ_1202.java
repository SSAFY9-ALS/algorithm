import java.util.*;
import java.io.*;
/**
 * 
 * 보석 도둑 / 골드 2 / 70분
 * https://www.acmicpc.net/problem/1202
 * 
 */
class Pair implements Comparable<Pair> {
	int m;
	int v;

	public Pair(int m, int v) {
		super();
		this.m = m;
		this.v = v;
	}

	@Override
	public int compareTo(Pair o) {
		return Integer.compare(this.m, o.m);
		// 보석의 무게를 기준으로 정렬하기 위해서 설정
	}
}

public class Main {
	static int n;
	static int k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		List<Pair> j_list = new ArrayList<>();
		List<Integer> b_list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			j_list.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		for (int i = 0; i < k; i++) {
			b_list.add(Integer.parseInt(br.readLine()));
		}

		Collections.sort(j_list);
		Collections.sort(b_list);
		// 작은 용량의 배낭을 먼저 가져와 작은 무게의 보석을 먼저 넣기 위하여 정렬

//		for(int i = 0; i < k; i++) {
//			int bag = b_list.get(i);
//			for(int j = 0; j < n; j++) {
//				if(j_list.get(j).m <= bag) {
//					
//				}
//			}
//		}

		int num = 0;
		// num -> 보석을 가리키는 포인터
		long answer = 0;
		//  300,000,000,000 최대 가격
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i = 0; i < k; i++) {
			int max = 0;
			int bag = b_list.get(i);
			// 작은 용량을 기준으로 정렬된 가방을 가져오기
			while(num < n && j_list.get(num).m <= bag) {
				// 해당 포인터가 보석의 개수 안에 있고, 보석의 무게가 가방의 무게보다 작을 때 실행
				pq.add(j_list.get(num).v);
				// 가방에 넣을 수 있는 모든 보석의 경우의 수를 pq에 넣기
				num++;
				// 포인터 증가
			}
			if(!pq.isEmpty()) {
				answer+=pq.poll();
				// 가장 가치가 높은 보석을 실제 가방에 넣기
			}
		}
		System.out.println(answer);
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 보석도둑 / 골드2 / 2시간
 * https://www.acmicpc.net/problem/1202
 */
public class BJ_1202_보석도둑 {
	static class Jewelry implements Comparable<Jewelry> {
		int weight;
		long price;
		
		public Jewelry(int weight, long price) {
			this.weight = weight;
			this.price = price;
		}
		
		@Override
		public int compareTo(Jewelry o) {
			// 1. 가격순 정렬
			return (int)o.price - (int)this.price;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 보석 개수 : N, 가방 개수 : K
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 보석 큐
		PriorityQueue<Jewelry> jewelry = new PriorityQueue<>();
		
		// 보석 리스트
		ArrayList<Jewelry> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			
			list.add(new Jewelry(weight, price));
		}
		

		Collections.sort(list, (o1, o2) -> {
			return o1.weight - o2.weight;
		});
		
		// 가방 리스트
		ArrayList<Integer> bags = new ArrayList<>();
		
		for (int i = 0; i < K; i++) {
			int weight = Integer.parseInt(br.readLine());
			bags.add(weight);
		}
		
		// 가방 정렬
		Collections.sort(bags);
		
		// 보석 가격의 합
		long sum = 0;
		int index = 0;
		
		// 가방 리스트를 돌면서 jewelry에 들어있는 보석의 무게 비교
		for (int i = 0; i < bags.size(); i++) {
			// 보석 리스트를 돌면서 가방보다 작으면 우선순위 큐에 넣음
			while(index < N) {
				
				int weight = list.get(index).weight;
				long price = list.get(index).price;
				
				if(weight <= bags.get(i)) jewelry.add(new Jewelry(weight, price));
				else break;
				index++;
			}
			
			// 우선순위 큐에 넣은 걸 빼고 확인
			if(jewelry.isEmpty()) continue;
			
			Jewelry je = jewelry.poll();
			
			sum += je.price;
		}
		
		System.out.println(sum);
	}
}

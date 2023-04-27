package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 트리의 지름 / 골드2 / 2시간
 * https://www.acmicpc.net/problem/1167
 */

public class BOJ_1167 {
	static int v;
	static ArrayList<int[]>[] tree;
	
	static int[] findMaxDistance(int start) {
		// 우선순위 큐를 사용해 거리가 먼 정점 우선으로 poll되게 함
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
			return o2[1] - o1[1];
		});
		int[] cost = new int[v+1];
		boolean[] visited = new boolean[v+1];
		queue.offer(new int[] {start, 0});
		visited[start] = true;
		
		int n;
		int[] temp;
		while(!queue.isEmpty()) {
			temp = queue.poll(); // 정점 하나 poll
			n = temp[0];
			for(int[] node: tree[n]) { // 정점과 연결된 정점에 대해
				if(!visited[node[0]]) { // 아직 방문하지 않았으면
					cost[node[0]] += cost[n] + node[1]; // 거리 갱신
					visited[node[0]] = true; // 방문 처리
					queue.offer(node); // 값 다시 큐에 넣어 다음 체크
				}
			}
		}
		
		// 가장 거리가 먼 정점과 그때의 거리 리턴
		int maxNode = 0, maxDepth = 0;
		for(int i = 0; i <= v; i++) {
			if(maxDepth < cost[i]) {
				maxDepth = cost[i];
				maxNode = i;
			}
		}
		return new int[] { maxNode, maxDepth};
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		v = Integer.parseInt(in.readLine());
		
		tree = new ArrayList[v+1];
		for(int i = 1; i <= v; i++)
			tree[i] = new ArrayList<>();
		
		// 값 입력
		int num, v1, d;
		for(int i = 1; i <= v; i++) {
			st = new StringTokenizer(in.readLine());
			num = Integer.parseInt(st.nextToken());

			while(true) {
				v1 = Integer.parseInt(st.nextToken());
				if(v1 == -1)
					break;
				d = Integer.parseInt(st.nextToken());
				tree[num].add(new int[] {v1, d});
			}
		}
		
		// 트리의 지름 구하는 방법
		// 1. 임의의 한 점에서 가장 거리가 먼 정점 구함
		// 2. 구한 정점으로부터 가장 거리가 먼 정점까지의 거리
		
		int[] tempMax = findMaxDistance(1); // 1번 점으로부터 가장 거리가 먼 정점 구함
		int[] resultMax = findMaxDistance(tempMax[0]); // 구한 정점으로부터 가장 거리가 먼 정점 구함
		System.out.println(resultMax[1]); // 결과 출력
	}
}

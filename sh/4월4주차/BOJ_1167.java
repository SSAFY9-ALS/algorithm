import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *	트리의 지름 / 골드 2 / 2시간
 */
public class BOJ_1167 {
	
	static ArrayList<int[]>[] tree;
	static boolean[] visited;
	static int max, startNode;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int V = Integer.parseInt(br.readLine());
		tree = new ArrayList[V+1];
		for (int i = 0; i < V+1; i++) {
			tree[i] = new ArrayList<>();
		}
		visited = new boolean[V+1];
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int vertex1 = Integer.parseInt(st.nextToken());
			while (true) {
				int vertex2 = Integer.parseInt(st.nextToken());
				if(vertex2 == -1) break;
				int val = Integer.parseInt(st.nextToken());
				tree[vertex1].add(new int[] {vertex2, val});
			}
		}
		
		visited[1] = true;
		dfs(1, 0);
		
		visited = new boolean[V+1];
		max = 0;
		visited[startNode] = true;
		dfs(startNode, 0);
		
		
		System.out.println(max);
	}
	
	static void dfs(int index, int totalDis) {
		if (totalDis > max) {
			max = totalDis;
			startNode = index;
		}
			
		for (int[] array : tree[index]) {
			if(visited[array[0]]) continue;
			
			visited[array[0]] = true;
			int dis = array[1];
			dfs(array[0], totalDis + dis);			
		}
		
	}
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
class Town implements Comparable<Town> {
    int end;
    int weight;
 
    Town(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
 
    @Override
    public int compareTo(Town arg0) {
        return weight - arg0.weight;
    }
}

/*
      [1,2,3,4 번 노드가 있다고 했을 때 2번 노드가 파티 장소라면]
1. 파티에 참석하러 가야하는 경우 ( 1,3,4 번 노드에서 2번 노드로 가는 모든 최단거리 )
2. 파티가 끝나고 다시 집으로 가야하는 경우 ( 2번 노드들에 대해서 나머지 다른 노드들의 거리 )
=> 1번 경우에는 1,3,4번 노드에서 2번 노드(목적지)로 가는 최단거리를 다 구해야 하므로 반대로 2번 노드에서 1,3,4번 노드로 가는 최단거리를 구하는 것으로 바꿈
  따라서, 2번 노드를 목적지 노드에서 출발지 노드로 바꾸고 단방향 거리를 반대로 저장해주고 계산
*/
public class BJ_1238_파티 {
    static final int INF = 10000001;
    static ArrayList<ArrayList<Town>> arrList, reverse_arrList;
    static int N, X;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
 
        arrList = new ArrayList<>(); // 문제의 입력을 그대로 받은 배열
        reverse_arrList = new ArrayList<>(); // 문제의 입력을 반대로 받은 배열
 
        for (int i = 0; i <= N; i++) {
            arrList.add(new ArrayList<>());
            reverse_arrList.add(new ArrayList<>());
        }
 
        // arrList와 reverse_arrList를 각각 단방향 인접리스트로 구현
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
 
            // arrList는 문제의 입력을 그대로 받으므로 start인덱스에 Town클래스를 넣는다.
            arrList.get(start).add(new Town(end, weight));
            // reverse_arrList는 문제의 입력을 반대로 받으므로 위의 arrList와 반대로 입력을 받는다.
            reverse_arrList.get(end).add(new Town(start, weight));
        }
 
        int[] dist1 = dijkstra(arrList); // X에서 시작점들 사이의 최단거리
        int[] dist2 = dijkstra(reverse_arrList); // 시작점들에서 X 사이의 최단거리
 
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dist1[i] + dist2[i]);
        }
 
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    
    // 다익스트라 알고리즘
    public static int[] dijkstra(ArrayList<ArrayList<Town>> a) {
        PriorityQueue<Town> pq = new PriorityQueue<>();
        pq.offer(new Town(X, 0));
        
        boolean[] check = new boolean[N + 1];
        // 최단거리 배열
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[X] = 0;
 
        while (!pq.isEmpty()) {
            Town curTown = pq.poll();
            int cur = curTown.end;
 
            if (!check[cur]) {
                check[cur] = true;
 
                for (Town town : a.get(cur)) {
                    if (!check[town.end] && dist[town.end] > dist[cur] + town.weight) {
                        dist[town.end] = dist[cur] + town.weight;
                        pq.add(new Town(town.end, dist[town.end]));
                    }
                }
            }
        }
        return dist;
    }
 
}

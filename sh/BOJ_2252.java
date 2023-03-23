package TopologicalSort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 줄 세우기 / 골드 3 / 40분
 * https://www.acmicpc.net/problem/2252
 */
public class BJ_2252_줄세우기 {
    static int N, cnt;
    static int[] inDegree;
    static HashMap<Integer, LinkedList<Integer>> adj;
    static StringBuilder sequence;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N : 학생수 , M : 비교한 횟수
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 학생 in-degree 배열 = 학생을 가리키고 있는 간선의 개수
        inDegree = new int[N+1];

        // 학생 인접 리스트 (비교한 학생들 넣는 리스트)
        adj = new HashMap<>();

        // 순서 넣는 String
        sequence = new StringBuilder();

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            // left, right 관계 넣기
            if(!adj.containsKey(left)) {
                adj.put(left, new LinkedList<>());
            }
            adj.get(left).add(right);

            // right 학생, 가리키는 간선 개수 + 1
            inDegree[right]++;
        }

        // 방문처리
        visited = new boolean[N+1];
        while(cnt<N) {
            sortStudent();
        }

        System.out.println(sequence);
    }

    static void sortStudent() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < N+1; i++) {
            if(inDegree[i] == 0) queue.add(i);
        }

        while(!queue.isEmpty()) {
            int poll = queue.poll();
            sequence.append(poll).append(" ");
            cnt++;

            // 인접 리스트에 poll값의 key가 없으면 넘어감
            if(!adj.containsKey(poll)) continue;

            for (int i = 0; i < adj.get(poll).size(); i++) {
                int value = adj.get(poll).get(i);

                // 방문했다면 넘어감
                if(visited[value]) continue;

                // 탐색했을 때 in-degree값 1 감소
                inDegree[value]--;

                // in-degree가 0이라면 queue에 포함
                if(inDegree[value] == 0) {
                    queue.add(value);
                    // 방문표시
                    visited[value] = true;
                }
            }
        }
    }
}

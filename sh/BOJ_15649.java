import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  N과 M(1) / 실버 3 / 40분
 *  https://www.acmicpc.net/problem/15649
 */
public class q15649 {
    static int N;
    static int M;

    static boolean[] visited;

    static int[] S;

    static StringBuilder sb;
    static int[] first;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];

        S = new int[N+1];

        for(int i = 0; i < N+1; i++) {
            S[i] = i;
        }

        sb = new StringBuilder();

        first = new int[M];
        pick(0, 0);

    }

    static void pick(int count, int insert) {
        if(count == M) {
            for(int i = 0; i < M; i++) {
                System.out.print(first[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = 1 ; i < N+1; i++) {
            if(!visited[i]) { // 방문 하지 않았다면
                visited[i] = true;
                first[count] = i;
                pick(count + 1, i);
                visited[i] = false;
            }
        }
    }
}

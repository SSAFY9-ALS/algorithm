import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 여행가자 / 골드4 / 30분
 * https://www.acmicpc.net/problem/1976
 */
public class BJ_1976_여행가자 {

    static int[][] trip;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 도시의 수 : N
        int N = Integer.parseInt(br.readLine());

        // 여행 계획에 속한 도시들의 수 : M
        int M = Integer.parseInt(br.readLine());

        // 여행 할 수 있는지 경로 배열
        trip = new int[N+1][N+1];


        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                int link = Integer.parseInt(st.nextToken());

                // 행과 열이 같다면 1로 표시
                if(i == j) {
                    trip[i][j] = 1;
                    continue;
                }

                // 1이면 연결된 것, 0이면 연결 안 된 것
                trip[i][j] = link;
                trip[j][i] = link;
            }
        }

        // 플로이드 워샬
        floyd(N);

        // 여행계획
        st = new StringTokenizer(br.readLine());

        int s = 0;
        for (int i = 0; i < M; i++) {
            int e = Integer.parseInt(st.nextToken());

            if(i > 0 && trip[s][e] == 0) {
                System.out.println("NO");
                return;
            }

            s = e;
        }
        System.out.println("YES");
    }

    /**플로이드 워샬 이용해서 이어지는 거리인지 파악*/
    static void floyd(int N) {
        for (int k = 1; k < N+1; k++) {
            for (int s = 1; s < N+1; s++) {
                for (int e = 1; e < N+1; e++) {
                    if(trip[s][k] == 1 && trip[k][e] == 1) trip[s][e] = 1;
                }
            }
        }
    }
}

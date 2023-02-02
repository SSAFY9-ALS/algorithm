import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class q14889 {
    static int N;
    static int[][] S;
    static boolean[] visited;
    static int Min = Integer.MAX_VALUE;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        S = new int[N][N];
        visited = new boolean[N];


        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Cycle(0,0);
        System.out.println(Min);

    }

    public static void Cycle(int idx, int count) {
        if(count == N/2) {
            diff();
            return;
        }

        for(int i = idx; i < N; i++) {
            if(!visited[i]) { // 방문 하지 않았다면
                visited[i] = true;
                Cycle(i + 1, count + 1);
                visited[i] = false;
            }
        }
    }

    public static void diff() {
        int leftTeam = 0;
        int rightTeam = 0;
        for(int i = 0 ; i < N-1; i++) {
            for(int j = i+1; j < N;j++) {
                if (visited[i] && visited[j]) {
                    leftTeam += S[i][j];
                    leftTeam += S[j][i];
                } else if (!visited[i] && !visited[j]) {
                    rightTeam += S[i][j];
                    rightTeam += S[j][i];
                }
            }
        }
        int val = Math.abs(leftTeam - rightTeam);

        if(val == 0){
            System.out.println(val);
            System.exit(0);
        }
        Min = Math.min(val, Min);
    }
}

/**
 * 스타트와 링크 / 실버2 / 1시간
 *
 * https://www.acmicpc.net/problem/14889
 */

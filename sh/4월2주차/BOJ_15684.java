import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 사다리조작 / 골드 3 / 1시간 30분 / 4월 16일
 */
public class BJ_15684_사다리조작 {
    static int N, M, H, answer;
    static int[][] map;
    private static boolean finish = false;
    public static void main(String[] args) throws Exception{
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 세로선의 개수 : N, 가로선의 개수 : M, 세로선마다 가로선을 넣을 수 있는 위치의 개수 : H
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H+1][N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // b번 세로선과 b+1번 세로선을 a번 점선 위치에서 연결
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
            map[a][b+1] = 2;
        }
        for (int i = 0; i <= 3; i++) {
            answer = i;
            dfs(1, 0);
            if (finish) break;
        }

        System.out.println((finish) ? answer : -1);
    }

    private static void dfs(int x, int count) {
        if (finish) return;
        if (answer == count) {
            if (check()) finish = true;
            return;
        }

        for (int i = x; i < H + 1; i++) {
            for (int j = 1; j < N; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    map[i][j + 1] = 2;
                    dfs(i, count + 1);
                    map[i][j] = map[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean check() {
        for (int i = 1; i <= N; i++) {
            int x = 1, y = i;
            for (int j = 0; j < H; j++) {
                if (map[x][y] == 1) y++;
                else if (map[x][y] == 2) y--;
                x++;
            }
            if (y != i) return false;
        }
        return true;
    }
}

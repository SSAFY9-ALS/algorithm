import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 빙산 / 골드4 / 1시간 / 5월 7일
 */
public class BJ_2573_빙산 {
    static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] map;
    static Stack<int[]> stack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int cnt = 0;
        while ((cnt = separate(N, M)) < 2) {
            if (cnt == 0) {
                time = 0;
                break;
            }
            melt(N, M);
            time++;
        }
        System.out.println(time);
    }

    /**
     * 빙하 녹이는 함수, bfs 활용
     * @param N 세로 길이
     * @param M 가로 길이
     */
    static void melt(int N, int M) {
        Queue<int[]> queue = new LinkedList<>();
        int[] startPos = new int[2];
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int sea = 0;

            for (int i = 0; i < 4; i++) {
                int dx = x + directions[i][0];
                int dy = y + directions[i][1];

                if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;

                if (visited[dx][dy]) continue;

                if (map[dx][dy] == 0) sea++;
            }

            if (map[x][y] - sea < 0) map[x][y] = 0;
            else map[x][y] -= sea;
        }
    }

    /**
     * 빙하 분리하는 함수
     * @param N 세로 길이
     * @param M 가로 길이
     * @return int 쪼개진 빙하 개수
     */
    static int separate(int N, int M) {
        boolean[][] visited = new boolean[N][M];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    dfs(i, j, N, M, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    /**
     * 서로 연결된 빙하 찾는 메소드
     * @param x
     * @param y
     * @param N
     * @param M
     * @param visited
     */
    static void dfs(int x, int y, int N, int M, boolean[][] visited) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int dx = x + directions[i][0];
            int dy = y + directions[i][1];

            if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;

            if (!visited[dx][dy] && map[dx][dy] > 0) {
                dfs(dx, dy, N, M, visited);
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 치즈 / 골드3 / 1시간 / 5월 6일
 */
public class BJ_2638_치즈 {
    static final int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    static int[][] map;
    static int[][] cnt;
    static Stack<int[]> melting;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        melting = new Stack<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        boolean remain = true;
        while (true) {
            cnt = new int[N][M];
            int[] startPos = new int[2];
            startPos[0] = 1;
            startPos[1] = 0;


            // 치즈 녹이러 가기
            bfs(startPos,N,M);
            if (melting.isEmpty()) remain = false;
            while (!melting.isEmpty()) {
                int[] pos = melting.pop();
                map[pos[0]][pos[1]] = 0;
            }
            // 남아있는 치즈가 없다면 반복문 종료
            if (!remain) break;

            time++;

        }
        System.out.println(time);
    }

    static void bfs(int[] startPos, int N, int M) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(startPos);
        boolean[][] visited = new boolean[N][M];

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            for (int i = 0; i < 4; i++) {
                int dx = x + directions[i][0];
                int dy = y + directions[i][1];

                if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;

                if (visited[dx][dy]) continue;

                // 치즈 중에 공기와 맞닿는 변이 2개 이상이라면 스택에 해당 좌표를 집어넣는다.
                if (map[dx][dy] >= 1) {
                    cnt[dx][dy]++;
                    if (cnt[dx][dy] == 2) melting.push(new int[] {dx, dy});
                    continue;
                }

                visited[dx][dy] = true;
                queue.offer(new int[] {dx, dy});
            }
        }
    }
}

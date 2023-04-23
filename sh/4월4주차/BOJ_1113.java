import javax.swing.text.html.ListView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 수영장만들기 / 골드1 / 2시간 / 4월 23일
 */
public class BJ_1113_수영장만들기 {
    static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int[][] map;
    static boolean[][] visited;
    static int area;
    static boolean isPass;
    public static void main(String[] args) throws Exception {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int totalArea = 0;
        int maxHeight = 0;
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }
        // 수영장에 있는 최대 높이까지 반복하기
        for (int height = 1; height < maxHeight; height++) {
            visited = new boolean[N][M];
            for (int i = 1; i < N-1; i++) {
                for (int j = 1; j < M-1; j++) {
                    // 반복하는 높이보다 크면 bfs 돌리지 않고 넘어가기
                    if (map[i][j] > height) continue;
                    // 이미 방문했던 자리면 넘어가기
                    if (visited[i][j]) continue;
                    // 웅덩이 크기 리셋
                    area = 1;
                    // bfs 돌리기
                    bfs(N, M, new int[]{i, j}, height);
                    // 웅덩이가 제대로 만들어졌다면 전체 크기에 더해주기
                    if (!isPass) totalArea += area;
                    isPass = false;
                }
            }
        }
        System.out.println(totalArea);
    }

    /**
     * 웅덩이 크기 구하는 bfs 메소드
     * @param N 수영장 세로 크기
     * @param M 수영장 가로 크기
     * @param pos bfs를 시작하려는 시작점
     * @param height bfs를 돌릴려는 최대 높이
     */
    static void bfs(int N, int M, int[] pos, int height) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {pos[0], pos[1]});

        visited[pos[0]][pos[1]] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            for (int i = 0; i < 4; i++) {
                int dx = x + directions[i][0];
                int dy = y + directions[i][1];

                if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;

                // 보려고 하는 높이보다 사방탐색한 곳의 높이가 크다면 그대로 넘어가기
                if (map[dx][dy] > height) continue;

                if (visited[dx][dy]) continue;

                visited[dx][dy] = true;
                // 만약 웅덩이가 만들어질 수 없는 가에 쪽에 있는 곳이라면 이 곳은 웅덩이가 만들어질 수 없다는 표시(isPass=true)를 한다.
                if (dx == 0 || dx == N-1 || dy == 0 || dy == M-1) {
                    isPass = true;
                    continue;
                }
                area++;
                queue.add(new int[] {dx, dy});
            }
        }
    }
}

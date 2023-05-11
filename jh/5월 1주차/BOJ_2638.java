package may;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 치즈 / 골드3 / 40분 / 4월 28일
 * https://www.acmicpc.net/problem/2638
 */

public class BOJ_2638 {
    static int n, m;
    static int[][] cheese;

    // 치즈 제거 메서드
    static int removeCheese() {
        int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] edge = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        ArrayDeque<Point> queue = new ArrayDeque<>();
        
        // 가장자리의 값들 큐에 넣어줌
        for(int i = 0; i < n; i++) {
            queue.offer(new Point(i, 0));
            queue.offer(new Point(i, m-1));
            visited[i][0] = true;
            visited[i][m-1] = true;
        }

        Point point;
        int dx, dy;
        while(!queue.isEmpty()) {
            point = queue.poll();
            for(int k = 0; k < 4; k++) { // 상하좌우 탐색
                dx = point.x + d[k][0];
                dy = point.y + d[k][1];
                if(dx < 0 || dx >= n || dy < 0 || dy >= m)
                    continue;
                edge[dx][dy]++; // 주변값들 +1 증가
                if(cheese[dx][dy] == 0 && !visited[dx][dy]) { // 빈칸이면서 아직 방문하지 않았을 때
                    queue.offer(new Point(dx, dy)); // 큐에 넣어 다음 탐색
                    visited[dx][dy] = true; // 방문 처리
                }
            }
        }

        int cnt = 0;
        // 전체 탐색
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(cheese[i][j] == 1 && edge[i][j] >= 2) { // 녹아서 없어지는 경우면
                    cheese[i][j] = 0; // 치즈 제거
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 입력값
        cheese = new int[n][m];
        int total = 0;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < m; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());

                if(cheese[i][j] == 1)
                    total++;
            }
        }

        // 치즈가 모두 사라질 때까지
        int cnt, time = 0;
        while(total != 0) {
            cnt = removeCheese(); // 메서드 수행
            total -= cnt;
            time++;
        }

        System.out.println(time); // 결과 출력
    }
}
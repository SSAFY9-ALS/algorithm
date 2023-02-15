import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 로봇청소기 / 골드5 / 2시간
 * https://www.acmicpc.net/problem/14503
 */
public class BJ_14503_로봇청소기 {

    static int N;
    static int M;
    static int count;
    static int[][] directions = {{-1,0}, {0,1}, {1,0}, {0, -1} };
    static int[][] room;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        room = new int[N][M];

        count = 1;

        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());

        // 로봇 청소기가 바라보는 방향
        int d = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(startX, startY, d);

        System.out.println(count);
    }

    public static void dfs(int startX, int startY, int direct) {
        // 현재 위치 청소
        room[startX][startY] = -1;

        for(int i =0 ; i < 4; i++) {
            direct = (direct + 3) %4;
            int dy = startX + directions[direct][0];
            int dx = startY + directions[direct][1];

            // 청소 count업
            if(dy >= 0 && dy < N && dx >= 0 && dx < M && room[dy][dx] == 0) {
                count++;
                dfs(dy, dx, direct);
                return;
            }
        }

        // 4방향 모두 청소 되어 있을 때
        int back = (direct + 2) % 4;
        int dy = startX + directions[back][0];
        int dx = startY + directions[back][1];

        if(dy >= 0 && dy < N && dx >= 0 && dx < M && room[dy][dx] != 1) {
            dfs(dy,dx, direct);
        }

    }

}

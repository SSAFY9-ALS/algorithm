import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * 뱀 / 골드4 / 75 / 2월 28일
 * https://www.acmicpc.net/problem/3190
 */

class Pos {
    private int x;
    private int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pos pos = (Pos) o;
        return x == pos.x && y == pos.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class BJ_3190_뱀 {
    static int N;
    static int[][] board;       // 2 = 사과, 1 = 뱀
    static int[][] dr = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static Deque<Pos> snake = new ArrayDeque<>();
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        int K = Integer.parseInt(br.readLine());
        //과자 위치
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            board[r][c] = 2;
        }

        snake.offer(new Pos(0, 0));
        int currD = 0;
        //명령어 입력받기
        boolean flag = false;
        int commandNum = Integer.parseInt(br.readLine());
        for (int c = 0; c < commandNum; c++) {
            st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            char turn = st.nextToken().charAt(0);
            if (!move(currD, sec)) {
                flag = true;
                break;
            }
            if (turn == 'L') {
                currD = currD == 0 ? 3 : currD - 1;
            } else {
                currD = (currD + 1) % 4;
            }
        }
        if (!flag) {
            move(currD, Integer.MAX_VALUE);
        }
        System.out.println(time + 1);
    }

    //먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
    private static boolean move(int currD, int sec) {
        int newX = snake.peekFirst().getX() + dr[currD][0];
        int newY = snake.peekFirst().getY() + dr[currD][1];
        while (time < sec) {
            if (newX >= N || newX < 0 || newY >= N || newY < 0) {
                return false;
            }
            //만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
            if (board[newX][newY] == 2) {
                board[newX][newY] = 0;
            } else if (snake.contains(new Pos(newX, newY))) {
                return false;
            } else {
                snake.pollLast();
            }

            snake.offerFirst(new Pos(newX, newY));
            time++;
            newX += dr[currD][0];
            newY += dr[currD][1];
        }
        return true;
    }
}

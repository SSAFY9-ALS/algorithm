package march;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20057 {
    static int n, total;
    static int[][] map;
    static int[] percent = {1, 1, 2, 2, 7, 7, 10, 10, 5, 0};
    static int[][] moveX = {
            {-1, 1, -2, 2, -1, 1, -1, 1, 0, 0}, // 좌
            {-1, -1, 0, 0, 0, 0, 1, 1, 2, 1}, // 하
            {-1, 1, -2, 2, -1, 1, -1, 1, 0, 0}, // 우
            {1, 1, 0, 0, 0, 0, -1, -1, -2, -1} // 상
    };
    static int[][] moveY = {
            {1, 1, 0, 0, 0, 0, -1, -1, -2, -1}, // 좌
            {-1, 1, -2, 2, -1, 1, -1, 1, 0, 0}, // 하
            {-1, -1, 0, 0, 0, 0, 1, 1, 2, 1}, // 우
            {-1, 1, -2, 2, -1, 1, -1, 1, 0, 0} // 상
    };

    static boolean isRange(int x, int y) {
        if(x >= 0 && x < n && y >= 0 && y < n)
            return true;
        return false;
    }

    static void runTornado(int x, int y, int d) {
        int dx, dy, num, origin = map[x][y];
        for(int i = 0; i < 10; i++) {
            dx = x + moveX[d][i];
            dy = y + moveY[d][i];
            num = origin * percent[i] / 100;
            if(isRange(dx, dy)) {
                map[dx][dy] += num;
            }
            else {
                total += num;
            }
            map[x][y] -= num;
        }
    }

    static int[] moveSand(int x, int y, int d) {
        switch (d) {
            case 0:
                y--;
                break;
            case 1:
                x++;
                break;
            case 2:
                y++;
                break;
            case 3:
                x--;
                break;
        }
        return new int[] {x, y};
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(in.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int x = n / 2, y = n / 2;
        int d = 0; // 좌: 0, 하: 1, 우: 2, 상: 3
        int step = 1; // 움직이는 칸의 개수
        int c = 0; // step을 바꾸기 위한 변수

        int[] temp;
        for (int i = 0; i < (n - 1); i++) {
            for (int j = 0; j < 2; j++) {
                // 흩날리기 함수 호출
                for (int s = 0; s < step; s++) {
//                    System.out.println(x + " " + y);
                    if(map[x][y] != 0)
                        runTornado(x, y, d);
                    temp = moveSand(x, y, d);
                    x = temp[0];
                    y = temp[1];

                    for(int[] a: map)
                        System.out.println(Arrays.toString(a));
                    System.out.println();
                }

                // 방향 변경
                d = (d+1) % 4;
            }
            // step 1 증가
            step++;
        }
        // 한번 더 움직임
        x = 0;
        y = n-1;
        d = 0;
        for(int s = 0; s < n; s++) {
//            System.out.println(x + " " + y);
            runTornado(x, y, d);
            temp = moveSand(x, y, d);
            x = temp[0];
            y = temp[1];
        }
        System.out.println(total);
    }
}
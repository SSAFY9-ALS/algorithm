import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 원판돌리기 / 골드 2 / 2시간
 * https://www.acmicpc.net/problem/17822
 */
public class BJ_17822_원판돌리기 {

    static int[][] circle;
    static boolean[][] isSelected;
    public static void main(String[] args) throws  Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 반지름의 크기 : N
        int N = Integer.parseInt(st.nextToken());
        // 원판에 적혀있는 정수의 개수 : M
        int M = Integer.parseInt(st.nextToken());
        // 회전 횟수 : T
        int T = Integer.parseInt(st.nextToken());

        // 원판
        circle = new int[N+1][M];
        // 지워졌는지 확인하는 변수
        isSelected = new boolean[N+1][M];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                circle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            // x의 배수인 원판을 회전
            int x = Integer.parseInt(st.nextToken());
            // 방향 -> 0 : 시계방향, 1 : 반시계 방향
            int d = Integer.parseInt(st.nextToken());
            // 회전 칸 수
            int k = Integer.parseInt(st.nextToken());

            // 회전시키기
            rotate(x, d, k, N, M);
            // 인접하면서 같은 수 찾기
            if(!adjacent(N, M)) average(N, M, false);
        }
        int res = average(N, M, true);

        System.out.println(res);
    }

    /** 회전 메소드 */
    static void rotate(int x, int d, int k, int N, int M) {
        for (int K = 0; K < k; K++) {
            // 시계방향 회전
            if (d == 0) {
                for (int i = x; i <= N; i+=x) {
                    // 마지막 숫자로 초기화
                    int before = circle[i][M - 1];
                    boolean beforeSelected = isSelected[i][M-1];
                    for (int j = 0; j < M; j++) {
                        before = swap(i, j, before);
                        beforeSelected = swapBoolean(i,j, beforeSelected);
                    }
                }
            }
            // 반시계 방향 회전
            else if (d == 1) {
                for (int i = x; i <= N; i+=x) {
                    // 첫번째 숫자로 초기화
                    int before = circle[i][0];
                    boolean beforeSelected = isSelected[i][0];
                    for (int j = M - 1; j >= 0; j--) {
                        before = swap(i, j, before);
                        beforeSelected = swapBoolean(i,j, beforeSelected);
                    }
                }
            }
        }
    }

    static int swap(int i, int j, int before){
        int temp = circle[i][j];
        circle[i][j] = before;
        before = temp;

        return before;
    }
    static boolean swapBoolean(int i, int j, boolean beforeSelected){
        boolean tmp = isSelected[i][j];
        isSelected[i][j] = beforeSelected;
        beforeSelected = tmp;

        return beforeSelected;
    }

    /** 인접하면서 같은 수인 것 찾기 */
    static boolean adjacent(int N, int M) {
        boolean isErase = false;

        boolean[][] check = new boolean[N+1][M];

        for (int i = 1 ; i < N+1; i++) {
            for (int j = 0; j < M; j++) {
                // 이미 지워진 적이 있다면 건너뛰기
                if(isSelected[i][j]) continue;

                // 서로 인접하면서 수가 같을 때
                // isSelected로 지워졌다고 표시하기
                
                if(i < N && circle[i][j] == circle[i+1][j]){
                    // 비교하는 대상도 이미 지워진 적 있다면 건너뛰기
                    if(!isSelected[i+1][j]) {
	                    check[i][j] = true;
	                    check[i+1][j] = true;
	                    isErase = true;
                    }
                }
                if(circle[i][j] == circle[i][(j+1)%M]) {
                    // 비교하는 대상도 이미 지워진 적 있다면 건너뛰기
                    if(!isSelected[i][(j+1)%M]) {	
	                    check[i][j] = true;
	                    check[i][(j+1)%M] = true;
	                    isErase = true;
                    }
                }
            }
        }

        for (int i = 1; i < N+1; i++) {
            for (int j = 0; j < M; j++) {
                if(isSelected[i][j]) continue;
                isSelected[i][j] = check[i][j];
            }
        }
        return isErase;
    }

    /** 인접하거나 같은 수가 없는 경우일 때, 평균 구하기 */
    static int average(int N, int M, boolean isSum) {
        double sum = 0;
        double count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                // 지워진 숫자면 그대로 패쓰
                if(isSelected[i][j]) continue;
                sum += circle[i][j];
                count++;
            }
        }

        if(!isSum) {
            if(count != 0) {
                double average = sum / count;
                plusMinus(N, M, average);
            }
        }

        return (int)sum;
    }

    static void plusMinus(int N, int M, double average) {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                // 지워진 숫자면 그대로 패쓰
                if(isSelected[i][j]) continue;
                // 평균보다 크면 1 더해주기
                if(average < circle[i][j]) circle[i][j]--;
                    // 평균보다 작으면 1 빼주기
                else if(average > circle[i][j]) circle[i][j]++;
            }
        }
    }
}

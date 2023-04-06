import java.util.Scanner;

public class BJ_10974_모든순열 {
    static int N;
    static int[] numbers;
    static boolean[] visited;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        numbers = new int[N];
        visited = new boolean[N+1];

        recur(0);

    }

    public static void recur(int cnt) {
        if(cnt == N) {
            for(int i = 0; i < N ;i++) {
                System.out.print(numbers[i] + " ");
            }
            System.out.println();
            return;
        }

        // 앞에서부터 방문했는지 확인
        // 방문 안했다고 하면 방문 체크하고 그 다음 수 구하러 간다.
        // 해당 수 만큼 돌고 리턴하면 다시 그 수를 방문체크를 지운다.
        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                numbers[cnt] = i;
                recur(cnt + 1);
                visited[i] = false;
            }
        }
    }
}

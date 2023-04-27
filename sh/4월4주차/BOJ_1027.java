import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1027 {
    static int N;
    static int[] buildings; // 건물들 높이
    static int[] answer; // 보이는 건물의 개수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        buildings = new int[N];
        answer = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N-1; i++) {
            // 해당 index 오른쪽 건물은 무조건 보이므로 +1
            answer[i]++;
            // 해당 index 오른쪽 건물은 해당 index 건물이 보이므로 +1
            answer[i+1]++;
            // 기울기 저장(이 기울기보다 증가해야 보이는 건물이다)
            double slope = buildings[i+1] - buildings[i];
            // 해당 index+2부터 오른쪽으로만 탐색 시작
            for (int j = i+2; j < N; j++) {
                double nextSlope = calculate(i, j);
                if (nextSlope > slope) {
                    slope = nextSlope;
                    answer[i]++;
                    answer[j]++;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < answer.length; i++) {
            max = Math.max(max, answer[i]);
        }

        System.out.println(max);
    }

    static double calculate(int i, int j) {
        return (double) (buildings[j]-buildings[i]) / (j - i);
    }
}

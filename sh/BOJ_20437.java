import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문자열게임2 / / 1시간
 * https://www.acmicpc.net/problem/20437
 */
public class BJ_20437_문자열게임2 {

    static String W;
    static int K;
    static int count;
    static int result3;
    static int result4;
    static int first;
    static boolean[] visited;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            // 문자열
            W = br.readLine();

            // 정수 K
            K = Integer.parseInt(br.readLine());

            // 방문했는지 확인하는 변수
            visited = new boolean[W.length()];

            // 결과
            result3 = Integer.MAX_VALUE;
            result4 = 0;
            for(int i = 0; i < W.length(); i++) {
                count = 0;
                first = i;
                findStr3(0, i);
            }
            if(result3 == Integer.MAX_VALUE) {
                result3 = -1;
            }
            sb.append(result3).append(" ").append(result4).append("\n");
        }
        System.out.println(sb);
    }

    public static void findStr3(int cnt, int start) {
        if(cnt == K) {
            count = start - first;
            result3 = Math.min(result3, count);
            result4 = Math.max(result4, count);
//            System.out.println("============================================");
//            System.out.println("result3 : " + result3 + ", start : " + first + ", last : " + start);
//            System.out.println("result4 : " + result4 + ", start : " + first + ", last : " + start);
            return;
        }

        for(int i = start; i < W.length(); i++) {
            if(W.charAt(i) == W.charAt(first) && !visited[i]) {
                visited[i] = true;
                findStr3(cnt + 1, i + 1);
            }
        }
    }
}

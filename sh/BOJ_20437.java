import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문자열게임2 / 골드5 / 1시간
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
            // 만약 반복문을 돌고 왔어도 결과값이 여전히 제일 큰 값이라면 -1을 결과로 출력해준다.
            // result3만 봐도 되는 이유는 가장 짧은 문자열이 없다면 긴 문자열도 없고, 짧은 문자열이 있다면 역시 긴 문자열 또한 존재하기 때문에(짧은 문자열이 긴 문자열이 될 수 있음) 그래서 result3만 봐도 된다.
            if(result3 == Integer.MAX_VALUE) {
                sb.append(-1).append("\n");
            } else {
                sb.append(result3).append(" ").append(result4).append("\n");
            }
        }
        System.out.println(sb);
    }

    /** 문자열의 길이 찾는 메소드 */
    public static void findStr3(int cnt, int start) {
        // K개의 문자열을 찾았으면 각각 결과값에 넣어준다.
        if(cnt == K) {
            count = start - first;
            // 문자열의 길이가 가장 짧은지 확인하는 변수
            result3 = Math.min(result3, count);
            // 문자열의 길이가 가장 긴지 확인하는 변수
            result4 = Math.max(result4, count);
            return;
        }

        for(int i = start; i < W.length(); i++) {
            if(W.charAt(i) == W.charAt(first)) {
                // 문자가 같다면 함수호출을 해주는데 K개를 찾았을 때는 그 이후로 K+1개를 찾으면 안되니까 마지막에 break를 달아준다.
                findStr3(cnt + 1, i + 1);
                break;
            }
        }
    }
}

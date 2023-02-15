import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 수영장 / 20분
 */
public class SWEA_1952_수영장 {

    static int[] tickets;
    static int[] plans;
    static int minCost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            minCost = Integer.MAX_VALUE;

            tickets = new int[4];

            plans = new int[12];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 4; i++) {
                tickets[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 12; i++) {
                plans[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);

            minCost = Math.min(tickets[3], minCost);

            sb.append("#").append(t).append(" ");
            sb.append(minCost).append("\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int month, int cost) {
        if(month >= 12) {
            minCost = Math.min(cost, minCost);
            return;
        }

        // 하루 이용권 사용할 경우
        dfs(month + 1, cost + (tickets[0] * plans[month]));

        // 한달 이용권을 사용할 경우
        dfs(month + 1, cost + tickets[1]);

        // 세달 이용권을 사용할 경우
        dfs(month + 3, cost + tickets[2]);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * List of Unique Numbers / 골드4 / 100분 / 2월 27일
 * https://www.acmicpc.net/problem/13144
 */
public class BJ_13144_ListofUniqueNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long result = 0;
        boolean[] checked = new boolean[100001];
        st = new StringTokenizer(br.readLine());

        Deque<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            int curr = Integer.parseInt(st.nextToken());
            while (checked[curr]) {
                result += que.size();
                checked[que.pollFirst()] = false;
            }
            que.offer(curr);
            checked[curr] = true;
        }
        result += (long) que.size() * (que.size() + 1) / 2;
        System.out.println(result);
    }
}

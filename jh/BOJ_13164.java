package march;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 행복 유치원 / 골드5 / 1시간 30분
 * https://www.acmicpc.net/problem/13164
 */

public class BOJ_13164 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int diff, total = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        st = new StringTokenizer(in.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end;
        for(int i = 0; i < n-1; i++) {
            end = Integer.parseInt(st.nextToken());
            diff = end - start;
            total += diff;
            queue.offer(diff);
            start = end;
        }
        // k개의 조로 나눈다는 건, 총 k-1개의 차이가 생긴다는 것 ex) (1, 2, 3, 4) (5, 7) (9, 15)
        // (n-1)개의 인접한 키 차이 배열에서 큰 순서대로 (k-1)개의 인접한 키 차이를 무시하면 됨
        for(int i = 0; i < k-1; i++)
            total -= queue.poll();
        System.out.println(total);
    }
}

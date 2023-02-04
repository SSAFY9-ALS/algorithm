package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N과 M(2) / 실버 3 / 10분 / 2월 4일
 * https://www.acmicpc.net/problem/15650
 */
public class q15650 {
    static int N;
    static int M;

    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];

        combo(1, 0);

    }

    static void combo(int num, int count){
        if(count == M) {
            sequence();
            System.out.println();
            return;
        }

        for(int i = num; i < N+1; i++) {
            if(!visited[i]) {
                visited[i] = true;
                combo(i + 1, count + 1);
                visited[i] = false;
            }
        }
    }

    static void sequence() {
        for(int i = 1; i < N+1 ; i++) {
            if(visited[i]) {
                System.out.print(i + " ");
            }
        }
    }
}

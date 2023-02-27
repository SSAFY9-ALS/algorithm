package Baekjoon.TwoPointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 회전초밥 / 실버 1 / 20분
 * https://www.acmicpc.net/problem/2531
 */
public class BJ_2531_회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N : 회전초밥 벨트에 놓인 접시의 수
        int N = Integer.parseInt(st.nextToken());
        // d : 초밥의 가짓수
        int d = Integer.parseInt(st.nextToken());
        // k : 연속해서 먹는 접시의 수
        int k = Integer.parseInt(st.nextToken());
        // c : 쿠폰 번호
        int c = Integer.parseInt(st.nextToken());

        // 벨트에 놓인 초밥들
        int[] sushi = new int[N];

        // 선택한 초밥들
        LinkedList<Integer> temp = new LinkedList<>();
        HashSet<Integer> pick = new HashSet<>();

        // 초밥 종류
        for (int n = 0; n < N; n++) {
            sushi[n] = Integer.parseInt(br.readLine());

            if(n <= k-1) {
                temp.add(sushi[n]);
                pick.add(sushi[n]);
            }
        }
        // 초밥의 가짓수 최댓값
        int maxCnt = pick.size();

        if(!pick.contains(c)) {
            maxCnt++;
        }


        // 처음 가리키는 포인터
        int firstPoint = 0;
        // 마지막 가리키는 포인터
        int lastPoint = k;

        for (int i = 0; i < N; i++) {
            int size = 0;

            temp.remove(0);

            if(!temp.contains(sushi[firstPoint])) {
                pick.remove(sushi[firstPoint]);
            }

            pick.add(sushi[lastPoint]);
            temp.add(sushi[lastPoint]);

            if(!pick.contains(c)) {
                size++;
            }

            size += pick.size();

            // 최대값 갱신
            maxCnt = Math.max(maxCnt, size);

            firstPoint = (firstPoint + 1) % N;
            lastPoint = (lastPoint + 1) % N;
        }

        System.out.println(maxCnt);
    }
}

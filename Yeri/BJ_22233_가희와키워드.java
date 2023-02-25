package yeri.algorithm0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 가희와 키워드 / 실버2 / 15분
 * https://www.acmicpc.net/problem/22233
 */
public class BJ_22233_가희와키워드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<String> keywords = new HashSet<>();

        for (int i = 0; i < N; i++) {
            keywords.add(br.readLine());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), ",");
            while (st.hasMoreTokens()) {
                String str = st.nextToken();
                if (keywords.contains(str)) {
                    keywords.remove(str);
                }
            }
            System.out.println(keywords.size());
        }
    }
}

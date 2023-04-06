package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 운동 / 골드 4 / 2시간
 *
 */
public class BJ_1956_운동 {

    static int V;
    static final int INF = 4000001;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // v개의 마을, E개의 도로
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 마을간의 도로 연결 변수
        int[][] connection = new int[V+1][V+1];
        
        // 도로 연결 초기화
        for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				connection[i][j] = INF;
			}
		}

        // a번 마을에서 b번 마을로 가는 거리가 c인 도로
        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            connection[a][b] = c;
        }
        
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    connection[i][j] = Math.min(connection[i][k] + connection[k][j], connection[i][j]);
                }
            }
        }
        
        int ans = INF;
        for (int i = 1; i <= V; i++) {
            ans = Math.min(connection[i][i], ans);
        }
        
        if(ans != INF) {
        	System.out.println(ans);
        } else {
        	System.out.println(-1);
        }
    }
}

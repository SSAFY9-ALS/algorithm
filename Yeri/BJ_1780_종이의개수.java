package yeri.algorithm0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 종이의 개수 / 실버2 / 15분
 * https://www.acmicpc.net/problem/1780
 */
public class BJ_1780_종이의개수 {
    static int[][] arr ;
    static StringBuilder sb = new StringBuilder();
    static Map<Integer,Integer> count = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int y = 0; y < N; y++) {
            String[] str = br.readLine().split(" ");
            for(int x = 0; x < N; x++) {
                arr[y][x] = Integer.parseInt(str[x]);
            }
        }
        count.put(-1,0);
        count.put(0,0);
        count.put(1,0);
        quadTree(0,0,N);
//        System.out.println(sb);
        System.out.println(count.get(-1));
        System.out.println(count.get(0));
        System.out.println(count.get(1));
    }
    /**
     *
     * @param r 시작위치 좌표
     * @param c
     * @param size 크기
     */
    private static void quadTree(int r, int c, int size) {
//		sb.append("(");
        int curr = arr[r][c];
        for(int y = r; y<r+size;y++) {
            for(int x = c; x<c+size; x++) {
                if(curr != arr[y][x]) {
//                    sb.append("(");

                    quadTree(r, c, size/3);
                    quadTree(r, c+size/3, size/3);
                    quadTree(r, c+(size/3)*2, size/3);

                    quadTree(r+size/3, c, size/3);
                    quadTree(r+size/3, c+size/3, size/3);
                    quadTree(r+size/3, c+(size/3)*2, size/3);

                    quadTree(r+(size/3)*2, c, size/3);
                    quadTree(r+(size/3)*2, c+size/3, size/3);
                    quadTree(r+(size/3)*2, c+(size/3)*2, size/3);
//                    sb.append(")");
                    return;
                }
            }
        }
//        System.out.println(curr);
        count.put(curr,count.get(curr)+1);
//        sb.append(curr);
//		sb.append(curr).append(")");
    }

}



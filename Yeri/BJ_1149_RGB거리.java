package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * RGB거리 / 실버1 /
 * https://www.acmicpc.net/problem/1149
 */
public class BJ_1149_RGB거리 {
    static int result = 1000000;
    static int N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 3; i++) {
            fun2(i, 0, 0);
        }


        System.out.println(result);
    }
    
    /**
     *
     * @param color     index -1 의 색
     * @param index     현재 건물
     * @param temp      이전까지 금액
     */
    public static void fun2(int color, int index, int temp){
        if(index == N-1){   //마지막 건물
            temp += Math.min(arr[index][(color + 1) % 3], arr[index][(color + 2) % 3]);
            if(temp<result)
                result = temp;
        }else{
            int temp1 = temp + arr[index][(color + 1) % 3];
            if(temp1 > result){
                return;
            }else{
                fun2((color+1) % 3,index+1,temp1);
            }

            temp1 = temp + arr[index][(color + 2) % 3];
            if(temp1 > result){
                return;
            }else{
                fun2((color + 2) % 3,index+1,temp1);
            }
        }
    }
}

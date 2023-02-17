package yeri.algorithm0217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 잃어버린 괄호 / 실버2 / 10분
 * https://www.acmicpc.net/problem/1541
 */
public class BJ_1541_잃어버린괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split("-");
        String[] front = str[0].split("\\+");
        int result = 0;
        for(int i = 0; i < front.length; i++){
            result += Integer.parseInt(front[i]);
        }
        for(int i = 1; i < str.length; i++){
            String[] temp = str[i].split("\\+");
            for(int j = 0; j < temp.length; j++){
                result-=Integer.parseInt(temp[j]);
            }
        }
            System.out.println(result);
    }
}

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * List of Unique Numbers / 난이도 / 15분
 * https://www.acmicpc.net/problem/13144
 */
public class BJ_13144_ListofUniqueNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
//        int start = 0;
//        int end = 0;
        st = new StringTokenizer(br.readLine());
        Stack<Integer>
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        
        
        
        int result = N;
        for (int start = 0; start < N; start++) {
            boolean[] checked = new boolean[100000];
            checked[start] = true;
            for (int end = start+1; end < N; end++) {
                if(checked[end]){
                    break;
                }
                checked[end] = true;
                if(arr[start]==arr[end]){
                    break;
                }
                result++;
            }
        }
//        while (start!=N){
//            if(start==end){
//                result++;
//                end++;
//                continue;
//            }
//            if(arr[start]==arr[end]){
//                start++;
//            }else {
//                end++;
//                result++;
//            }
//        }
        System.out.println(result);
    }
}

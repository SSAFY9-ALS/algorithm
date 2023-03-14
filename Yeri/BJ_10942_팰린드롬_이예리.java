package ws.ws0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 팰린드롬? / 골드4 / 30분 / 3월 14일
 * https://www.acmicpc.net/problem/10942
 */
public class BJ_10942_팰린드롬_이예리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n  = Integer.parseInt(br.readLine());
        st= new StringTokenizer(br.readLine());
        int [] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int [][]arr = new int[n+1][n+1];
        for(int i = 1; i <=n; i++   ){
            arr[i][i] = 1;
            for(int j = i+1; j <= n;j++){
                arr[i][j] = isPalindrome(i-1,j-1,nums);
            }
        }
        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(arr[start][end]).append("\n");
        }
        System.out.println(sb);
    }

    private static int isPalindrome(int start, int end, int[] nums) {
        while(start<end){
            if(nums[start++]!=nums[end--])
                return 0;
        }
        return 1;
    }
}

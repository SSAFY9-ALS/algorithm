package yeri.algorithm0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A와 B 2 / 골드5/ 2시간
 * https://www.acmicpc.net/problem/12919
 */
public class BJ_12919_A와B2 {
//    static String T;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();



//        find(S, T);
        System.out.println(find(S, T));
    }

    private static int find(String s, String t) {
        if(t.length()<s.length())
            return 0;
        if(s.equals(t))
            return 1;
        if(t.charAt(0)=='B'){
            StringBuilder sb = new StringBuilder(t);
            sb.reverse().setLength(t.length()-1);
            if (find(s,sb.toString())==1){
                return 1;
            }
        }
        StringBuilder sb = new StringBuilder(t);
        if(t.charAt(t.length()-1)=='B')
            return 0;
        sb.setLength(t.length()-1);
        if(find(s,sb.toString())==1){
            return 1;
        }
        return 0;
    }

//    private static void dfs(String s) {
//        if (result==1 || s.length() == T.length()) {
//            return;
//        }
//        String tempA = s+"A";
//        String tempB = plusB(s);
//        if (tempB.equals(T) || tempA.equals(T)) {
//            System.out.println(1);
//            System.exit(0);
//        }
//        dfs(tempA);
//        dfs(tempB);
//    }
//
//    private static String plusA(String s) {
//        return s + "A";
//    }
//
//    private static String plusB(String s) {
//        StringBuilder sb = new StringBuilder(s);
//        return sb.append("B").reverse().toString();
//    }

}

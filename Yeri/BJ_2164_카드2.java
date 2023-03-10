package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 카드2 / 실버4 / 20분
 * https://www.acmicpc.net/problem/2164
 */
public class BJ_2164_카드2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        List<Integer> arr = new ArrayList<>();
        int size = n;
        if(n!=1) {
            for (int i = 2; i <= n; i += 2) {
                arr.add(i);
            }
            if(size%2!=0){
                int temp = arr.get(0);
                arr.remove(0);
                arr.add(temp);
            }
            size = arr.size();      //초기화

            while (size > 1) {
                if(size%2!=0){
                    for (int i = size-1; i >=0; i -= 2) {
                        arr.remove(i);
                    }
                    int temp = arr.get(0);
                    arr.remove(0);
                    arr.add(temp);
                }
                else {
                    for (int i = size-2; i >=0; i -= 2) {
                        arr.remove(i);
                    }
                }
                size = arr.size();
            }
            System.out.println(arr.get(0));
        }else {
            System.out.println('1');
        }

    }
}

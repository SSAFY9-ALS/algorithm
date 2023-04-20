package again;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 공항 / 골드 2 / 2시간 / 4월 16일
 */
public class BJ_10775_공항 {
    static int G;
    static int[] parents;
    static void makeSet() {
        for (int i = 1; i <= G; i++) {
            parents[i] = i;
        }
    }
    static int findSet(int a) {
        if(a == parents[a]) return a;
        return parents[a] = findSet(parents[a]);
    }
    static void union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if(aRoot != bRoot) parents[bRoot] = aRoot;
    }
    public static void main(String[] args)  throws  Exception{
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 게이트의 수 : G
        G = Integer.parseInt(br.readLine());
        // 비행기의 수 : P
        int P = Integer.parseInt(br.readLine());
        int result = 0;
        parents = new int[G+1];
        makeSet();

        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine());

            int set = findSet(g);
            // 만약 해당 자리가 가리키는 곳이 0이라고 한다면 1부터 해당 자리(g)까지가 가득 찼으므로 그대로 입력을 끝낸다.
            if (set == 0) break;

            result++;

            // 해당 자리는 꽉 찼다는 표시로 부모의 한 자리 아래 수와 합친다.
            union(set - 1, set);
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}

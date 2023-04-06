package march;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 줄 세우기 / 골드3 / 2시간
 */
public class BOJ_2252 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer((in.readLine()));
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] students = new int[n];
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        int start, end;
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            start = Integer.parseInt(st.nextToken()) - 1;
            end = Integer.parseInt(st.nextToken()) - 1;
            students[end]++;
            if(map.containsKey(start))
                map.get(start).add(end);
            else {
                map.put(start, new HashSet<>());
                map.get(start).add(end);
            }
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            if(students[i] == 0)
                queue.offer(i);
        }
        StringBuilder sb = new StringBuilder();
        int student;
        while(!queue.isEmpty()) {
            student = queue.poll();
            sb.append((student+1) + " ");
            if(map.get(student) != null) {
                for(Integer conn: map.get(student)) {
                    students[conn]--;
                    if(students[conn] == 0)
                        queue.offer(conn);
                }
            }
        }
        System.out.println(sb);
    }
}

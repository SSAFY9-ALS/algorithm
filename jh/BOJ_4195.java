package march;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class BOJ_4195 {
    static HashMap<String, String> people; // 사람, 그 사람의 부모
    static HashSet<String> set;

    static String findRoot(String a) {
        set.add(a);
        if(a == people.get(a))
            return a;
        String p = findRoot(people.get(a));
//        people.put(a, p);
        return p;
    }

    static void union(String a, String b) {
        String aRoot = findRoot(a);
        String bRoot = findRoot(b);
//        System.out.println(aRoot + " " + bRoot);
        if(!aRoot.equals(bRoot)) {
            people.put(bRoot, aRoot);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(in.readLine());
        int f;
        for(int t = 0; t < tc; t++) {
            f = Integer.parseInt(in.readLine());
            people = new HashMap<>();
            set = new HashSet<>();
            for(int i = 0; i < f; i++) {
                String[] sen = in.readLine().split(" ");
                set.clear();
                if(!people.containsKey(sen[0]))
                    people.put(sen[0], sen[0]);
                if(!people.containsKey(sen[1]))
                    people.put(sen[1], sen[0]);
                union(sen[0], sen[1]);
//                sb.append(total + "\n");
                System.out.println(set.toString());

            }
        }
        System.out.println(sb);
    }
}
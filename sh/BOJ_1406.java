import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        LinkedList<Stack<Character>> Nodes = new LinkedList<>();
        String str = br.readLine();

        Nodes.add(new Stack<>());
        Nodes.add(new Stack<>());
        for(int i = 0; i < str.length(); i++) { //str 집어넣기
            Nodes.get(0).push(str.charAt(i));
        }

        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            char cmd = st.nextToken().charAt(0);

            if(cmd == 'L') {
                if(!Nodes.get(0).empty()) { // 커서가 문장의 맨 앞이 아닐 때
                    Nodes.get(1).push(Nodes.get(0).pop());
                }
            } else if(cmd == 'D') {
                if(!Nodes.get(1).empty()) { // 커서가 문장의 맨 끝이 아닐 때
                    Nodes.get(0).push(Nodes.get(1).pop());
                }
            } else if(cmd == 'B') { // 커서 왼쪽에 있는 문자 삭제
                if(!Nodes.get(0).empty()) {
                    Nodes.get(0).pop();
                }
            } else if(cmd == 'P') {
                char n = st.nextToken().charAt(0);
                Nodes.get(0).push(n);
            }
        }
        StringBuilder result = new StringBuilder();

        for (Character node : Nodes.get(0)) {
            result.append(node);
        }
        while(!Nodes.get(1).empty()) {
            result.append(Nodes.get(1).pop());
        }
        System.out.println(result);
    }
}

/**
 * @title : 에디터 / 실버 2 / 40분
 * @link : https://www.acmicpc.net/problem/1406
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private final List<Integer> Node = new LinkedList<>();

    public static void main(String[] args) throws NumberFormatException, IOException {
        Main m1 = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            if(cmd.equals("push")) {
                int param = Integer.parseInt(st.nextToken());
                m1.push(param);
            } else if(cmd.equals("pop")) {
                System.out.println(m1.pop());
            } else if(cmd.equals("size")) {
                System.out.println(m1.size());
            } else if(cmd.equals("empty")) {
                System.out.println(m1.empty());
            } else if(cmd.equals("top")) {
                System.out.println(m1.top());
            }
        }
    }

    public void push(int X) {
        Node.add(X);
    }

    public int pop() {
        int res;
        if(!Node.isEmpty()) {
            res = Node.get(Node.size()-1);
            Node.remove(Node.size()-1);
            return res;
        } return -1;
    }

    public int size() {
        return Node.size();
    }

    public int empty() {
        if(Node.size() > 0) {
            return 0;
        }
        return 1;
    }

    public int top() {
        if(!Node.isEmpty()) {
            return Node.get(Node.size()-1);
        }
        return -1;
    }
}

/**
 * @title : 스택 / 실버 4 / 10분
 * @link : https://www.acmicpc.net/problem/10828
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 replace를 사용해서 같은 문자열이 있으면 제거하는 방법 사용 -> 문자열의 길이로 인하 메모리 초과
*/
public class BOJ_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String part = br.readLine();
        int partSize = part.length();

        Stack<Character> st = new Stack<>();

        for(int i=0; i<line.length(); i++) {
            // 스택에 문자열의 문자 하나하나 넣으면서 탐색
            st.push(line.charAt(i));

            // 폭발 문자열과 길이가 같아지면 탐색 시작
            if(st.size()>= partSize) {
                boolean flag = true;

                // st.size - partSize(폭발문자열크기) ~ st.size까지 보면서 같은 문자열이면 삭제
                for(int j=0; j<partSize; j++) {
                    // 같은 문자열이 아닐 때
                    if(st.get(st.size()-partSize+j) != part.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                // 같은 문자열이면 -> flag = true 폭발 문자열 크기만큼 pop
                if(flag) {
                    for(int j=0; j<partSize; j++) {
                        st.pop();
                    }
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        for(Character c : st) {
            sb.append(c);
        }
        System.out.println(sb.length()==0? "FRULA" : sb.toString());
    }
}

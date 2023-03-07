/**
 *  야구 / 골드 4 /120분
 *  https://www.acmicpc.net/problem/17281
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_17281 {

	static int N;
    static int[][] arr;
    static int[] hitorder;
    static boolean[] isSelected;
    static int outCount = 0;
    static int max=Integer.MIN_VALUE;
    
    private static void makeOrder(int cnt) {
        if(cnt == 9) {
            // 타순 완성
            // 점수 구하기
            play();
            return;
        }
         // 타순 구하기
        for(int i=1;i<9;i++) {
            if(!isSelected[i]) {
                hitorder[cnt] = i;
                isSelected[i] = true;
                if(cnt==2) makeOrder(cnt+2);
                else makeOrder(cnt+1);
                isSelected[i] = false;
            }
        }

    }
    private static void play() {
        int point = 0;
        int idx = 0; // 1번 타자 시작
        for(int i=0;i<N;i++) { // 이닝수만큼 반복
            int[] base = new int[3]; // 베이스  1루 2루 3루
            outCount=0; // 베이스 및 아웃카운트 초기화
            while(outCount<3) {
                int hitter = arr[i][hitorder[idx%9]]; // 타자가 침
                if(hitter==0) { // 아웃 하나 증가
                    outCount++;
                }
                else if(hitter==4) {// 홈런
                    for(int j=0;j<3;j++) {
                        if(base[j]!=0) point++; // 출루해있던 주자들 점수
                        base[j]=0;
                    }
                    point++; // 홈런친 타자 점수
                }
                else if(hitter==1) { // 안타
                    if(base[2]==1){ //3루에 주자 있으면 점수
                        point++;
                        base[2]=0;
                    }
                    if(base[1]==1) { // 2루 주자 3루로
                        base[2]=1;
                        base[1]=0;
                    }
                    if(base[0]==1) {// 1루 주자 2루로
                        base[1]=1;
                    }
                    base[0]=1; //1루 진출
                }
                else if(hitter==2) { // 2루타
                    if(base[2]==1) { // 3루주자 홈으로
                        point++;
                        base[2]=0;
                    }
                    if(base[1]==1) { // 2루주자 홈으로
                        point++;
                        base[1]=0;
                    }
                    if(base[0]==1) {// 1루주자 3루로
                        base[2]=1;
                        base[0]=0;
                    }
                    base[1]=1; // 2루 진출
                }
                else if(hitter==3) { // 3루타
                    for(int j=0;j<3;j++) {
                        if(base[j]==1) {
                            point++; // 123루 주자 점수
                            base[j]=0;
                        }
                    }
                    base[2]=1; // 3루 진출
                }
                idx++;
            }
        }
        max = Math.max(max, point);
    }

public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    N = parseInt(br.readLine());
    arr = new int[N][9];
    
    for(int i=0;i<N;i++) {
        String[] tmp = br.readLine().split(" ");
        for(int j=0;j<9;j++) {
            arr[i][j] = parseInt(tmp[j]);
        }
    }
    
    
    hitorder = new int[9];
    isSelected = new boolean[9];
    isSelected[0]=true; // 1번 선수는 무조건 4번 타자로 정해짐
    // 순서 구하고 점수계산
    
    makeOrder(0);
    
    System.out.println(max);
}
}

package swA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 구슬탈출2 / 난이도 / 100분
 * https://www.acmicpc.net/problem/13460
 */
class Pos {
    int r;
    int c;
    int count;

    public Pos(int r, int c, int count) {
        this.r = r;
        this.c = c;
        this.count = count;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public int getCount() {
        return count;
    }
}

public class BJ_13460_구슬탈출2 {
    static char[][] board;
    /* R,B의 위치 */
    static int[] currR = new int[2];
    static int[] currB = new int[2];
    static int N;
    /* 방향 */
    static int[][] dr = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        board = new char[N][M];     //보드 초기화

        /* 보드 상태, R,B의 위치 입력받기 */
        for (int r = 0; r < N; r++) {
            String line = br.readLine();
            for (int c = 0; c < M; c++) {
                board[r][c] = line.charAt(c);
                if (board[r][c] == 'R') {
                    currR[0] = r;
                    currR[1] = c;
                    board[r][c] = '.';
                }
                if (board[r][c] == 'B') {
                    currB[0] = r;
                    currB[1] = c;
                    board[r][c] = '.';
                }
            }
        }

        int result = bfs(currR, currB);

        System.out.println(result);
    }

    /**
     * 이동할수있는지
     *
     * @param d 방향
     * @return t f
     */
    private static boolean canMove(int[] currR, int[] d) {
        int nr = currR[0] + d[0];
        int nc = currR[1] + d[1];
        if (board[nr][nc] == '.' || board[nr][nc] == 'O') { //이동하려는 방향으로 이동 가능함
            return true;
        }
        return false;
    }

    private static int[] move(int[] curr, int[] d) {
        int nr = curr[0] + d[0];
        int nc = curr[1] + d[1];
        int[] newCurr = new int[2];
        while (true) {
            if (board[nr][nc] == '.') {
                nr += d[0];
                nc += d[1];
            } else if (board[nr][nc] == 'O') {
                newCurr[0] = nr;
                newCurr[1] = nc;
                return newCurr;
            } else {
                newCurr[0] = nr - d[0];
                newCurr[1] = nc - d[1];
                return newCurr;
            }
        }
    }

    private static int bfs(int[] currR, int[] currB) {
        Queue<Pos> redQue = new LinkedList<>();
        Queue<Pos> blueQue = new LinkedList<>();
        redQue.offer(new Pos(currR[0], currR[1], 0));
        blueQue.offer(new Pos(currB[0], currB[1], 0));
        int count = 0;
        while (!redQue.isEmpty()) {
            Pos red = redQue.poll();
            Pos blue = blueQue.poll();
            if (red.getCount() > 10) {
                return -1;
            }
            if (board[red.getR()][red.getC()] == 'O') {
                return red.getCount();
            }
            for (int[] d : dr) {
                boolean redFlag = false;
                boolean blueFlag = false;

                int[] redCurr = {red.getR(), red.getC()};
                if (canMove(redCurr, d)) { //빨강 이동가능
                    redCurr = move(redCurr, d);
                    redFlag = true;
                }
                int[] blueCurr = {blue.getR(), blue.getC()};
                if (canMove(blueCurr, d)) {
                    blueCurr = move(blueCurr, d);
                    blueFlag = true;
                }
                if (redCurr[0] == blueCurr[0] && redCurr[1] == blueCurr[1]) {
                    if (board[redCurr[0]][redCurr[1]] == 'O') {
                        continue;
                    }
                    int redDist = getDist(red, redCurr);
                    if (redDist < getDist(blue, blueCurr)) {
                        redQue.offer(new Pos(redCurr[0], redCurr[1], red.getCount() + 1));
                        blueQue.offer(new Pos(blueCurr[0] - d[0], blueCurr[1] - d[1], blue.getCount() + 1));
                        continue;
                    } else {
                        redQue.offer(new Pos(redCurr[0] - d[0], redCurr[1] - d[1], red.getCount() + 1));
                        blueQue.offer(new Pos(blueCurr[0], blueCurr[1], blue.getCount() + 1));
                        continue;
                    }
                }
                if (board[blueCurr[0]][blueCurr[1]] == 'O') {
                    continue;
                }

                if (redFlag || blueFlag) {
                    redQue.offer(new Pos(redCurr[0], redCurr[1], red.getCount() + 1));
                    blueQue.offer(new Pos(blueCurr[0], blueCurr[1], blue.getCount() + 1));
                }
            }
        }
        return count;
    }

    private static int getDist(Pos pos, int[] curr) {
        return Math.abs(pos.getR() - curr[0]) + Math.abs(pos.getC() - curr[1]);
    }
}





/*시간초과*/

//public class BJ_13460_구슬탈출2 {
//    static char[][] board;
//    static char[][] originBoard;
//    /* R,B의 위치 */
//    static int[] currR = new int[2];
//    static int[] currB = new int[2];
//    /* 방향 */
//    static int[][] dr = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
//    static int min = Integer.MAX_VALUE ;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        board = new char[N][M];     //보드 초기화
//        originBoard = new char[N][M];     //보드 초기화
//
//        /* 보드 상태, R,B의 위치 입력받기 */
//        for (int r = 0; r < N; r++) {
//            String line = br.readLine();
//            for (int c = 0; c < M; c++) {
//                originBoard[r][c] = line.charAt(c);
//                if (originBoard[r][c] == 'R') {
//                    currR[0] = r;
//                    currR[1] = c;
//                }
//                if (originBoard[r][c] == 'B') {
//                    currB[0] = r;
//                    currB[1] = c;
//                }
//            }
//        }
//
//        /* 이동? */
//        for(int i = 0; i < originBoard.length; i++) {
//            board[i] = originBoard[i].clone();
//        }
//        int result=dfs(currR, currB, 0, 0);
//
//        for(int i = 0; i < originBoard.length; i++) {
//            board[i] = originBoard[i].clone();
//        }
//        result = Math.min(result, dfs(currR, currB, 1, 0));
//
//        for(int i = 0; i < originBoard.length; i++) {
//            board[i] = originBoard[i].clone();
//        }
//        result = Math.min(result, dfs(currR, currB, 2, 0));
//
//        for(int i = 0; i < originBoard.length; i++) {
//            board[i] = originBoard[i].clone();
//        }
//        result = Math.min(result, dfs(currR, currB, 3, 0));
//
//        System.out.println(result == 11 ? -1 : result);
//    }
//
//    /**
//     * 탐색
//     * @param d     : 방향
//     * @param count : 이동횟수
//     */
//    private static int dfs(int[] currR, int[] currB, int d, int count) {
//        if (count >= 10 || min <= count) {
//            return 11;
//        }
//
//        boolean flag = true;
//        int tmp = 11;
////        int newD = (d+1)%4;     //좌우
//        int newD = d;
//        int moveCase =  canMove(currR, dr[newD]);
//        if ( moveCase== 1) {
//            int[] newR = move(currR, dr[newD]);
//            if(board[newR[0]][newR[1]]=='O'){
//                flag = false;
////                return count+1;
//            }
//            if(flag){board[newR[0]][newR[1]] = 'R';
//            }
//            board[currR[0]][currR[1]] = '.';
//            int[] newB = move(currB, dr[newD]);
//            if(board[newB[0]][newB[1]]=='O'){
//                return 11;
//            }
//            board[newB[0]][newB[1]] = 'B';
//            board[currB[0]][currB[1]] = '.';
//            if(!flag){
//                return count+1;
//            }
//            tmp = Math.min(dfs(newR, newB, (newD+1)%4, count + 1), dfs(newR, newB, (newD+3)%4, count + 1));
//        } else if (moveCase==0) {
//            int[] newB = move(currB, dr[newD]);
//            if(board[newB[0]][newB[1]]=='O'){
//                return 11;
//            }
//            board[newB[0]][newB[1]] = 'B';
//            board[currB[0]][currB[1]] = '.';
//            if(!flag){
//                return count+1;
//            }
//            int[] newR = move(currR, dr[newD]);
//            if(board[newR[0]][newR[1]]=='O'){
//                flag = false;
////                return count+1;
//            }
//            if(flag){board[newR[0]][newR[1]] = 'R';
//            }
//            board[currR[0]][currR[1]] = '.';
//        }
////        newD = (newD+2)%4;
////        if (canMove(currR, dr[newD]) == 1) {
////            int[] newR = move(currR, dr[newD]);
////            if(board[newR[0]][newR[1]]=='O'){
////                flag = false;
//////                return count+1;
////            }
////            if(flag){board[newR[0]][newR[1]] = 'R';
////            }
////            board[currR[0]][currR[1]] = '.';
////            int[] newB = move(currB, dr[newD]);
////            if(board[newB[0]][newB[1]]=='O'){
////                return 11;
////            }
////            board[newB[0]][newB[1]] = 'B';
////            board[currB[0]][currB[1]] = '.';
////            if(!flag){
////                return count+1;
////            }
////            tmp = Math.min(dfs(newR, newB, newD, count + 1), tmp);
////        }
//        return tmp;
//    }
//
//    private static int[] move(int[] curr, int[] d) {
//        int nr = curr[0] + d[0];
//        int nc = curr[1] + d[1];
//        int [] newCurr = new int[2];
//        while (true) {
//            if (board[nr][nc] == '.'){
//                nr += d[0];
//                nc += d[1];
//            }else  if (board[nr][nc] == 'O'){
//                newCurr[0] = nr;
//                newCurr[1] = nc;
//                return newCurr;
//            }else{
//                newCurr[0] = nr-d[0];
//                newCurr[1] = nc-d[1];
//                return newCurr;
//            }
//        }
//    }
//
//    /**
//     * 이동할수있는지
//     *
//     * @param d 방향
//     * @return 1: 빨강 먼저 이동, 0: 파랑먼저 이동, -1 : 이동불가
//     */
//    private static int canMove(int []currR, int[] d) {
//        int nr = currR[0] + d[0];
//        int nc = currR[1] + d[1];
//        int state = 1;
//        while (board[nr][nc] != '#') {
//            if (board[nr][nc] == '.'||board[nr][nc] == 'O')  //이동하려는 방향으로 이동 가능함
//                return state;
//            else if (board[nr][nc] == 'B') {
//                state = 0;          //B가 먼저 이동해야함
//                nr += d[0];         //B뒤에 이동 가능한지 확인
//                nc += d[1];
//            }
//        }
//        return -1;
//    }
//
//
//}


/*
5 5
#####
#..B#
#.#.#
#RO.#
#####

1


7 7
#######
#...RB#
#.#####
#.....#
#####.#
#O....#
#######

5


7 7
#######
#..R#B#
#.#####
#.....#
#####.#
#O....#
#######

5


10 10
##########
#R#...##B#
#...#.##.#
#####.##.#
#......#.#
#.######.#
#.#....#.#
#.#.#.#..#
#...#.O#.#
##########

-1




 */

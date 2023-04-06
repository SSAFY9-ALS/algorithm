/**
 * 보석 도둑 / 골드 2 / 90분 
 * https://www.acmicpc.net/problem/1202
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_1202 {
    
    static int N,K;
    static int[] bag;
    static PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
    static ArrayList<Node> jewel = new ArrayList<>();
    static long answer;
    
    static public class Node implements Comparable<Node> {
        int weight;
        int price;
        public Node(int weight, int price) {
            super();
            this.weight = weight;
            this.price = price;
        }
        @Override
        public int compareTo(Node o) {
            // TODO Auto-generated method stub
//        	if(this.price == o.price) {
//        		return this.weight - o.weight;
//        	}
//        	return o.price - this.price;
            if(this.weight==o.weight) {
                return o.price - this.price;
            }
            return this.weight - o.weight; // 오름차순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] tmp = br.readLine().split(" ");
        N = parseInt(tmp[0]);
        K = parseInt(tmp[1]);
        
        bag = new int[K];
        
        for(int i=0;i<N;i++) {
            tmp = br.readLine().split(" ");
            jewel.add(new Node(parseInt(tmp[0]),parseInt(tmp[1])));
        }
        for(int i=0;i<K;i++) {
            bag[i] = parseInt(br.readLine());
        }
        
        // 가방 정렬
        Arrays.sort(bag);
        Collections.sort(jewel);

        for(int i=0,j=0;i<K;i++) {
            while(j<N && jewel.get(j).weight<=bag[i]) {
                queue.offer(jewel.get(j++).price);
                
            }
            if(!queue.isEmpty()) {
                answer += queue.poll();
            }
        }
        
        System.out.println(answer);
    }

}
package kr.co.programmers;

import java.io.*;
import java.util.*;

/**
 * 메뉴 리뉴얼 / 105분
 * @author 민정인
 * https://school.programmers.co.kr/learn/courses/30/lessons/72411?language=java
 */

class Solution {
    static Map<String, Integer> map = new HashMap<>();
    static ArrayList<String> result = new ArrayList<>();
    static class Pair implements Comparable<Pair> {
        String s;
        int cnt;
        public Pair(String s, int cnt){
            this.s = s;
            this.cnt = cnt;
        }
        
        public int compareTo(Pair o){
            return o.cnt - this.cnt;
        }
    }
    public String[] solution(String[] orders, int[] course) {
        int oLen = orders.length;
        int cLen = course.length;
        for(int i = 0; i < oLen; i++){
            char[] tmp = orders[i].toCharArray();
            int len = orders[i].length();
            Arrays.sort(tmp);
            orders[i] = "";
            for(int j = 0; j < len; j++){
                orders[i] += tmp[j];
            }
        }
        // ArrayList<Character>[] list = new ArrayList[oLen];
        // for(int i = 0; i < oLen; i++){
        //     list[i] = new ArrayList<>();
        //     for(int j = 0; j < orders[i].length(); j++){
        //         list[i].add(orders[i].charAt(j));
        //     }
        // }
        for(int i = 0; i < cLen; i++){
            for(int j = 0; j < oLen; j++){
                comb("", course[i], 0, orders[j]);
            }
            // System.out.println(map);
            if(!map.isEmpty()){
                ArrayList<Pair> list = new ArrayList<>();
                for(Map.Entry<String, Integer> entry : map.entrySet()){
                    list.add(new Pair(entry.getKey(), entry.getValue()));
                    // System.out.println(entry.getKey() + " " + entry.getValue());
                }
                Collections.sort(list);
                int cur = list.get(0).cnt;
                if(cur >= 2){
                    result.add(list.get(0).s);
                    for(int j = 1; j < list.size(); j++){
                        if(list.get(j).cnt != cur){
                            break;
                        }
                        result.add(list.get(j).s);
                    }
                }
                map.clear();
            }
            
        }
        String[] answer = new String[result.size()];
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }
    static void comb(String s, int cnt, int idx, String origin){
        if(s.length() == cnt){
            if(map.get(s) != null){
                map.put(s, map.get(s) + 1);
            } else{
                map.put(s, 1);
            }
            return;
        }
        for(int i = idx; i < origin.length(); i++){
            comb(s + origin.charAt(i), cnt, i + 1, origin);
        }
    }
}
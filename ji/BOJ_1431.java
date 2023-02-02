package net.acmicpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/** 79 */
public class BOJ_1431 {	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<String> ar = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			ar.add(sc.next());
		}
		Collections.sort(ar, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				if(a.length() != b.length()) {
					return a.length() - b.length();
				} else {
					int size = a.length();
					int aVal = 0;
					int bVal = 0;
					for(int i = 0; i < size; i++) {
						if(Character.isDigit(a.charAt(i))) {
							aVal += (int) a.charAt(i) - '0';
						}
						if(Character.isDigit(b.charAt(i))) {
							bVal += (int) b.charAt(i) - '0';
						}
					}
					if(aVal != bVal) {
						return aVal - bVal;
					}
					return a.compareTo(b);
				}
			}
		});
		for(int i = 0; i < ar.size(); i++) {
			System.out.println(ar.get(i));
		}
	}
}

// https://www.acmicpc.net/problem/1431
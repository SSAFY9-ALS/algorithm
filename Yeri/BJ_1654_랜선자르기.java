package yeri.algorithm0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 랜선자르기 / 실버2 / 40분
 */
public class BJ_1654_랜선자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		ArrayList<Integer> arr = new ArrayList<>();

		long sum = 0;
		for (int i = 0; i < n; i++) {
			int l = Integer.parseInt(br.readLine());
			sum += l;
			arr.add(l);
		}

		Collections.sort(arr);

		long start = 1, end = arr.get(arr.size() - 1);
		long cm = 1;
		int result = 0;

		while (start <= end) {
			int count = 0;
			cm = (start + end) / 2;

			for (int i = 0; cm != 0 && i < arr.size(); i++) {
				count += arr.get(i) / cm;
			}
			if (count < k)
				end = cm - 1;
			else {
				result = (int) cm;
				start = cm + 1;
			}
		}

		System.out.println(result);

	}

}

package gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_12886_돌그룹 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] group = new int[3];
		for(int i = 0; i < 3; i++) {
			group[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] overlap = new int[1001];
		Arrays.sort(group);
		ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
		queue.add(group);
		boolean flag = false;
		int k = 0;
		ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>();
		list.add(new HashSet<Integer>());
		while(!queue.isEmpty()) {
			int[] arr = queue.poll();
			if(arr[0] == arr[1] && arr[1] == arr[2]) {
				flag = true;
				break;
			}
			for(int i = 0; i < 2; i++) {
				for(int j = i+1; j < 3; j++) {
					int a = arr[j] - arr[i];
					int b = arr[(3-i-j)];
					int c = arr[i] + arr[i];
					if(a == b && b == c) {
						flag = true;
						break;
					}
					if(a == b || b == c || a == c) continue;
					int[] n_arr = new int[] {a, b, c};
					Arrays.sort(n_arr);
					if(overlap[n_arr[0]] == 0) {
						overlap[n_arr[0]] = ++k;
						list.add(new HashSet<Integer>());
					}
					if(list.get(overlap[n_arr[0]]).contains(n_arr[1])) continue;
					list.get(overlap[n_arr[0]]).add(n_arr[1]);
					queue.add(n_arr);
				}
				if(flag) break;
			}
			if(flag) break;
		}
		System.out.println(flag?1:0);
	}
}

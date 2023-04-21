package platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_2887_행성터널 {
	
	static ArrayList<int[]> list;
	static int[] parent;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		parent = new int[N];
		for(int i = 0; i < N; i++) parent[i] = i;
		
		list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < i; j++) {
				if(i == j) continue;
				int[] o = list.get(j);
				min = Math.min(min, Math.min(Math.abs(x-o[0]), Math.min(Math.abs(y-o[1]), Math.abs(z-o[2]))));
			}
			
		}
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px < py) parent[py] = px;
		else parent[px] = py;
	}
	
}

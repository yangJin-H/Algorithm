package platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2887_행성터널 {
	
	static class Planet {
		int index, x, y, z, parent;
		int[] cost;

		public Planet(int index, int x, int y, int z) {
			this.index = index;
			this.x = x;
			this.y = y;
			this.z = z;
			this.parent = index;
			this.cost = new int[N-index-1];
		}
		
		public void setCost(Planet o) {
			int min = Math.min(Math.min(Math.abs(this.x - o.x), Math.abs(this.y - o.y)), Math.abs(this.z - o.z));
			this.cost[o.index-this.index-1] = min;
			if(min == 0) union(this.index, o.index);
		}
	}
	
	static int N, unioned, cost;
	static Planet[] planets;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		planets = new Planet[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			planets[i] = new Planet(i, x, y, z);
		}
		
		unioned = 1;
		for(int i = 0; i < N-1; i++) {
			for(int j = i+1; j < N; j++) {
				planets[i].setCost(planets[j]);
			}
		}
		
		while(unioned < N) {
			int min = Integer.MAX_VALUE;
			int from = -1;
			int to = -1;
			for(int i = 0; i < N-1; i++) {
				for(int j = i+1; j < N; j++) {
					if(planets[i].cost[j-i-1] >= min || find(i) == find(j)) continue;
					min = planets[i].cost[j-i-1];
					from = i;
					to = j;
				}
			}
			cost += min;
			union(from, to);
		}
		
		System.out.println(cost);
	}
	
	static int find(int x) {
		if(planets[x].parent == x) return x;
		else return planets[x].parent = find(planets[x].parent);
	}
	
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px < py) planets[py].parent = px;
		else planets[px].parent = py;
		unioned++;
	}
}

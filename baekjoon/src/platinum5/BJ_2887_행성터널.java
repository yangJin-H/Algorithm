package platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2887_행성터널 {
	
	static class Planet implements Comparable<Planet> {
		int index, p; // p : position
		
		public Planet(int index, int p) {
			this.index = index;
			this.p = p;
		}

		@Override
		public int compareTo(Planet o) {
			return this.p - o.p;
		}
	}

	static class Edge implements Comparable<Edge> {
		int u, v, cost;

		public Edge(int u, int v, int cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	
	static int N, total, unioned;
	static Planet[][] planets;
	static Edge[][] edges;
	static int[] parent;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		planets = new Planet[3][N];
		parent = new int[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(Planet[] planet : planets) {
				planet[i] = new Planet(i, Integer.parseInt(st.nextToken()));
			}
			parent[i] = i;
		}
		
		for(Planet[] planet : planets) {
			Arrays.sort(planet);
		}
		
		edges = new Edge[3][N-1];
		for(int i = 0; i < 3; i++) {
			edges[i] = new Edge[N-1];
		}
		for(int i = 0; i < N-1; i++) {
			for(int j = 0; j < 3; j++) {
				Planet[] planet = planets[j];
				Edge[] edge = edges[j];
				int cost = planet[i+1].p - planet[i].p;
				int u = Math.min(planet[i].index, planet[i+1].index);
				int v = Math.max(planet[i].index, planet[i+1].index);
				edge[i] = new Edge(u, v, cost);
			}
		}
		
		for(Edge[] edge : edges) {
			Arrays.sort(edge);
		}
		unioned = 1;
		int[] idxs = new int[3];
		while(unioned < N) {
			int k = -1;
			int min = Integer.MAX_VALUE;
			for(int i = 0; i < 3; i++) {
				if(idxs[i] == N-1) continue;
				while(find(edges[i][idxs[i]].u) == find(edges[i][idxs[i]].v) && idxs[i] < N-1) idxs[i]++;
				if(min > edges[i][idxs[i]].cost) {
					min = edges[i][idxs[i]].cost;
					k = i;
				}
			}
			
			total += edges[k][idxs[k]].cost;
			union(edges[k][idxs[k]].u, edges[k][idxs[k]].v);
		}
		
		System.out.println(total);
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
		unioned++;
	}
}

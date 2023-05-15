package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1738_골목길 {
	
	static class Edge implements Comparable<Edge> {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.v - o.v;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Edge>> edgelist = new ArrayList<>();
		for(int i = 0; i <= N; i++) edgelist.add(new ArrayList<Edge>());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			Edge edge = new Edge(v, w);
			int index = edgelist.get(u).indexOf(edge);
			if(index != -1) {
				if(edgelist.get(u).get(index).w < w) {
					edgelist.get(u).get(index).w = w;
				}
				continue;
			}
			edgelist.get(u).add(edge);
		}
		
		int[] parent = new int[N+1];
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MIN_VALUE/2);
		distance[1] = 0;
		parent[1] = 1;
		
		for(int i = 1; i < N; i++) {
			for(int u = 1; u <= N; u++) {
				for(Edge edge : edgelist.get(u)) {
					if(distance[u] + edge.w > distance[edge.v]) {
						distance[edge.v] = distance[u] + edge.w; 
						parent[edge.v] = u;
					}
				}
			}
		}
		
		int[] cycle = distance.clone();
		for(int u = 1; u <= N; u++) {
			for(Edge edge : edgelist.get(u)) {
				if(cycle[u] + edge.w > cycle[edge.v]) {
					cycle[edge.v] = cycle[u] + edge.w; 
					parent[edge.v] = u;
				}
			}
		}
		
		boolean loop = false;
		for(int i = 1; i <= N; i++) {
			if(distance[i] < cycle[i]) {
				distance[i] = Integer.MAX_VALUE/2;
				loop = true;
				break;
			}
		}
		
		if(loop) {
			cycle = distance.clone();
			for(int i = 0; i < N; i++) {
				for(int u = 1; u <= N; u++) {
					for(Edge edge : edgelist.get(u)) {
						if(cycle[u] + edge.w > cycle[edge.v]) {
							cycle[edge.v] = cycle[u] + edge.w; 
							parent[edge.v] = u;
						}
					}
				}
			}
			
			if(cycle[N] > distance[N]) {
				System.out.println(-1);
				return;
			}
		}
		
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		int k = N;
		stack.add(k);
		while(k != 1) {
			stack.push(parent[k]);
			k = parent[k];
		}
		
		while(!stack.isEmpty()) {
			int next = stack.pop();
			sb.append(next).append(" ");
		}
		System.out.println(sb);
	}
}

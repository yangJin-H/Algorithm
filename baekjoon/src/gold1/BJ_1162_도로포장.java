package gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1162_도로포장 {
	
	static class Node implements Comparable<Node> {
		int index, k;
		long dist;
		
		public Node(int index, long dist, int k) {
			this.index = index;
			this.dist = dist;
			this.k = k;
		}

		@Override
		public int compareTo(Node o) {
			if(this.dist > o.dist) return 1;
			else if(this.dist < o.dist) return -1;
			else return 0;
		}

		@Override
		public String toString() {
			return "Node [index=" + index + ", dist=" + dist + ", k=" + k + "]";
		}
	}
	
	static int N, M, K;
	static ArrayList<ArrayList<Node>> graph;
	static long[][] distance;
	
	static final long INF = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(new Node(v, d, 0));
			graph.get(v).add(new Node(u, d, 0));
		}
		
		distance = new long[N+1][K+1];;
		for(int i = 1; i <= N; i++) {
			for(int k = 0; k <= K; k++) {
				distance[i][k] = INF;
			}
		}
		
		dijkstra(1);
		
		long ans = INF;
		for(long d : distance[N]) {
			ans = ans < d ? ans : d;
		}
		
		System.out.println(ans);
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		distance[start][0] = 0;
		queue.offer(new Node(start, 0, 0));
		
		while(!queue.isEmpty()) {
//			System.out.println(queue);
			Node node = queue.poll();
			int idx = node.index;
			long dist = node.dist;
			int k = node.k;
			
			if(distance[idx][k] < dist) continue;
			
			for(int i = 0; i < graph.get(idx).size(); i++) {
				int nidx = graph.get(idx).get(i).index;
				long ndist = dist + graph.get(idx).get(i).dist;
				
				if(distance[nidx][k] > ndist) {
					distance[nidx][k] = ndist;
					queue.add(new Node(nidx, ndist, k));
				}
				if(k < K && distance[nidx][k+1] > dist) {
					distance[nidx][k+1] = dist;
					queue.add(new Node(nidx, dist, k+1));
				}
			}
		}
	}
}

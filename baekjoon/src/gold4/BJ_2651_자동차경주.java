package gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_2651_자동차경주 {
	public static class Car implements Comparable<Car> {
		ArrayList<Integer> repaired_num;
		int left_dist;
		int used_time;
		
		public Car(int left_dist) {
			this.repaired_num = new ArrayList<Integer>();
			this.left_dist = left_dist;
			this.used_time = 0;
		}

		@Override
		public int compareTo(Car o) {
			return this.used_time - o.used_time;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int m_dist = Integer.parseInt(br.readLine());
		int r_num = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] r_dist = new int[r_num+1];
		for(int i = 0; i < r_dist.length; i++) {
			r_dist[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int[] r_time = new int[r_num];
		for(int i = 0; i < r_time.length; i++) {
			r_time[i] = Integer.parseInt(st.nextToken());
		}
		
		PriorityQueue<Car> queue = new PriorityQueue<Car>();
		queue.offer(new Car(m_dist));
		while(!queue.isEmpty()) {
			Car c = queue.poll();
			
		}
		
	}
}

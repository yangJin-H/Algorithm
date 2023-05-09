package gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BJ_2515_전시장 {
	
	static class Paint {
		int height, cost;

		public Paint(int height, int cost) {
			this.height = height;
			this.cost = cost;
		}
	}
	
	static Paint[] sorted;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		Paint[] paints = new Paint[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int height = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			paints[i] = new Paint(height, cost);
		}
		
		sorted = new Paint[N];
		mergesort(paints, 0, N-1);

		TreeSet<Paint> memo = new TreeSet<Paint>(new Comparator<Paint>() {
			@Override
			public int compare(Paint o1, Paint o2) {
				return o1.height - o2.height;
			}
		});

		int max = 0;
		for(int i = 0; i < N; i++) {
			Paint fp = memo.floor(paints[i]);
			if(fp == null) {
				paints[i].height += S;
				if(max < paints[i].cost) { 
					memo.add(paints[i]);
					max = paints[i].cost;
				}
			} else {
				paints[i].height += S;
				if(max < paints[i].cost + fp.cost) {
					paints[i].cost += fp.cost;
					max = paints[i].cost;
					memo.add(paints[i]);
				}
			}	
		}
		System.out.println(max);
	}

	private static void mergesort(Paint[] paints, int left, int right) {
		int mid;
		
		if(left < right) {
			mid = (left+right)/2;
			mergesort(paints, left, mid);
			mergesort(paints, mid+1, right);
			merge(paints, left, mid, right);
		}
	}

	private static void merge(Paint[] paints, int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int k = left;
		
		while(l <= mid && r <= right) {
			if(paints[l].height == paints[r].height) {
				if(paints[l].cost >= paints[r].cost) {
					sorted[k++] = paints[l++];
				} else {
					sorted[k++] = paints[r++];
				}
			}
			else if(paints[l].height <= paints[r].height) {
				sorted[k++] = paints[l++];
			} else {
				sorted[k++] = paints[r++];
			}
		}
		
		if(l > mid) {
			for(int i = r; i <= right; i++) {
				sorted[k++] = paints[i];
			}
		} else {
			for(int i = l; i <= mid; i++) {
				sorted[k++] = paints[i];
			}
		}
		
		for(int i = left; i <= right; i++) {
			paints[i] = sorted[i];
		}
	}
}

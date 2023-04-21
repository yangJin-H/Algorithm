package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * SWEA D4 5604. 구간 합
 * 
 * @author 양진형
 */
public class D4_5604_구간합_양진형 {
	
	static long[] counts; // 0~9, 0~99, 0~999, ... 의 각 숫자의 갯수 (ex: 0~9는 각 숫자가 1번씩 나오므로 counts[1] = 1)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		counts = new long[16]; // 자릿수별 각 숫자의 갯수 초기화
		counts[1] = 1;
		for(int i = 2; i <= 15; i++) {
			counts[i] = (long) Math.pow(10, i-1) + counts[i-1] * 10;
		}
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			long s = Long.parseLong(st.nextToken()); // 시작 번호
			s = s-1==-1?0:s-1; // 시작번호 -1
			long e = Long.parseLong(st.nextToken()); // 끝 번호
			
			long ans1 = calc(e, Long.toString(e).length() - 1, new long[10], Long.toString(e)); // 0~해당 숫자까지 구간합 계산
			long ans2 = calc(s, Long.toString(s).length() - 1, new long[10], Long.toString(s));
			sb.append("#").append(tc).append(" ").append(ans1 - ans2).append("\n"); // 출력
		}
		System.out.println(sb);
	}

	private static long calc(long n, int digit, long arr[], String s) {
		Stack<Integer> stack = new Stack<Integer>();
		makestack(n, stack); // 자릿수별로 스택으로 만들기
		while(!stack.isEmpty()) { // 가장 큰 자리수부터 차례대로 꺼내오기
			int k = stack.pop(); // 숫자
			for(int i = 0; i < k; i++) { // 뽑아온 숫자 전까지
				arr[i] += Math.pow(10, digit); // 해당 숫자는 10^digit만큼 출현함
				for(int j = 0; j <= 9; j++) { // 0~9까지 각 수가 counts[digit]만큼 출현함
					arr[j] += counts[digit];
				}
			}
			s = s.substring(1); // 뽑아온 숫자 제외하고
			if(s.length() >= 1) { // 남은 숫자가 있다면
				arr[k] += Long.valueOf(s)+1; // 현재 뽑아온 숫자는 남은 숫자+1만큼 출현함
			} else {
				arr[k]++; // 마지막 숫자라면 한번만 출현함
			}
			digit--; // 자리수 줄이기
		}
		long sum = 0; // 구간합
		// 해당 숫자 * 출현 횟수를 더해줌
		for(int i = 0; i <= 9; i++) sum += arr[i] * i;
		return sum; // 반환
	}

	private static void makestack(long n, Stack<Integer> stack) {
		while(n > 0) {
			stack.add((int) (n % 10)); // 1의자리부터 스택에 넣기
			n /= 10;
		}
	}
}

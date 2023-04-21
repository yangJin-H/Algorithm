package platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * BJ 1786. 찾기
 * 
 * @author 양진형
 * 23.04.04
 */
public class BJ_1786_찾기_양진형 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String T = br.readLine(); // 대상 문자열
		String P = br.readLine(); // 찾을 문자열
		
		int[] kmp = new int[P.length()]; // pi (부분 일치 테이블)
		// i:접미사 포인터, j:접두사 포인터
		for(int i = 1, j = 0; i < P.length(); i++) { // 부분일치 테이블 만들기
			// j가 0보다 크고 i와 j가  다른 경우 =>
			// 1. 접두사 포인터를 일치하던 문자열(접두사: 0번 ~ j-1번/ 접미사: i-j번 ~ i-1번)의 마지막 문자가 반복하던 접두사의 가장 끝(kmp[j-1])으로 보낸다.
			// 2. 그 위치를 접두사 포인터로 하여 접미사 포인터와 일치하는지 while문을 돌면서 비교 (일치하거나 j=0이 될때까지)
			while(j > 0 && P.charAt(i) != P.charAt(j)) j = kmp[j-1];

			// 같은 경우 접두사 포인터+1하고 그 값을 부분일치 포인터에 저장
			if(P.charAt(i) == P.charAt(j)) kmp[i] = ++j;
			else kmp[i] = 0; // 생략 가능(원래 0으로 초기화)
		}
		
		System.out.println(Arrays.toString(kmp));
		ArrayList<Integer> idx = new ArrayList<>();
		for(int i = 0, j = 0; i < T.length(); i++) {
			while(j > 0 && T.charAt(i) != P.charAt(j)) j = kmp[j-1];
			
			if(T.charAt(i) == P.charAt(j)) {
				if(j == P.length()-1) { // 끝까지 같은 경우
					idx.add(i - j + 1); // 시작 인덱스 배열에 저장하기
					j = kmp[j];
				} else {
					j++;
				}
			}
		}
		
		System.out.println(idx.size());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < idx.size(); i++) {
			sb.append(idx.get(i)).append(" ");
		}
		System.out.println(sb);
	}
}

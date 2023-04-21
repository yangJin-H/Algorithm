package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = 55, r = 2, i = 4;
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString((a>>(r+i))<<i));
		System.out.println(Integer.toBinaryString(a%(1<<i)));
		System.out.println(Integer.toBinaryString((a%(1<<i))+((a>>(r+i))<<i)));
	}
	
	public static int gcd(int x, int y) {
		if(y == 0) return x;
		return gcd(y, x % y);
	}
}

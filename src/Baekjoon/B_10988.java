package Baekjoon;

import java.util.Scanner;

//팰린드롬인지 확인하기
public class B_10988 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();
        int l = word.length();
        int tf = 1;
        for (int i = 0; i < l / 2; i++) {
            if (word.charAt(i) != word.charAt(l - i - 1)) {
                tf = 0;
            }
        }
        System.out.print(tf);
    }
}

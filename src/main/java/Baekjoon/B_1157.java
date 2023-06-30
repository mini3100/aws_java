package Baekjoon;

import java.util.Scanner;
//미완
//단어 공부
public class B_1157 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();
        word = word.toLowerCase();
        int count[] = new int[word.length()];
        for (int i = 0; i < word.length(); i++) {
            word.contains(Character.toString(word.charAt(i)));
        }
    }
}

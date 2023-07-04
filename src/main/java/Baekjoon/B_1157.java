package Baekjoon;

import java.util.HashMap;
import java.util.Scanner;

//단어 공부
public class B_1157 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] word = scanner.nextLine().toLowerCase().toCharArray();
        HashMap<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < word.length; i++) {
            if (!count.containsKey(word[i])) {
                count.put(word[i], 1);
            } else {
                count.put(word[i], count.get(word[i]) + 1);
            }
        }
        //max 값 찾기
        int max = 0;
        char maxKey = 0;
        for (char w : count.keySet()) {
            if (count.get(w) == max)
                maxKey = '?';
            if (count.get(w) > max) {
                max = count.get(w);
                maxKey = w;
            }
        }
        System.out.println(Character.toString(maxKey).toUpperCase());
    }
}

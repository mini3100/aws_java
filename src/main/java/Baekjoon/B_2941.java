package Baekjoon;

import java.util.Scanner;

//크로아티아 알파벳
public class B_2941 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();

        word = word.replaceAll("c=","*");
        word = word.replaceAll("c-","*");
        word = word.replaceAll("dz=","*");
        word = word.replaceAll("d-","*");
        word = word.replaceAll("lj","*");
        word = word.replaceAll("nj","*");
        word = word.replaceAll("s=","*");
        word = word.replaceAll("z=","*");

        System.out.println(word.length());
    }
}

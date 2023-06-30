package ch03_연산자;

import java.util.Scanner;

public class Operator04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();
        char grade;
        grade = score > 100 || score < 0 ? 'X'
                : score >= 90 ? 'A'
                : score >= 80 ? 'B'
                : score >= 70 ? 'C'
                : score >= 60 ? 'D'
                : 'F';

        System.out.println(grade);
    }
}

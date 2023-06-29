package ch05_조건;

import java.util.Scanner;

public class IfScore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char grade;
        System.out.print("성적을 입력하세요 : ");
        int score = scanner.nextInt();

        if (score > 100 || score < 0)
            grade = 'X';
        else if (score >= 90)
            grade = 'A';
        else if (score >= 80)
            grade = 'B';
        else if (score >= 70)
            grade = 'C';
        else if (score >= 60)
            grade = 'D';
        else
            grade = 'F';

        System.out.println(grade);
    }
}

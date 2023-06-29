package Baekjoon;

import java.util.Scanner;

//너의 평점은
//(학점 * 과목평점)의 합 / 학점의 총합
public class B_25256 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double sum = 0, creditSum = 0, credit, grade, avg;
        String g;
        for (int i = 0; i < 20; i++) {
            scanner.next();
            credit = scanner.nextFloat();
            g = scanner.next();
            if (!g.equals("P")) creditSum += credit;
            grade = g.equals("A+") ? 4.5
                    : g.equals("A0") ? 4.0
                    : g.equals("B+") ? 3.5
                    : g.equals("B0") ? 3.0
                    : g.equals("C+") ? 2.5
                    : g.equals("C0") ? 2.0
                    : g.equals("D+") ? 1.5
                    : g.equals("D0") ? 1.0
                    : 0.0;
            sum += credit * grade;
        }
        avg = sum / creditSum;
        System.out.printf("%.6f",avg);
    }
}

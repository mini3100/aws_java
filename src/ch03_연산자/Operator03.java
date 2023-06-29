package ch03_연산자;

public class Operator03 {
    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        int c = 5;
        int max = a > b ? a : b;
        max = max > c ? max : c;
        System.out.println(max);
        System.out.println(a < b ? (b < c ? c : b) : (a < c ? c : a));
    }
}

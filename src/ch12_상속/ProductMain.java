package ch12_상속;

public class ProductMain {
    public static void main(String[] args) {
        Computer computer = new Computer("삼성 컴퓨터", 1000, "데스크탑");

        System.out.println(computer.getModel());
        System.out.println();
    }
}

package ch11_문자열;

public class String02 {
    public static void main(String[] args) {
        String phone = "010-9606-3110";
        int index = phone.indexOf("-");
        int lastIndex = phone.lastIndexOf("-");

        String midNumber = phone.substring(index + 1, lastIndex);
        System.out.println(midNumber);

    }
}

package ch11_문자열;

public class String04 {
    public static void main(String[] args) {
        String phone = "        010-----     -9606.3110    ";

        String replacePhone = phone
                .replaceAll("-"," ")
                .replaceAll(" ","")
                .replaceAll("[.]","");
        System.out.println(replacePhone);

        String phone2 = phone.trim();
        System.out.println(phone2);
    }
}

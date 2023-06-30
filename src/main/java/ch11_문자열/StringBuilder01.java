package ch11_문자열;

public class StringBuilder01 {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();

        builder.append("이름: ");
        builder.append("정가영");
        builder.delete(builder.indexOf(":"), builder.indexOf(":") + 1);
        builder.insert(2, ">>");    //인덱스 2번에 >> 삽입

        String str = builder.toString();
        System.out.println(str);

        //StringBuilder 쓰지 않고 : 삭제
        String name = "이름: 정가영";
        String str2 = name.substring(0, name.indexOf(":"))
                .concat(name.substring(name.indexOf(":")+1));
        System.out.println(str2);
    }
}

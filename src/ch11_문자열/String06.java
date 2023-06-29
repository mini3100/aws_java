package ch11_문자열;

public class String06 {

    public static void main(String[] args) {
        String token = "Bearer aaaaaaabbbbbb.cccccccccddddddddddd.eeeeeeeeeeffffff";

        boolean flag = token.startsWith("Bearer");
        boolean flag2 = token.endsWith("ffff"); //파일 확장자 확인할 때 많이 씀

        System.out.println(flag);
        System.out.println(flag2);

        String url = "/api/v1/user/1";
        boolean flag3 = url.startsWith("/api/v1");  //버전 확인

        if(flag3){
            System.out.println("api 버전1 사용");
        }
    }
}

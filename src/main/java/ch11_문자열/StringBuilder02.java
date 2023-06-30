package ch11_문자열;

public class StringBuilder02 {
    public static void main(String[] args) {
        //권한
        String[] roles = {"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"};

        //StringBuilder 없이 하는 방법
        String strRoles = "";

        for (int i = 0; i < roles.length; i++) {
            strRoles += roles[i];
            if (i != roles.length - 1) {
                strRoles += ", ";
            }
        }
        System.out.println(strRoles);

        //StringBuilder 사용하는 방법
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < roles.length; i++) {
            sb.append(roles[i] + ", ");
        }
        sb.delete(sb.lastIndexOf(", "), sb.length());
        System.out.println(sb.toString());
    }
}

package ch19_제네릭;

public class ResponseMain {
    public static void main(String[] args) {
        Response<String> response1 = new Response(200, "회원가입 성공!");

        SignupUser signupUser = SignupUser.builder()
                .username("aaa")
                .password("1234")
                .name("김준일")
                .email("aaa@gmail.com")
                .build();
        //Response의 data에 String과 signupUser 타입을 둘 다 넣을 수 있게 만들고 싶음.
        Response<SignupUser> response2 = new Response(400, signupUser);

        AccountUser accountUser = AccountUser.builder().username("aaa").password("1234").build();
        Response<AccountUser> response3 = new Response<AccountUser>(200, accountUser);
        //response3 = response2;
        UpdateUser updateUser = UpdateUser.builder()
                .username("aaa")
                .password("1234")
                .address("부산")
                .phone("010-9606-3110")
                .build();
        Response<UpdateUser> response4 = new Response<UpdateUser>(300, updateUser);
        printResponse(response2);
    }

    public static Response<?> printResponse(Response<? super SignupUser> response) { //AccountUser를 상속 받은 것만 자료형 가능)
        System.out.println(response);
        return response;
    }
}

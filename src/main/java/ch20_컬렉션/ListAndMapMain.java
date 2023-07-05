package ch20_컬렉션;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAndMapMain {
    public static void main(String[] args) {
        List<TestUser> testUsers = new ArrayList<>();
        testUsers.add(TestUser.builder().username("aaa").password("1111").build());
        testUsers.add(TestUser.builder().username("bbb").password("2222").build());
        testUsers.add(TestUser.builder().username("ccc").password("3333").build());
        testUsers.add(TestUser.builder().username("ddd").password("4444").build());
        testUsers.add(TestUser.builder().username("eee").password("5555").build());

        System.out.println(testUsers);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("testUserList", testUsers);
        responseData.put("statusCode", "OK");

        for(TestUser testUser : (List<TestUser>) responseData.get("testUserList")){
            //Object로 testUsers를 받았기 때문에 다운캐스팅 필요함
            System.out.println(testUser);
        }
    }
}

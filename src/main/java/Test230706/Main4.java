package Test230706;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Company {
	private int companyId;
	private String companyName;
	private String address;
	private String city;
	private String state;
	private String zipCode;

}

public class Main4 {
	public static void main(String[] args) {
		Gson gson = new Gson();
	
		Company company = new Company(100, "Apple"
				, "AppleComputer Inc. 1 infinite Loop"
				, "Cupertino", "CA", "95014");
		
		System.out.println(gson.toJson(company)
				.replaceAll(",", ",\n "));
	}

}
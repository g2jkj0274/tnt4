package tnt4.dto;

import java.util.Map;
import lombok.Data;

@Data
public class Member extends Dto {
	public String loginId;
	public String loginPw; 
	public String name, gender, birth, height, weight;
	
	public Member(Map<String, Object> row) {
		super(row);
		this.loginId = (String) row.get("loginId");
		this.loginPw = (String) row.get("loginPw");
		this.name = (String) row.get("name");
		this.gender = (String) row.get("gender");
		this.birth = (String) row.get("birth");
		this.height = (String) row.get("height");
		this.weight = (String) row.get("weight");
	}
	
	public Member(String loginId, String loginPw, String name, String gender, String birth, String height, String weight) {
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.height = height;
		this.weight = weight;
	}
}
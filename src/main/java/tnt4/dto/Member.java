package tnt4.dto;

import java.util.Map;
import lombok.Data;

@Data
public class Member extends Dto {
	public String loginId;
	public String loginPw; 
	public String name, gender, birth;
	public int height, weight;
	
	public Member(Map<String, Object> row) {
		super(row);
		this.loginId = (String) row.get("loginId");
		this.loginPw = (String) row.get("loginPw");
		this.name = (String) row.get("name");
		this.gender = (String) row.get("gender");
		this.birth = (String) row.get("birth");
		this.height = (int) row.get("height");
		this.weight = (int) row.get("weight");
	}
	
	public Member(String loginId, String loginPw, String name, String gender, String birth, int height, int weight) {
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.height = height;
		this.weight = weight;
	}
}
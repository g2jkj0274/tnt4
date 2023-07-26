package tnt4.dto;

import java.util.Map;
import lombok.Data;

@Data
public class Member extends Dto {
	public String loginId;
	public String loginPw; 
	public String name, gender;
	public int height, weight;
	public int bmiId;
	
	public Member(Map<String, Object> row) {
		super(row);
		this.loginId = (String) row.get("loginId");
		this.loginPw = (String) row.get("loginPw");
		this.name = (String) row.get("name");
		this.gender = (String) row.get("gender");
		this.height = (int) row.get("height");
		this.weight = (int) row.get("weight");
		// Null 체크 후 Integer로 형변환
		// 현재 전부 0 뜸, 수정 필요
		this.bmiId = row.get("bmiId") != null ? (Integer) row.get("bmiId") : 0;
	}
	
	public Member(String loginId, String loginPw, String name, String gender,  int height, int weight, int bmiId) {
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.bmiId =bmiId;
	}
}
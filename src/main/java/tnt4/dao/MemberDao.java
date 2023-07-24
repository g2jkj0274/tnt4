package tnt4.dao;

import java.util.Map;

import tnt4.container.Container;
import tnt4.db.DBConnection;
import tnt4.dto.Member;

public class MemberDao extends Dao {
	private DBConnection dbConnection;
	
	public MemberDao() {
		dbConnection = Container.getDBConnection();
	}
	
	public Member getMemberByLoginId(String loginId) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `member` "));
		sb.append(String.format("WHERE loginId = '%s' ", loginId));

		Map<String, Object> memberRow = dbConnection.selectRow(sb.toString());

		if ( memberRow.isEmpty() ) {
			return null;
		}
		return new Member(memberRow);
	}
	
	public Member getMember(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `member` "));
		sb.append(String.format("WHERE id = '%d' ", id));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());

		if (row.isEmpty()) {
			return null;
		}

		return new Member(row);
	}
	
	public int join(Member member) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("INSERT INTO `member` "));
		sb.append(String.format("SET regDate = NOW(), "));
		sb.append(String.format("updateDate = NOW(), "));
		sb.append(String.format("loginId = '%s', ", member.loginId));
		sb.append(String.format("loginPw = '%s', ", member.loginPw));
		sb.append(String.format("`name` = '%s' ", member.name));
		sb.append(String.format("`name` = '%s' ", member.name));
		sb.append(String.format("`name` = '%s' ", member.name));
		sb.append(String.format("`name` = '%s' ", member.height));
		sb.append(String.format("`name` = '%s' ", member.weight));
		
		return dbConnection.insert(sb.toString());
	}

	public int changePw(String loginPw,String loginId) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("UPDATE `member` "));
		sb.append(String.format("SET loginPw = '%s'",loginPw));
		sb.append(String.format("WHERE loginId= '%s' ",loginId));
		return dbConnection.insert(sb.toString());
	}
}

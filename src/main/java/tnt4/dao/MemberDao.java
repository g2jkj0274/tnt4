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

	// DB Data에 있는 ID 가져옴
	public Member getMemberByLoginId(String loginId) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `member` "));
		sb.append(String.format("WHERE loginId = '%s' ", loginId));
		Map<String, Object> memberRow = dbConnection.selectRow(sb.toString());
		if (memberRow.isEmpty()) {
			return null;
		}
		return new Member(memberRow);
	}

	// DB Data에 있는 PW 가져옴
	public Member getMemberByLoginPw(String loginPw) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `member` "));
		sb.append(String.format("WHERE loginPw = '%s' ", loginPw));

		Map<String, Object> memberRow = dbConnection.selectRow(sb.toString());

		if (memberRow.isEmpty()) {
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

		sb.append("INSERT INTO `member` ");
		sb.append("(`bmiId`, `loginId`, `loginPw`, `name`, `gender`, `height`, `weight`) ");
		sb.append(String.format("VALUES ('%d', '%s', '%s', '%s', '%s', '%d', '%d')", member.bmiId, member.loginId,
				member.loginPw, member.name, member.gender, member.height, member.weight));

		return dbConnection.insert(sb.toString());
	}

	public int changePw(String loginPw, String loginId) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("UPDATE `member` "));
		sb.append(String.format("SET loginPw = '%s'", loginPw));
		sb.append(String.format("WHERE loginId= '%s' ", loginId));
		return dbConnection.insert(sb.toString());
	}

	public void modify(Member member) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("UPDATE `member` "));
		sb.append(String.format("SET `bmiId`='%d', `name`='%s', `gender`='%s', `height`='%d', `weight`='%d' ",
				member.bmiId, member.name, member.gender, member.height, member.weight));
		sb.append(String.format("WHERE `id`='%s'", member.id));
		dbConnection.insert(sb.toString());
		System.out.println("회원 정보가 수정되었습니다!");
	}

	public void delete(Member member) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("DELETE FROM `member` "));
		sb.append(String.format("WHERE loginId='%s' ", member.loginId));
		dbConnection.insert(sb.toString());
	}
}

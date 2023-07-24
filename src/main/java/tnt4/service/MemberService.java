package tnt4.service;

import tnt4.container.Container;
import tnt4.dao.MemberDao;
import tnt4.dto.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		memberDao = Container.memberDao;
	}
	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}
	public Member getMember(int memberId) {
		return memberDao.getMember(memberId);
	}
	public void changeNewPw(String loginPw,String loginId) {
		memberDao.changePw(loginPw,loginId);
	}
	public void join(String loginId, String loginPw, String name, String gender, String birth, int height, int weight) {
		Member member = new Member(loginId, loginPw, name, gender, birth, height, weight);
		memberDao.join(member);
	}
}

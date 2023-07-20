package tnt4.controller;

import java.util.Scanner;

import tnt4.container.Container;
import tnt4.dto.Member;
import tnt4.service.MemberService;

public class MemberController extends Controller {
	private static Scanner sc;
	private static MemberService memberService;
	private static Session session;
	
	public MemberController() {
		sc = Container.getScanner();
		session = Container.getSession();
		memberService = Container.memberService;
	}
	
	public void doAction(String command, String actionMethodName) {
		switch ( actionMethodName ) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		case "info":
			showInfo();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}

	private static boolean isJoinableLoginId(String loginId) {
		Member member = memberService.getMemberByLoginId(loginId);
		
		if ( member == null ) {
			return true;
		}
		return false;
	}
	
	public static void doJoin() {
		String loginId = null;
		
		while ( true ) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			
			if ( isJoinableLoginId(loginId) == false ) {
				System.out.printf("%s(은)는 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			break;
		}
		String loginPw = null;
		String loginPwConfirm = null;
		while ( true ) {
			System.out.printf("로그인 비번 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비번확인 : ");
			loginPwConfirm = sc.nextLine();
			
			if ( loginPw.equals(loginPwConfirm) == false ) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}
		System.out.printf("이름 : ");
		String name = sc.nextLine();
		System.out.printf("성별 : ");
		String gender = sc.nextLine();
		System.out.printf("생년월일 : ");
		String birth = sc.nextLine();
		System.out.printf("키 : ");
		String height = sc.nextLine();
		System.out.printf("몸무게 : ");
		String weight = sc.nextLine();
		memberService.join(loginId, loginPw, name, gender, birth, height, weight);
		System.out.printf("회원가입이 완료되었습니다. [%s] 님 환영합니다^^\n", name);
	}
	
	public static void doLogin() {
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비번 : ");
		String loginPw = sc.nextLine();
		
		// 입력받은 아이디에 해당하는 회원이 존재하는지
		Member member = memberService.getMemberByLoginId(loginId);
		
		if ( member == null ) {
			System.out.println("해당 회원은 존재하지 않습니다.");
			return;
		}
		if ( member.loginPw.equals(loginPw) == false ) {
			System.out.println("비밀번호가 맞지 않습니다.");
			return;
		}
		session.setLoginedMember(member);
		Member loginedMember = session.getLoginedMember();

		System.out.printf("로그인 성공! %s님 환영합니다!\n", loginedMember.name);
	}
	
	private void doLogout() {
		session.setLoginedMember(null);
		System.out.println("로그아웃 되었습니다.");
	}
	
	private void showInfo() {
		System.out.println("이름:"+session.getLoginedMember().name);
		System.out.println("성별:"+session.getLoginedMember().gender);
		System.out.println("생년월일:"+session.getLoginedMember().birth);
		System.out.println("키:"+session.getLoginedMember().height);
		System.out.println("몸무게:"+session.getLoginedMember().weight);
		
		System.out.println("- - -");
		System.out.println("비밀번호 변경 : changePw");
		String changePw = sc.nextLine();
		if(changePw.equals(changePw)) {
			dochangePw();
		}
	}

	private void dochangePw() {
		System.out.println("현재 비밀번호 : ");
		String nowPw = sc.nextLine();
		Member member = memberService.getMemberByLoginId(nowPw);
		if ( member.loginPw.equals(nowPw) == false ) {
			System.out.println("비밀번호가 맞지 않습니다.");
			return;
		}
		String loginPw = null;
		String loginPwConfirm = null;
		while ( true ) {
			System.out.printf("변경할 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("비밀번호 확인 : ");
			loginPwConfirm = sc.nextLine();
			
			if ( loginPw.equals(loginPwConfirm) == false ) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			memberService.changeNewPw(loginPw);
			System.out.println("비밀번호 변경 완료");
			break;
		}
	}
}

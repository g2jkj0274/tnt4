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
	
	public void doAction(String command) {
		switch (command) {
		case "member join":
			doJoin();
			break;
		case "member login":
			doLogin();
			break;
		case "member logout":
			doLogout();
			break;
		case "member info":
			showInfo();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}
	
	// 로그인 상태 확인
	private static boolean isJoinableLoginId(String loginId) {
		Member member = memberService.getMemberByLoginId(loginId);
		
		if ( member == null ) {
			return true;
		}
		return false;
	}
	
	// 회원가입
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
		System.out.printf("키 : ");
		int height = Integer.parseInt(sc.nextLine());
		System.out.printf("몸무게 : ");
		int weight = Integer.parseInt(sc.nextLine());
		
		int bmiId = MemberController.getBmi(height, weight);
		memberService.join(loginId, loginPw, name, gender, height, weight, bmiId);

		System.out.println("회원가입이 완료되었습니다.");
		
		// 회원가입 후 정보 유지, DB에 저장
		Member member = memberService.getMemberByLoginId(loginId);
		
		session.setLoginedMember(member);
		Member loginedMember = session.getLoginedMember();

		System.out.printf("로그인 성공! [%s]님 환영합니다!\n", loginedMember.name);
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
		System.out.printf("로그인 성공! [%s]님 환영합니다!\n", loginedMember.name);
	}
	
	private void doLogout() {
		session.setLoginedMember(null);
		System.out.println("로그아웃 되었습니다.");
	}
	
	// My Page
	private void showInfo() {
		System.out.println("= 내 정보 =");
		System.out.println("이름:" + session.getLoginedMember().name);
		System.out.println("성별:" + session.getLoginedMember().gender);
		System.out.println("키:" + session.getLoginedMember().height);
		System.out.println("몸무게:" + session.getLoginedMember().weight);
		System.out.println("BMI:" + session.getLoginedMember().bmiId);

		System.out.println("- - -");
		System.out.println("돌아가기 : 1 / 회원 정보 수정 : 2 / 비밀번호 변경 : 3 / 회원 탈퇴 : 4");

		while (true) {
			String input = sc.nextLine();
			switch (input) {
			case "1":
				return; // 돌아가기
			case "2":
				doModify(); // 회원 정보 수정
				return;
			case "3":
				dochangePw(); // 비밀번호 변경
				return;
			case "4":
				doDelete(session); // 회원 탈퇴
				return;
			default:
				System.out.println("존재하지 않는 명령어 입니다. 다시 입력해주세요.");
				System.out.println("돌아가기 : 1 / 회원 정보 수정 : 2 / 비밀번호 변경 : 3 / 회원 탈퇴 : 4");
				break;
			}
		}
	}
	
	// 비밀번호 변경
	private void dochangePw() {
		System.out.println("현재 비밀번호 : ");
		String nowPw = sc.nextLine();
		Member member = memberService.getMemberByLoginPw(nowPw);
		if ( member.loginPw.equals(nowPw) == false ) {
			System.out.println("비밀번호가 맞지 않습니다.");
			return;
		}
		String loginPw = null;
		String loginPwConfirm = null;
		while ( true ) {
			System.out.println("현재 아이디는 " + member.loginId);
			System.out.printf("변경할 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("비밀번호 확인 : ");
			loginPwConfirm = sc.nextLine();
			
			if ( loginPw.equals(loginPwConfirm) == false ) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			memberService.changeNewPw(loginPw,member.loginId); 
			System.out.println("비밀번호 변경 완료");
			break;
		}
	}
	
	public static void doDelete(Session session) {
	    Member loginedMember = session.getLoginedMember();
	    System.out.println("진짜 탈퇴하시겠습니까 ? Y/N");
	    // 수정함 : next() -> nextLine() - 이유 : next()로 하면 삭제 후 App클래스에서 다시 입력하세요가 출력됨
	    String an = sc.nextLine();
	    if(an.equals("Y") || an.equals("y")) {
	        memberService.delete(loginedMember);
	        session.setLoginedMember(null);
	    }
	    else if (an.equalsIgnoreCase("N")) {
	        System.out.println("취소되었습니다.");
	    }
	}
	
	//수정 필요
	//추가 , 회원 정보 수정 함수
	public static void doModify() {
		if(!session.isLogined()) {
			System.out.println("로그인을 먼저 하십시오");
		}
		Member loginedMember = session.getLoginedMember();
		System.out.println("수정을 시작");
		System.out.printf("이름 : ");
		String name = sc.nextLine();
		System.out.printf("성별 : ");
		String gender = sc.nextLine();
		//제거
		System.out.printf("키 : ");
		int height = sc.nextInt();
		sc.nextLine();
		System.out.printf("몸무게 : ");
		int weight = sc.nextInt();
		sc.nextLine();
		int bmiId; //추가
		bmiId=getBmi(height,weight);
		loginedMember.name =name; loginedMember.gender = gender; loginedMember.height=height;
		loginedMember.weight = weight; loginedMember.bmiId=bmiId;
		memberService.modify(gender, name, bmiId, height, weight);
	}
	
	// bmi 공식
	public static int getBmi(int height, int weight) {
		double bmi;
		int bmiId;
		
		bmi = weight/(Math.pow(((float)height/100),2));
		if(bmi<=18.5) {
			bmiId=1;
		}
		else if(bmi <= 22.9) {
			bmiId =2;
		}
		else if(bmi <= 24.9) {
			bmiId =3;
		}
		else if(bmi > 24.9) {
			bmiId =3;
		}
		else {
			return 0;
		}
		return bmiId;
	}
}

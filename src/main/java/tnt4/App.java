package tnt4;

import tnt4.container.Container;
import tnt4.controller.ArticleController;
import tnt4.controller.Controller;
import tnt4.controller.MemberController;
import tnt4.controller.Session;
import tnt4.db.DBConnection;

public class App {
	private Session session;
	
	public App() {
		DBConnection.DB_NAME = "sbs_proj";
		DBConnection.DB_USER = "sbsst";
		DBConnection.DB_PASSWORD = "sbs123414";
		DBConnection.DB_PORT = 3306;
		
		session = Container.getSession();
		
		Container.getDBConnection().connect();
	}
	
	public void start() {
		System.out.println("==프로그램 시작==");

		ArticleController articleController = new ArticleController();
		MemberController memberController = new MemberController();

		while(true) {
			String select = null;
			if (session.isLogined() == false) {
				System.out.println("[월요일]");
				System.out.println("- 부터 요요 없는 일요일 까지!");
				System.out.println("-------명령어 모음-------");
				System.out.println("로그인 : member login");
				System.out.println("회원가입 : member join");
				System.out.println("프로그램 종료:system exit");
				System.out.println("----------------------");
				System.out.printf(">>> ");
				select = Container.getScanner().nextLine();
//				System.out.println("입력된 명령어 >>> " + select);

				switch (select) {
				case "member login":
					MemberController.doLogin();
					break;
				case "member join":
					MemberController.doJoin();
					break;
				case "system exit":
					System.out.println("==프로그램을 종료합니다==");
                    return; // 프로그램 종료
				default:
					System.out.println("다시 입력하세요");
				}
				//해당 ID 없으면 다시 실행
				continue;
			}
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("[명령어 모음]======================");
			System.out.println(" - 운동/식단 선택 : select item");
			System.out.println(" - 공지사항 : notice board");
			System.out.println(" - My Page : member info");
			System.out.println(" - 로그아웃 : member logout");
			System.out.println(" - 프로그램 종료 : system exit");
			System.out.println("================================");
			
			if(session.getLoginedMember().loginId.equals("admin")) {
				System.out.println("[관리자 권한 명령어 목록]=============");
				System.out.println(" - 공지사항 게시판 : ");
				System.out.println(" - 유저 관리 : ");
				System.out.println(" - 운동/식단 관리 : ");
				System.out.println("================================");
			}

			System.out.printf("명령어 >>> ");
			String command = Container.getScanner().nextLine();
			command = command.trim();
			
			// 아무것도 입력하지 않으면 다시 실행
			if (command.length() == 0) {
				continue;
			}
			
			// 시스템 종료
			if (command.equals("system exit")) {
				break;
			}
			
			// command 분리
			String[] commandBits = command.split(" ");
			if (commandBits.length == 1) {
				System.out.println("존재하지 않는 명령어");
				continue;
			}
			String controllerName = commandBits[0]; // article
			String actionMethodName = commandBits[1]; // member
			
			Controller controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;
			} 
			else if (controllerName.equals("member")) {
				controller = memberController;
			}
			else if (controllerName.equals("select")) {
				controller = articleController;
			}
			else if (controllerName.equals("notice")) {
				controller = articleController;
			} 
			else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			String actionName = controllerName + "/" + actionMethodName;
			switch (actionName) {
			case "member/logout":
			case "member/info":
			case "select/item":
			case "Notice/board":
				if (Container.getSession().isLogined() == false) {
					System.out.println("로그인 후 이용");
					continue;
				}
				break;
			}
			switch (actionName) {
			case "member/login":
			case "member/join":
				if (Container.getSession().isLogined()) {
					System.out.println("로그아웃 후 이용");
					continue;
				}
				break;
			}
			controller.doAction(command, actionMethodName);
		}
		Container.getScanner().close();
		System.out.println("==프로그램을 종료합니다==");
	}
}

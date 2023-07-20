package tnt4;

import tnt4.container.Container;
import tnt4.controller.ArticleController;
import tnt4.controller.Controller;
import tnt4.controller.MemberController;
import tnt4.controller.Session;
import tnt4.db.DBConnection;

public class App {
	public App() {
		DBConnection.DB_NAME = "tnt4";
		DBConnection.DB_USER = "sbsst";
		DBConnection.DB_PASSWORD = "sbs123414";
		DBConnection.DB_PORT = 3306;
		
		Container.getDBConnection().connect();
		
		Container.getSession().setCurrentBoard(Container.articleService.getBoard(1));
	}
	
	public void start() {
		System.out.println("==프로그램 시작==");
		
		ArticleController articleController = new ArticleController();
		MemberController memberController = new MemberController();
		
		while(true) {
			String select = null;
			if (Container.getSession().isLogined() == false) {
				System.out.printf("로그인:1 - 회원가입:2 - 비회원:3 >>> ");
				select = Container.getScanner().nextLine();
				System.out.println("입력된 명령어 >>> " + select);

				switch (select) {
				case "1":
					MemberController.doLogin();
					break;
				case "2":
					MemberController.doJoin();
					break;
				case "3":
					System.out.println("비회원으로 진행");
					break;
				}
			}
			System.out.println("[명령어 모음]");
			System.out.println("1. 운동/식단 선택 : article changeBoard");
			System.out.println("2. My Page : member info");
			
			System.out.printf("명령어) ");
			String command = Container.getScanner().nextLine();
			command = command.trim();

			if(command.length() == 0) {
				continue;
			}
			if(command.equals("system exit")) {
				break;
			}
			String[] commandBits = command.split(" "); // article ~~
			if(commandBits.length==1) {
				System.out.println("존재하지 않는 명령어");
				continue;
			}
			String controllerName  = commandBits[0]; // article
			String actionMethodName = commandBits[1]; // member
			
			Controller controller = null;
			
			if(controllerName .equals("article")) {
				controller = articleController;
			}
			else if(controllerName .equals("member")) {
				controller = memberController;
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			String actionName = controllerName + "/" + actionMethodName;
			switch (actionName) {
			case "article/write":
			case "article/delete":
			case "article/modify":
			case "member/logout":
			case "article/exercise":
			case "member/info":
				if (Container.getSession().isLogined() == false) {
					System.out.println("로그인 후 이용");
					continue;
				}
				break;
			}
			switch(actionName) {
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
		System.out.println("== 프로그램 끝 ==");
	}
}

package tnt4.controller;

import java.util.Scanner;

import tnt4.container.Container;

public class AdminController extends Controller {
	private Scanner sc;
	private String command;
	private Session session;

	public AdminController() {
		sc = Container.getScanner();
		session = Container.getSession();
	}

	public void doAction(String command) {
		System.out.println("작동 확인");
		this.command = command;
		switch (command) {
		case "exercise":
			System.out.println("운동 관리 기능 실행");
			break;
		case "eat":
			System.out.println("식단 관리 기능 실행");
			break;
		case "notice":
			System.out.println("공지사항 관리 기능 실행");
			break;
		case "QnA":
			System.out.println("QnA 관리 기능 실행");
			break;
		case "member":
			System.out.println("회원 관리 기능 실행");
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}
}
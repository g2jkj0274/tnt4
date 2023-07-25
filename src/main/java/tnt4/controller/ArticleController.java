package tnt4.controller;

import java.util.List;
import java.util.Scanner;

import tnt4.container.Container;
import tnt4.service.ArticleService;

public class ArticleController extends Controller {
	private Scanner sc;
	private String command;
	private Session session;
	private ArticleService articleService;

	public ArticleController() {
		sc = Container.getScanner();
		session = Container.getSession();
		articleService = Container.articleService;
	}
	
	public void doAction(String command, String actionMethodName) {
		System.out.println(session.getLoginedMember().loginId);
		this.command = command;
		if(session.getLoginedMember().loginId.equals("admin")) {
			switch (actionMethodName) {
			case "select":
				showSelect();
				break;
			default:
				System.out.println("존재하지 않는 명령어 입니다.");
				break;
			}
		}
		else {
			switch (actionMethodName) {
			case "select":
				showSelect();
				break;
			default:
				System.out.println("존재하지 않는 명령어 입니다.");
				break;
			}
		}
	}
	
	private void showSelect() {
		System.out.println("운동 : 1 - 식단 : 2");
		String select =sc.nextLine();
		System.out.println("입력된 명령어 >>> " + select);
		
		switch(select) {
		case "1":
			showSelectPlace();/////!!!!!!!!!!!!!!!!!!
//			showPlaceExercise("1", "3");
			break;
		case "2":
			showSelectEat();
			break;
		}
	}
	private void showSelectPlace() {
		System.out.println("헬스장 / 홈 : ");
		String selectPlace =sc.nextLine();
		System.out.println("입력된 명령어 >>> " + selectPlace);
		
		System.out.println("유산소 / 무산소 : ");
		String selectExercise =sc.nextLine();
		System.out.println("입력된 명령어 >>> " + selectExercise);
		
		showPlaceExercise(selectPlace, selectExercise);
	}
	private void showPlaceExercise(String selectPlace, String selectExercise) {
		// 리스트 형식으로 DB Data를 저장
		List<String> exerciseList = articleService.getExerciseList(selectPlace, selectExercise);
		// for문을 사용하여 리스트 출력
		for (String exercise : exerciseList) {
		    System.out.println(exercise);
		}
	}
	private void showSelectEat() {
		System.out.println("다이어트 : 1 - 벌크업 : 2");
		String select =sc.nextLine();
		System.out.println("입력된 명령어 >>> " + select);
		
		switch(select) {
		case "1":
			showDiet();
			break;
		case "2":
			showBulk();
			break;
		}
	}
	private void showDiet() {
	}
	private void showBulk() {
	}
}
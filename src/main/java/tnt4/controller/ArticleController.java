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
	
	// 생성자 - 스캐너, 세션(현재 유저 정보), 서비스 사용 가능하게 함
	public ArticleController() {
		sc = Container.getScanner();
		session = Container.getSession();
		articleService = Container.articleService;
	}
	
	// 입력한 명령어를 switch로 구분 
	public void doAction(String command, String actionMethodName) {
		System.out.println(session.getLoginedMember().loginId);
		this.command = command;
		// 관리자 계정이 사용 가능한 기능
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
		// 일반 사용자가 사용 가능한 기능
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
	// 운동/ 식단 선택
	private void showSelect() {
		System.out.println("운동 : 1 - 식단 : 2");
		String select =sc.nextLine();
		System.out.println("입력된 명령어 >>> " + select);
		
		switch(select) {
		// 1번이면 운동
		case "1":
			showSelectPlace();
//			showPlaceExercise("1", "3");/////test용
			break;
		// 2번이면 식단
		case "2":
			showSelectEat();
			break;
		// 그 외의 입력시 다시 실행
		default:
            System.out.println("올바른 값을 입력하세요.");
            showSelect(); // 재귀 호출하여 메서드를 다시 실행
            break;
		}
		
	}
	
	// 헬스장/홈트 선택
	private void showSelectPlace() {
		System.out.println("헬스장 / 홈트 : ");
		String selectPlace = sc.nextLine();
		System.out.println("입력된 명령어 >>> " + selectPlace);
		// 헬스장 or 홈트 아니면 다시 실행
		if(!selectPlace.equals("헬스장") && !selectPlace.equals("홈트")) {
			System.out.println("올바른 장소를 입력하세요.");
			showSelectPlace(); // 재귀 호출하여 메서드를 다시 실행
			return;
		}
		showSelectExercise(selectPlace);
	}
	
	// 유산소/무산소 선택
	private void showSelectExercise(String selectPlace) {
		System.out.println("유산소 / 무산소 : ");
		String selectExercise = sc.nextLine();
		System.out.println("입력된 명령어 >>> " + selectExercise);
		// 유산소 or 무산소 아니면 다시 실행
		if(!selectExercise.equals("유산소") && !selectExercise.equals("무산소")) {
			System.out.println("올바른 운동 종류를 입력하세요.");
			showSelectExercise(selectPlace); // 재귀 호출하여 메서드를 다시 실행
			return;
		}
		// 입력한 값을 매개변수로 전달
		showPlaceExercise(selectPlace, selectExercise);
	}
	
	// 입력한 헬스장/홈, 유산소/무산소에 따라 DB에서 Data 가져옴
	private void showPlaceExercise(String selectPlace, String selectExercise) {
		// 리스트 형식으로 DB Data를 저장
		List<String> exerciseList = articleService.getExerciseList(selectPlace, selectExercise);
		// for문을 사용하여 리스트 출력
		for (String exercise : exerciseList) {
		    System.out.println(exercise);
		}
	}
	
	// 식단 선택
	// 다이어트 식단 - DB 식단 2, 3번, 벌크업 식단 - DB 1, 2번
	private void showSelectEat() {
		System.out.println("다이어트 : 1 - 벌크업 : 2");
		String select =sc.nextLine();
		System.out.println("입력된 명령어 >>> " + select);
		
		// 수정 필요
		switch(select) {
		case "1":
//			articleService.getFoodList(select);
			break;
		case "2":
//			articleService.getFoodList(select);
			break;
		default:
            System.out.println("올바른 값을 입력하세요.");
            showSelectEat(); // 재귀 호출하여 메서드를 다시 실행
            break;
		}
	}
}
package tnt4.controller;

import java.util.List;
import java.util.Scanner;

import tnt4.container.Container;
import tnt4.dto.Food;
import tnt4.dto.Member;
import tnt4.dto.NoticeBoard;
import tnt4.dto.QnABoard;
import tnt4.service.AdminService;

public class AdminController extends Controller {
	private Scanner sc;
	private AdminService adminService;
	private String command;

	public AdminController() {
		sc = Container.getScanner();
		adminService = Container.getAdminService();
	}

	public void doAction(String command, String loginId) {
		this.command = command;
		switch (command) {
		case "exercise":
			adminSelectPlace();
			break;
		case "food":
			showAdminFoodList();
			break;
		case "notice":
			showAdminNoticeList();
			break;
		case "QnA":
			showAdminQnAList();
			break;
		case "member":
			showAdminMemberList();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}

	// 운동 장소(헬스장/홈트) 구분
	private void adminSelectPlace() {
		System.out.println("--------------------");
		System.out.println("장소를 입력하세요.");
		System.out.println("헬스장 / 홈트");
		System.out.printf(">>> ");

		String selectPlace = sc.nextLine();
		if (!selectPlace.equals("헬스장") && !selectPlace.equals("홈트")) {
			System.out.println("올바른 장소를 입력하세요.");
			adminSelectPlace(); // 재귀 호출하여 메서드를 다시 실행
			return;
		}
		adminSelectExercise(selectPlace);
	}

	// 운동 장소(유산소/무산소) 구분
	private void adminSelectExercise(String selectPlace) {
		System.out.println("--------------------");
		System.out.println("장소를 입력하세요.");
		System.out.println("유산소 / 무산소");
		System.out.printf(">>> ");

		String selectExercise = sc.nextLine();
		if (!selectExercise.equals("유산소") && !selectExercise.equals("무산소")) {
			System.out.println("올바른 운동을 입력하세요.");
			adminSelectExercise(selectPlace); // 재귀 호출하여 메서드를 다시 실행
			return;
		}
		showAdminExerciseList(selectPlace, selectExercise);
	}

	// 관리자 - 운동 리스트 출력
	private void showAdminExerciseList(String selectPlace, String selectExercise) {
		List<String> exerciseList = adminService.getAdminExerciseList(selectPlace, selectExercise);
		System.out.println("");
		System.out.println("[입력한 값]");
		System.out.printf("- 장소 : %s / 종류 : %s \n", selectPlace, selectExercise);
		System.out.println("===== 운동 리스트 =====");
		for (String exercise : exerciseList) {
			System.out.println(exercise);
		}
		System.out.println("====================");

		String selectList = "exercise";
		// 아이템을 어떻게 할 것인지 실행
		itemManagement(selectList);
	}

	// 관리자 - 식단 리스트 출력
	private void showAdminFoodList() {
		List<Food> foodList = adminService.getAdminFoodList();
		System.out.println("===== 식단 리스트 =====");
		for (Food food : foodList) {
			System.out.println("No." + food.id + " / 이름 : " + food.getName());
		}
		System.out.println("====================");

		String selectList = "food";
		// 아이템을 어떻게 할 것인지 실행
		itemManagement(selectList);
	}

	// 관리자 - 공지사항 리스트 출력
	private void showAdminNoticeList() {
		List<NoticeBoard> noticeList = adminService.getAdminNoticeList();
		System.out.println("===== 공지사항 리스트 =====");
		for (NoticeBoard notice : noticeList) {
			System.out.println("No." + notice.getId() + " / 제목 : " + notice.getName());
		}
		System.out.println("=======================");

		String selectList = "notice";
		// 아이템을 어떻게 할 것인지 실행
		itemManagement(selectList);
	}

	// 관리자 - QnA 리스트 출력
	private void showAdminQnAList() {
		List<QnABoard> qnaList = adminService.getAdminQnAList();
		System.out.println("===== QnA 리스트 =====");
		for (QnABoard qna : qnaList) {
			System.out.println("No." + qna.getId() + " / 사용자 질문 : " + qna.getUserQuestionName());
		}
		System.out.println("====================");

		String selectList = "QnA";
		// 아이템을 어떻게 할 것인지 실행
		itemManagement(selectList);
	}

	// 관리자 - 멤버 리스트 출력
	private void showAdminMemberList() {
		List<Member> adminList = adminService.getAdminMemberList();
		System.out.println("===== 멤버 리스트 =====");
		for (Member admin : adminList) {
			System.out.println("ID : " + admin.getId() + " / 이름 : " + admin.getName());
		}
		System.out.println("====================");

		String selectList = "member";
		// 아이템을 어떻게 할 것인지 실행
		itemManagement(selectList);
	}

	// 아이템 쓰기, 수정, 삭제 구분
	private void itemManagement(String selectList) {
		System.out.println("[명령어]");
		System.out.println(" - 메인 : main");
		System.out.println(" - 추가 : write");
		System.out.println(" - 수정 : modify");
		System.out.println(" - 삭제 : delete");
		System.out.println("====================");
		System.out.printf(">>> ");
		String adminSelect = sc.nextLine();
		switch (adminSelect) {
		case "main":
			break;
		case "write":
			writeAdminSelectList(selectList);
			break;
		case "modify":
			modifyAdminSelectList(selectList);
			break;
		case "delete":
			deleteAdminSelectList(selectList);
			break;
		default:
			System.out.println("다시 입력하세요.");
			itemManagement(selectList);
		}
	}

	// 선택한 리스트에 아이템 추가
	private void writeAdminSelectList(String selectList) {
		System.out.println("--------------------");
		System.out.println("아이템 정보를 입력하세요.");

		switch (selectList) {
		case "exercise":
			System.out.printf("운동명 : ");
			String writeName = sc.nextLine();
			System.out.printf("운동장소 : ");
			String writePlace = sc.nextLine();
			System.out.printf("운동종류 : ");
			String writeExercise = sc.nextLine();
			System.out.printf("링크 : ");
			String writeLink = sc.nextLine();
			System.out.printf("BMI ID : ");
			int writeBmiId = sc.nextInt();
			sc.nextLine();

			// 데이터베이스에 아이템 추가
			adminService.writeAdminExercise(writePlace, writeExercise, writeName, writeLink, writeBmiId);

			System.out.println("--------------------");
			System.out.println("[입력한 값]");
			System.out.printf(" - 장소 : %s / 종류 : %s \n", writePlace, writeExercise);
			System.out.printf(" - 이름 : %s / 링크 : %s / BMI ID : %d \n", writeName, writeLink, writeBmiId);
			System.out.println("운동 아이템 추가가 완료되었습니다.");
			break;
		case "food":
			System.out.printf("음식명 : ");
			String writeFoodName = sc.nextLine();
			System.out.printf("칼로리 : \n");
			int writeFoodKal = sc.nextInt();
			System.out.printf("프로틴 : \n");
			int writeFoodPro = sc.nextInt();
			System.out.printf("BMI ID : \n");
			int writeFoodBmiId = sc.nextInt();
			sc.nextLine();
			
			adminService.writeAdminFood(writeFoodName, writeFoodKal, writeFoodPro, writeFoodBmiId);
			
			System.out.println("--------------------");
			System.out.println("[입력한 값]");
			System.out.printf(" - 음식명 : %s / 칼로리 : %s / 프로틴 : %s / BMI ID : %d \n",
							   writeFoodName, writeFoodKal, writeFoodPro, writeFoodBmiId);
			System.out.println("식단 아이템 추가가 완료되었습니다.");
			break;
		case "notice":
			System.out.printf("공지사항 제목 : ");
			String writeNoticeName = sc.nextLine();
			System.out.printf("공지사항 내용 : ");
			String writeNoticeDetail = sc.nextLine();
			
			adminService.writeAdminNotice(writeNoticeName, writeNoticeDetail);
			
			System.out.println("--------------------");
			System.out.println("[입력한 값]");
			System.out.printf(" - 제목 : %s\n", writeNoticeName);
			System.out.printf(" - 내용 : %s\n", writeNoticeDetail);
			System.out.println("공지사항이 작성되었습니다.");
		case "QnA":
			break;
		case "member":
			break;
		default:
			System.out.println("잘못된 목록을 선택하였습니다.");
			return;
		}
	}

	// 선택한 리스트의 아이템 수정
	private void modifyAdminSelectList(String selectList) {
		System.out.println("--------------------");
		System.out.println("수정할 아이템의 ID를 입력하세요.");
		System.out.printf(">>> ");
		int itemId = sc.nextInt();
		sc.nextLine();

		switch (selectList) {
		case "exercise":
			System.out.println("--------------------");
			System.out.println("아이템 정보를 수정하세요.");

			// 수정할 정보 입력 받기
			System.out.printf("운동명 : ");
			String modifyName = sc.nextLine();

			String modifyLocation;
			do {
				System.out.printf("장소 : ");
				modifyLocation = sc.nextLine();
			} while (!isValidExercisePlace(modifyLocation));

			String modifyKind;
			do {
				System.out.printf("종류 : ");
				modifyKind = sc.nextLine();
			} while (!isValidExerciseType(modifyKind));

			System.out.printf("링크 : ");
			String modifyLink = sc.nextLine();

			// 데이터베이스에 아이템 수정
			adminService.modifyAdminExercise(itemId, modifyName, modifyLocation, modifyKind, modifyLink);

			System.out.println("--------------------");
			System.out.printf("아이템 (ID: %d) 수정이 완료되었습니다.\n", itemId);
			break;
		case "food":
			break;
		case "notice":
			break;
		case "QnA":
			break;
		case "member":
			// member 아이템 수정 구현
			break;
		default:
			System.out.println("잘못된 목록을 선택하였습니다.");
			return;
		}
	}

	// 선택한 리스트의 아이템 삭제
	private void deleteAdminSelectList(String selectList) {
		System.out.println("--------------------");
		System.out.println("삭제할 아이템의 ID를 입력하세요.");
		System.out.printf(">>> ");
		int itemId = sc.nextInt();
		sc.nextLine();

		switch (selectList) {
		case "exercise":
			adminService.deleteAdminSelectItem(selectList, itemId);
			break;
		case "food":
			adminService.deleteAdminSelectItem(selectList, itemId);
			break;
		case "notice":
			adminService.deleteAdminSelectItem(selectList, itemId);
			break;
		case "QnA":
			adminService.deleteAdminSelectItem(selectList, itemId);
			break;
		case "member":
			adminService.deleteAdminSelectItem(selectList, itemId);
			break;
		default:
			System.out.println("잘못된 목록을 선택하였습니다.");
			return;
		}
		System.out.printf("아이템 (ID: %d) 삭제가 완료되었습니다.\n", itemId);
		System.out.println("--------------------");
	}

	// 운동 장소 체크
	private boolean isValidExercisePlace(String place) {
		return place.equals("헬스장") || place.equals("홈트");
	}

	// 운동 종류 체크
	private boolean isValidExerciseType(String type) {
		return type.equals("유산소") || type.equals("무산소");
	}
}
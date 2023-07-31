package tnt4.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import tnt4.container.Container;
import tnt4.dto.Exercise;
import tnt4.dto.Food;
import tnt4.dto.Member;
import tnt4.dto.NoticeBoard;
import tnt4.dto.QnABoard;
import tnt4.service.AdminService;

public class AdminController extends Controller {
	private Scanner sc;
	private AdminService adminService;
	private String command;

	private int lastDisplayedQnAId = 0;

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
		// 가독성
		System.out.println("");
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
		// 가독성
		System.out.println("");
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
		// 가독성
		System.out.println("");
		List<NoticeBoard> noticeList = adminService.getAdminNoticeList();
		System.out.println("===== 공지사항 리스트 =====");
		for (NoticeBoard notice : noticeList) {
			System.out.println("No." + notice.getId() + " / 제목 : " + notice.getTitle());
		}
		System.out.println("=======================");

		String selectList = "notice";
		// 아이템을 어떻게 할 것인지 실행
		itemManagement(selectList);
	}

	// 관리자 QnA리스트 출력
	private void showAdminQnAList() {
		// 가독성
		System.out.println("");

		List<QnABoard> qnaList = adminService.getAdminQnAList();

		// 기존의 QnA 리스트 출력
		System.out.println("===== QnA 리스트 =====");
		for (QnABoard qna : qnaList) {
			System.out.println("No." + qna.getId() + " / 사용자 질문 : " + qna.getUserQuestionName());
		}

		// 가독성
		System.out.println("");

		// 추가된 QnA 리스트 출력
		System.out.println("===== 추가된 QnA 리스트 =====");
		for (QnABoard qna : qnaList) {
			if (qna.getId() > lastDisplayedQnAId) {
				System.out.println("No." + qna.getId() + " / 사용자 질문 : " + qna.getUserQuestionName());
			}
		}
		System.out.println("====================");

		String selectList = "QnA";
		// 아이템을 어떻게 할 것인지 실행
		itemManagement(selectList);

		// 새로운 QnA가 있으면 출력한 리스트의 최대 ID 값을 갱신
		if (!qnaList.isEmpty()) {
			int maxId = qnaList.get(qnaList.size() - 1).getId();
			lastDisplayedQnAId = Math.max(lastDisplayedQnAId, maxId);
		}
	}

	// 관리자 - 멤버 리스트 출력
	private void showAdminMemberList() {
		// 가독성
		System.out.println("");

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
		String adminSelect = null;

		if (selectList.equals("member")) {
			System.out.println("[명령어]");
			System.out.println(" - 메인 : main");
			System.out.println(" - 회원 강제삭제 : delete");
			System.out.println("====================");
			System.out.print(">>> ");
			adminSelect = sc.nextLine();
			switch (adminSelect) {
			case "main":
				System.out.println("메인 화면으로 돌아갑니다.");
				break;
			case "delete":
				deleteAdminSelectList(selectList);
				break;
			default:
				System.out.println("다시 입력하세요.");
				itemManagement(selectList);
			}
		} else {
			System.out.println("[명령어]");
			System.out.println(" - 메인 : main");
			System.out.println(" - 추가 : write");
			System.out.println(" - 수정 : modify");
			System.out.println(" - 삭제 : delete");
			System.out.println("====================");
			System.out.print(">>> ");
			adminSelect = sc.nextLine();
		}
		switch (adminSelect) {
		case "main":
			System.out.println("메인 화면으로 돌아갑니다.");
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
		if (!selectList.equals("member")) {

			System.out.println("--------------------");
			System.out.println("아이템 정보를 입력하세요.");

			switch (selectList) {
			case "exercise":
				System.out.printf("운동명 : ");
				String writeName = sc.nextLine();

				String writePlace;
				do {
					System.out.printf("장소 (헬스장/홈트) : ");
					writePlace = sc.nextLine();
				} while (!isValidExercisePlace(writePlace));

				String writeExercise;
				do {
					System.out.printf("종류 (유산소/무산소) : ");
					writeExercise = sc.nextLine();
				} while (!isValidExerciseType(writeExercise));

				System.out.printf("링크 : ");
				String writeLink = sc.nextLine();

				int writeBmiId;
				do {
					System.out.printf("BMI ID (1, 2, 3 중 하나 입력) : ");
					writeBmiId = sc.nextInt();
					sc.nextLine();
				} while (writeBmiId < 1 || writeBmiId > 3);

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
				System.out.printf("칼로리 : ");
				int writeFoodKal = sc.nextInt();
				sc.nextLine();
				System.out.printf("프로틴 : ");
				int writeFoodPro = sc.nextInt();
				sc.nextLine();
				System.out.printf("BMI ID : ");
				int writeFoodBmiId = sc.nextInt();
				sc.nextLine();

				adminService.writeAdminFood(writeFoodName, writeFoodKal, writeFoodPro, writeFoodBmiId);

				System.out.println("--------------------");
				System.out.println("[입력한 값]");
				System.out.printf(" - 음식명 : %s / 칼로리 : %s / 프로틴 : %s / BMI ID : %d \n", writeFoodName, writeFoodKal,
						writeFoodPro, writeFoodBmiId);
				System.out.println("식단 아이템 추가가 완료되었습니다.");
				break;
			case "notice":
				System.out.printf("공지사항 제목 : ");
				String writeNoticeTitle = sc.nextLine();
				System.out.printf("공지사항 내용 : ");
				String writeNoticeDetail = sc.nextLine();

				adminService.writeAdminNotice(writeNoticeTitle, writeNoticeDetail);

				System.out.println("--------------------");
				System.out.println("[입력한 값]");
				System.out.printf(" - 제목 : %s\n", writeNoticeTitle);
				System.out.printf(" - 내용 : %s\n", writeNoticeDetail);
				System.out.println("공지사항이 작성되었습니다.");
				break;
			case "QnA":
				System.out.printf("Q.제목 : ");
				String writeUserQuestionName = sc.nextLine();
				System.out.printf("Q.내용 : ");
				String writeUserQuestionText = sc.nextLine();
				System.out.printf("A.제목 : ");
				String writeAdminAnswerName = sc.nextLine();
				System.out.printf("A.내용 : ");
				String writeAdminAnswerText = sc.nextLine();

				adminService.writeAdminQnA(writeUserQuestionName, writeUserQuestionText, writeAdminAnswerName,
						writeAdminAnswerText);

				System.out.println("--------------------");
				System.out.println("[입력한 값]");
				System.out.printf(" - Q.제목 : %s \n - Q.내용 : %s \n", writeUserQuestionName, writeUserQuestionText);
				System.out.printf(" - A.제목 : %s \n - A.내용 : %s \n", writeAdminAnswerName, writeAdminAnswerText);
				System.out.println("QnA 작성 완료");
				break;
			default:
				System.out.println("잘못된 목록을 선택하였습니다.(write)");
				return;
			}
		}
	}

	// 선택한 리스트의 아이템 수정
	private void modifyAdminSelectList(String selectList) {
		if (!selectList.equals("member")) {
			System.out.println("--------------------");
			System.out.println("수정할 아이템의 ID를 입력하세요.");
			System.out.printf(">>> ");
			int itemId;
			if (sc.hasNextInt()) {
				itemId = sc.nextInt();
				sc.nextLine();
			} else {
				System.out.println("올바른 숫자를 입력하세요.");
				sc.nextLine(); // 입력 버퍼 비우기
				modifyAdminSelectList(selectList); // 재귀 호출로 다시 입력 받기
				return;
			}

			switch (selectList) {
			case "exercise":
				System.out.println("--------------------");
				System.out.println("아이템 정보를 수정하세요.");

				// 선택한 운동 보여줌
				Exercise exercise = adminService.getExercise(itemId);
				System.out.printf(" - 번호 : %d\n", exercise.getId());
				System.out.printf(" - 운동명 : %s\n", exercise.getName());
				System.out.printf(" - 장소 (헬스장/홈트) : %s\n", exercise.getLocation());
				System.out.printf(" - 종류 (유산소/무산소) : %s\n", exercise.getKind());
				System.out.printf(" - 링크 : %s\n", exercise.getLink());
				System.out.printf(" - 추천 수 : %d\n", exercise.getLike());
				System.out.printf(" - BMI ID : %d\n", exercise.getBmiId());
				System.out.println("--------------------");

				// 수정할 정보 입력 받기
				System.out.printf("운동명 : ");
				String modifyName = sc.nextLine();

				String modifyLocation;
				do {
					System.out.printf("장소 (헬스장/홈트) : ");
					modifyLocation = sc.nextLine();
				} while (!isValidExercisePlace(modifyLocation));

				String modifyKind;
				do {
					System.out.printf("종류 (유산소/무산소) : ");
					modifyKind = sc.nextLine();
				} while (!isValidExerciseType(modifyKind));

				System.out.printf("링크 : ");
				String modifyLink = sc.nextLine();

				System.out.printf("추천 수 : ");
				int modifyLike = sc.nextInt();

				int modifyBmiId;
				do {
					System.out.printf("BMI ID (1, 2, 3 중 하나 입력) : ");
					modifyBmiId = sc.nextInt();
					sc.nextLine();
				} while (modifyBmiId < 1 || modifyBmiId > 3);

				// 데이터베이스에 아이템 수정
				adminService.modifyAdminExercise(itemId, modifyName, modifyLocation, modifyKind, modifyLink, modifyLike,
						modifyBmiId);

				System.out.println("--------------------");
				System.out.printf("아이템 (ID: %d) 수정이 완료되었습니다.\n", itemId);
				break;
			case "food":
				System.out.println("--------------------");
				System.out.println("아이템 정보를 수정하세요.");

				// 선택한 식단 보여줌
				Food food = adminService.getFood(itemId);
				System.out.printf(" - 번호 : %d\n", food.getId());
				System.out.printf(" - 식단명 : %s\n", food.getName());
				System.out.printf(" - 칼로리 : %d\n", food.getKal());
				System.out.printf(" - 프로틴 : %d\n", food.getPro());
				System.out.printf(" - 추천 수 : %d\n", food.getLike());
				System.out.printf(" - BMI ID : %d\n", food.getBmiId());
				System.out.println("--------------------");

				// 수정할 정보 입력 받기
				System.out.printf("음식명 : ");
				String modifyFoodName = sc.nextLine();

				System.out.printf("칼로리 : ");
				int modifyFoodKal = sc.nextInt();

				System.out.printf("프로틴 : ");
				int modifyFoodPro = sc.nextInt();

				System.out.printf("추천 수 : ");
				int modifyFoodLike = sc.nextInt();

				int modifyFoodBmiId;
				do {
					System.out.printf("BMI ID (1, 2, 3 중 하나 입력) : ");
					modifyFoodBmiId = sc.nextInt();
					sc.nextLine();
				} while (modifyFoodBmiId < 1 || modifyFoodBmiId > 3);

				// 데이터베이스에 아이템 수정
				adminService.modifyAdminFood(itemId, modifyFoodName, modifyFoodKal, modifyFoodPro, modifyFoodLike,
						modifyFoodBmiId);

				System.out.println("--------------------");
				System.out.printf("아이템 (ID: %d) 수정이 완료되었습니다.\n", itemId);
				break;
			case "notice":
				System.out.println("--------------------");
				System.out.println("공지사항을 수정하세요.");

				// 선택한 공지사항 보여줌

				// 수정할 정보 입력 받기
				System.out.printf("공지사항 제목 : ");
				String modifyTitle = sc.nextLine();

				System.out.printf("공지사항 내용 : ");
				String modifyDetail = sc.nextLine();

				adminService.modifyAdminNotice(itemId, modifyTitle, modifyDetail);

				System.out.println("--------------------");
				System.out.printf("공지사항 (ID: %d) 수정이 완료되었습니다.\n", itemId);
				break;
			case "QnA":
				System.out.println("--------------------");
				System.out.println("QnA를 수정하세요.");

				// 선택한 QnA 보여줌
				QnABoard qnaBoard = adminService.getQnA(itemId);
				System.out.printf(" - QnA 번호 : %d\n", qnaBoard.getId());
				System.out.printf(" - 질문 제목 : %s\n", qnaBoard.getUserQuestionName());
				System.out.printf(" - 질문 내용 : %s\n", qnaBoard.getUserQuestionText());
				System.out.printf(" - 답변 제목 : %s\n", qnaBoard.getAdminAnswerName());
				System.out.printf(" - 답변 내용 : %s\n", qnaBoard.getAdminAnswerText());
				System.out.println("--------------------");

				System.out.printf("답변 제목 : ");
				String modifyAdminAnswerName = sc.nextLine();
				System.out.printf("답변 내용 : ");
				String modifyAdminAnswerText = sc.nextLine();

				adminService.modifyAdminQnA(itemId, qnaBoard.getUserQuestionName(), qnaBoard.getUserQuestionText(),
						modifyAdminAnswerName, modifyAdminAnswerText);

				System.out.println("--------------------");
				System.out.printf("QnA (ID: %d) 수정이 완료되었습니다.\n", itemId);
				break;
			default:
				System.out.println("잘못된 목록을 선택하였습니다.(modify)");
				return;
			}
		}
	}

	// 선택한 리스트의 아이템 삭제
	private void deleteAdminSelectList(String selectList) {
		System.out.println("--------------------");
		System.out.println("삭제할 아이템의 ID를 입력하세요.");
		System.out.printf(">>> ");
		int itemId = 0;
		try {
			itemId = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("올바른 숫자를 입력하세요.");
			sc.nextLine(); // 입력 버퍼 비우기
			modifyAdminSelectList(selectList);
			return;
		}

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
			System.out.println("잘못된 목록을 선택하였습니다.(delete)");
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
package tnt4.controller;

import java.util.List;
import java.util.Scanner;

import tnt4.container.Container;
import tnt4.dto.NoticeBoard;
import tnt4.dto.QnABoard;
import tnt4.service.OperationService;

public class OperationController extends Controller {
	private Scanner sc;
	private String command;
	private Session session;
	private OperationService operationService;
	
	// 생성자 - 스캐너, 세션(현재 유저 정보), 서비스 사용 가능하게 함
	public OperationController() {
		sc = Container.getScanner();
		session = Container.getSession();
		operationService = Container.operationService;
	}
	
	public void doAction(String command, String loginId) {
		System.out.println(session.getLoginedMember().loginId);
		this.command = command;

		System.out.println(command);
		
		// 입력한 명령어를 switch로 구분
		switch (command) {
		case "select item":
			showSelect(loginId);
			break;
		case "notice board":
			showAnnouncement();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다. (operation)");
			break;
		}
	}

	private void showAnnouncement() {
		while (true) {
			System.out.println("공지사항 ===============");
			List<NoticeBoard> noticeBoardList = operationService.getNoticeBoard();

			int index = 1;
			for (NoticeBoard noticeBoard : noticeBoardList) {
				System.out.println(index + ". " + noticeBoard.getName());
				index++;
			}
			System.out.println("======================");

			// 가독성 때문에 한 줄 띄움
			System.out.println("");

			System.out.println("QnA===================");
			List<QnABoard> QnABoardList = operationService.getQnABoard();

			index = 1;
			for (QnABoard QnABoard : QnABoardList) {
				System.out.println(index + ". " + QnABoard.getUserQuestionName());
				index++;
			}

			System.out.println("======================");

			System.out.println("공지사항 보기 : board 숫자");
			System.out.println("QnA 보기 : QnA 숫자");
			System.out.println("메인으로 돌아가기 : 1");

			System.out.printf(">>> ");
			String userInput = sc.nextLine();

			if (userInput.equals("1")) {
				// 돌아가기
				break;
			} else if (userInput.startsWith("board") && userInput.length() > 6) {
				// "board" 다음에 숫자가 오는지 확인
				String selectedIdStr = userInput.substring(6).trim();
				// 숫자 부분이 비어있는지 확인
				if (selectedIdStr.isEmpty()) {
					System.out.println("잘못된 입력입니다. 숫자로 다시 입력하세요.");
					continue; // 루프의 처음으로 돌아가서 유효한 입력을 받도록 함
				}
				try {
					int selectedId = Integer.parseInt(selectedIdStr);
					selectedId--;
					if (selectedId >= 0 && selectedId < noticeBoardList.size()) {
						NoticeBoard selectedNotice = noticeBoardList.get(selectedId);
						System.out.println("======================");
						System.out.println("제목 : " + selectedNotice.getName());
						System.out.println("내용 : " + selectedNotice.getDetail());
						System.out.println("======================");
					} else {
						System.out.println("해당하는 공지사항이 없습니다.");
					}
				} catch (NumberFormatException e) {
					System.out.println("잘못된 입력입니다. 숫자로 다시 입력하세요.");
				}
			} else if (userInput.startsWith("QnA") && userInput.length() > 3) {
				// "QnA" 다음에 숫자가 오는지 확인
				String selectedIdStr = userInput.substring(3).trim();
				// 숫자 부분이 비어있는지 확인
				if (selectedIdStr.isEmpty()) {
					System.out.println("잘못된 입력입니다. 숫자로 다시 입력하세요.");
					continue; // 루프의 처음으로 돌아가서 유효한 입력을 받도록 함
				}
				try {
					int selectedId = Integer.parseInt(selectedIdStr);
					selectedId--;
					if (selectedId >= 0 && selectedId < QnABoardList.size()) {
						QnABoard selectedQnA = QnABoardList.get(selectedId);
						System.out.println("======================");
						System.out.println("질문 : " + selectedQnA.getUserQuestionName());
						System.out.println("질문내용 : " + selectedQnA.getUserQuestionText());
						System.out.println("답변 : " + selectedQnA.getAdminAnswerName());
						System.out.println("답변내용 : " + selectedQnA.getAdminAnswerText());
						System.out.println("======================");
					} else {
						System.out.println("해당하는 QnA가 없습니다.");
					}
				} catch (NumberFormatException e) {
					System.out.println("잘못된 입력입니다. 숫자로 다시 입력하세요.");
				}
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력하세요.");
			}
		}
	}

	// 운동/ 식단 선택
	private void showSelect(String loginId) {
		System.out.println("운동 / 식단");
		System.out.printf(">>> ");
		String select =sc.nextLine();
		System.out.println("입력된 명령어 >>> " + select);
		
		switch(select) {
		case "운동":
			showSelectPlace(loginId);
			break;
		case "식단":
			showSelectEat(loginId);
			break;
		// 그 외의 입력시 다시 실행
		default:
            System.out.println("올바른 값을 입력하세요.");
            showSelect(loginId); // 재귀 호출하여 메서드를 다시 실행
            break;
		}
	}
	
	// 헬스장/홈트 선택
	private void showSelectPlace(String loginId) {
		System.out.println("=====");
		System.out.println("어디에서 운동 하십니까?");
		System.out.println("헬스장 / 홈트");
		System.out.printf(">>> ");
		
		String selectPlace = sc.nextLine();
		System.out.println("입력된 명령어 >>> " + selectPlace);
		
		// 헬스장 or 홈트 아니면 다시 실행
		if(!selectPlace.equals("헬스장") && !selectPlace.equals("홈트")) {
			System.out.println("올바른 장소를 입력하세요.");
			showSelectPlace(loginId); // 재귀 호출하여 메서드를 다시 실행
			return;
		}
		System.out.println("=====");
		showSelectExercise(selectPlace, loginId);
	}
	
	// 유산소/무산소 선택
	private void showSelectExercise(String selectPlace, String loginId) {
		System.out.println("어떤 운동을 하시겠습니까?");
		System.out.println("유산소 / 무산소");
		System.out.printf(">>> ");
		
		String selectExercise = sc.nextLine();
		System.out.println("입력된 명령어 >>> " + selectExercise);
		
		// 유산소 or 무산소 아니면 다시 실행
		if(!selectExercise.equals("유산소") && !selectExercise.equals("무산소")) {
			System.out.println("올바른 운동 종류를 입력하세요.");
			showSelectExercise(selectPlace, loginId); // 재귀 호출하여 메서드를 다시 실행
			return;
		}
		// 입력한 값을 매개변수로 전달
		showPlaceExercise(selectPlace, selectExercise, loginId);
		
		System.out.println("추천 하고 싶은 운동이 있다면 그 운동의 번호를 입력해주세요.");
		System.out.println(">>> ");
		int id = sc.nextInt();
		operationService.likeExercise(id);
		System.out.println("해당 운동이 추천되었습니다!");
		
		showPlaceExercise(selectPlace, selectExercise, loginId);
	}
	
	// 입력한 헬스장/홈, 유산소/무산소에 따라 DB에서 Data 가져옴
	private void showPlaceExercise(String selectPlace, String selectExercise, String loginId) {
	    List<String> exerciseList1 = operationService.getExerciseList(selectPlace, selectExercise, loginId);
	    List<String> exerciseList2 = operationService.getExerciseList2(selectPlace, selectExercise, loginId);
	    
	    System.out.println("------------------------------------------------------------------------");
	    System.out.println("회원들이 추천한 운동");
	    System.out.println("번호 ||        이름         ||   추천수  ||             유튜브 링크           ");
	    printExerciseList(exerciseList2);
	    System.out.println("------------------------------------------------------------------------");
	    
	    System.out.println("회원님의 맞춤 운동");
	    System.out.println("번호 ||        이름         ||   추천수  ||             유튜브 링크           ");
	    printExerciseList(exerciseList1);
	    System.out.println("------------------------------------------------------------------------");
	}
	
	// 운동 리스트 출력
	private void printExerciseList(List<String> exerciseList) {
	    for (int i = 0; i < exerciseList.size(); i += 4) {
	        for (int j = 0; j < 4; j++) {
	            int index = i + j;
	            if (index < exerciseList.size()) {
	                System.out.print(exerciseList.get(index));
	                System.out.print("       ");
	            }
	        }
	        System.out.println();
	    }
	}
	
	// 식단 선택
	// 다이어트 식단 - DB 식단 2, 3번, 벌크업 식단 - DB 1, 2번
	private void showSelectEat(String loginId) {
		String select = "";
		while (true) {
			System.out.println("=====");
			System.out.println("어떤 식단을 선택하시겠습니까?");
			System.out.println("다이어트 / 벌크업");
			System.out.printf(">>> ");
			select = sc.nextLine();
			if (!select.equals("다이어트") && !select.equals("벌크업")) {
				System.out.println("올바른 명령어를 입력해주세요");
				continue;
			}
			System.out.println("입력된 명령어 >>> " + select);
			break;
		}
		switch (select) {
		case "다이어트":
			// 인자값 쓴 이유 : 각 식단 리스트 불러오는 함수가 같아서 1/2로 구분 함
			showDiet(1, loginId);
			System.out.println("추천하고 싶은 음식이 있다면 번호를 입력해주세요");
			System.out.printf(">>> ");
			int id = sc.nextInt();
			operationService.likeFood(id);
			System.out.println("추천이 완료되었습니다.");
			break;
		case "식단":
			// 인자값 쓴 이유 : 각 식단 리스트 불러오는 함수가 같아서 1/2로 구분 함
			showBulk(2, loginId);
			System.out.println("추천하고 싶은 음식이 있다면 번호를 입력해주세요");
			System.out.printf(">>> ");
			int id2 = sc.nextInt();
			operationService.likeFood(id2);
			System.out.println("추천이 완료되었습니다.");
			break;
		}
		System.out.println("=====");
	}
	
	// 다이어트 식단 가져옴
	private void showDiet(int num, String loginId) {
		List<String> foodList = operationService.getFoodList(num, loginId);

		int i = 1;
		System.out.println("회원님 맞춤 식단");
		System.out.println("번호 ||  이름  ||   추천수  || 100g 당 칼로리  || 100g 당 단백질   ");
		for (String food : foodList) {

			System.out.print(food + "");
			System.out.print("       ");
			if (i % 5 == 0) {
				System.out.println();
			}
			i++;
			if (i == 16) {// 3줄만 나열하고 끝내게
				break;
			}
		}

	}
	
	// 벌크업 식단 가져옴
	private void showBulk(int num, String loginId) {
		List<String> foodList = operationService.getFoodList(num, loginId);

		int i = 1;
		System.out.println("회원님 맞춤 벌크업 식단 ");
		System.out.println("번호 ||  이름  ||   추천수  || 100g 당 칼로리  || 100g 당 단백질    ");
		for (String food : foodList) {

			System.out.print(food + "");
			System.out.print("       ");
			if (i % 5 == 0) {
				System.out.println();
			}
			i++;
			if (i == 16) {// 3줄만 나열하고 끝내게
				break;
			}
		}
	}
}
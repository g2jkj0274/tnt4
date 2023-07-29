package tnt4.controller;

import java.util.List;
import java.util.Scanner;

import tnt4.container.Container;
import tnt4.dto.Admin;
import tnt4.dto.Exercise;
import tnt4.dto.Food;
import tnt4.dto.Member;
import tnt4.dto.NoticeBoard;
import tnt4.dto.QnABoard;
import tnt4.service.AdminService;

public class AdminController extends Controller {
	private AdminService adminService;
	private String command;

	public AdminController() {
		adminService = Container.getAdminService();
	}

	public void doAction(String command, String loginId) {
		this.command = command;
		switch (command) {
		case "exercise":
			showAdminExerciseList();
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

	// 관리자 - 운동 리스트 출력
	private void showAdminExerciseList() {
		List<Exercise> exerciseList = adminService.getAdminExerciseList();
		System.out.println("===== 운동 리스트 =====");
		for (Exercise exercise : exerciseList) {
	        System.out.println("No." + exercise.getId() + " / 이름 : " + exercise.getName());
	    }
	}

	// 관리자 - 식단 리스트 출력
	private void showAdminFoodList() {
		List<Food> foodList = adminService.getAdminFoodList();
		System.out.println("===== 식단 리스트 =====");
		for (Food food : foodList) {
	        System.out.println("No." + food.id + " / 이름 : " + food.getName());
	    }
	}

	// 관리자 - 공지사항 리스트 출력
	private void showAdminNoticeList() {
		List<NoticeBoard> noticeList = adminService.getAdminNoticeList();
		System.out.println("===== 공지사항 리스트 =====");
		for (NoticeBoard notice : noticeList) {
	        System.out.println("No." + notice.getId() + " / 제목 : " + notice.getName());
	    }
	}

	// 관리자 - QnA 리스트 출력
	private void showAdminQnAList() {
		List<QnABoard> qnaList = adminService.getAdminQnAList();
		System.out.println("===== QnA 리스트 =====");
		for (QnABoard qna : qnaList) {
	        System.out.println("No." + qna.getId() + " / 사용자 질문 : " + qna.getUserQuestionName());
	    }
	}

	// 관리자 - 멤버 리스트 출력
	private void showAdminMemberList() {
		List<Member> adminList = adminService.getAdminMemberList();
		System.out.println("===== 멤버 리스트 =====");
		for (Member admin : adminList) {
			System.out.println("ID : " + admin.getId() + " / 이름 : " + admin.getName());
		}
	}
}
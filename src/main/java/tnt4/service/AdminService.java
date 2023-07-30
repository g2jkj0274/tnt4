package tnt4.service;

import java.util.List;

import tnt4.container.Container;
import tnt4.dao.AdminDao;
import tnt4.dto.Food;
import tnt4.dto.Member;
import tnt4.dto.NoticeBoard;
import tnt4.dto.QnABoard;

public class AdminService {
	private AdminDao adminDao;

	public AdminService() {
		adminDao = Container.adminDao;
	}

	// 관리자 - 운동 리스트 반환
	public List<String> getAdminExerciseList(String selectPlace, String selectExercise) {
		return adminDao.getAdminExerciseList(selectPlace, selectExercise);
	}

	// 관리자 - 식단 리스트 반환
	public List<Food> getAdminFoodList() {
		return adminDao.getAdminFoodList();
	}

	// 관리자 - 공지사항 리스트 반환
	public List<NoticeBoard> getAdminNoticeList() {
		return adminDao.getAdminNoticeList();
	}

	// 관리자 - QnA 리스트 반환
	public List<QnABoard> getAdminQnAList() {
		return adminDao.getAdminQnAList();
	}

	// 관리자 - 멤버 리스트 반환
	public List<Member> getAdminMemberList() {
		return adminDao.getAdminMemberList();
	}
	
	// 운동 쓰기
	public void writeAdminExercise(String writePlace, String writeExercise, String writeName, String writeLink, int writeBmiId) {
		adminDao.writeAdminExercise(writePlace, writeExercise, writeName, writeLink, writeBmiId, 0);
	}
	
	// 식단 쓰기
	public void writeAdminFood(String writeFoodName, int writeFoodKal, int writeFoodPro, int writeFoodBmiId) {
		adminDao.writeAdminFood(writeFoodName, writeFoodKal, writeFoodPro, writeFoodBmiId, 0);
	}
	
	// 공지사항 쓰기
	public void writeAdminNotice(String writeNoticeName, String writeNoticeDetail) {
		adminDao.writeAdminNotice(writeNoticeName, writeNoticeDetail);
	}
	
	public void modifyAdminExercise(int itemId, String modifyName, String modifyLocation, String modifyKind, String modifyLink) {
		adminDao.modifyAdminExercise(itemId, modifyName, modifyLocation, modifyKind, modifyLink);
	}

	public void deleteAdminSelectItem(String selectList, int itemId) {
		adminDao.deleteAdminSelectItem(selectList, itemId);
	}
}

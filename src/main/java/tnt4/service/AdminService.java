package tnt4.service;

import java.util.List;

import tnt4.container.Container;
import tnt4.dao.AdminDao;
import tnt4.dto.Admin;
import tnt4.dto.Exercise;
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
    public List<Exercise> getAdminExerciseList() {
        return adminDao.getAdminExerciseList();
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
}

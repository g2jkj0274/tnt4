package tnt4.service;

import java.util.List;

import tnt4.container.Container;
import tnt4.dao.ArticleDao;
import tnt4.dto.NoticeBoard;
import tnt4.dto.QnABoard;
public class ArticleService {
	private ArticleDao articleDao;
	public ArticleService() {
		articleDao = Container.articleDao;
	}
	public List<String> getExerciseList(String selectPlace, String selectExercise) {
		return articleDao.getExerciseList(selectPlace, selectExercise);
	}
	public String getFoodList() {
		return articleDao.getFoodList();
	}
	public List<NoticeBoard> getNoticeBoard() {
		return articleDao.getNoticeBoard();
	}
	public List<QnABoard> getQnABoard() {
		return articleDao.getQnABoard();
	}
}
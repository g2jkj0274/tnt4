package tnt4.service;

import tnt4.container.Container;
import tnt4.dao.ArticleDao;
public class ArticleService {
	private ArticleDao articleDao;
	public ArticleService() {
		articleDao = Container.articleDao;
	}
	public void getExerciseList(String selectPlace, String selectExercise) {
		articleDao.getExerciseList();
	}
}
package tnt4.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tnt4.container.Container;
import tnt4.db.DBConnection;
import tnt4.dto.Article;
public class ArticleDao extends Dao {
	public List<Article> articles;
	private DBConnection dbConnection;
	
	public ArticleDao() {
		articles = new ArrayList<>();
		dbConnection = Container.getDBConnection();
	}

	public void getExerciseList() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("name = '%s', ", exercise.name));
		sb.append(String.format("location = '%s', ", exercise.location));
		sb.append(String.format("kind = '%s', ", exercise.kind));
		sb.append(String.format("link = '%s' ", exercise.link));
	}
}

package tnt4.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tnt4.container.Container;
import tnt4.db.DBConnection;
import tnt4.dto.Article;
public class ArticleDao extends Dao {
	public List<Article> articles;
	private DBConnection dbConnection;
	private Statement statement;
	private ResultSet resultSet;
	private Connection connection;
	private String url = "jdbc:mysql://localhost:3306/tnt4";
	private String userName = "root";
	private String password = "";
	
	public ArticleDao() {
		articles = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(url, userName, password);
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public String getExerciseList(String selectPlace, String selectExercise) {
		try {
			resultSet = statement.executeQuery("SELECT `name` FROM `exercise`\r\n"
					+ "WHERE location = '헬스장' AND kind = '무산소';");
			String answer = resultSet.getString("name");
			return answer;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "실패";
		
		
		
	}
}

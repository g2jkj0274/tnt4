package tnt4.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
//test용, 기본 틀, [삭제 X]
	/*
	public String getExerciseList(String selectPlace, String selectExercise) {
		try {
			resultSet = statement.executeQuery("SELECT `name` FROM `exercise`\r\n"
					+ "WHERE location = '헬스장' AND kind = '무산소';");
			resultSet.next();
			String answer = resultSet.getString("name");
			return answer;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "실패";
	}
	*/
	
	public List<String> getExerciseList(String selectPlace, String selectExercise) {
		List<String> exerciseList = new ArrayList<>();

		try {
			String query = "SELECT `name` FROM `exercise` WHERE location = ? AND kind = ?";
			// Statement 클래스보다 기능이 향상된 클래스
			// 코드의 안정성과 가독성이 높다.
			// 인자 값으로 전달이 가능하다.
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, selectPlace);
			preparedStatement.setString(2, selectExercise);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String name = resultSet.getString("name");
				exerciseList.add(name);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return exerciseList;
	}
	
	public String getFoodList() {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM food ");
			while (resultSet.next()) {
				// 3 -> 5, 4-> 6
				System.out.println("이름 : " + resultSet.getString(5) + " // 칼로리 : " + resultSet.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "실패";
	}
}

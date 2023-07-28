package tnt4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tnt4.container.Container;
import tnt4.db.DBConnection;
import tnt4.dto.NoticeBoard;
import tnt4.dto.QnABoard;
public class ArticleDao extends Dao {
	private DBConnection dbConnection;
	private Statement statement;
	private ResultSet resultSet;
	private Connection connection;

	public ArticleDao() {
	    dbConnection = Container.getDBConnection();
	}

	// 운동 리스트 가져옴 (selectPlace : 헴스장/홈트, selectExercise : 유산소/무산소)
	public List<String> getExerciseList(String selectPlace, String selectExercise) {
		List<String> exerciseList = new ArrayList<>();
		
		try {
			String query = "SELECT `name` FROM `exercise` WHERE location = ? AND kind = ?";
			// Statement 클래스보다 기능이 향상된 클래스
			// 코드의 안정성과 가독성이 높다.
			// 인자 값으로 전달이 가능하다.
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, selectPlace);
			preparedStatement.setString(2, selectExercise);

			ResultSet resultSet = preparedStatement.executeQuery();

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
	
	// 식단 리스트
	public String getFoodList() {
	    try {
	        Statement statement = dbConnection.getConnection().createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM food ");
	        while (resultSet.next()) {
	            System.out.println("이름 : " + resultSet.getString("name") + " // 칼로리 : " + resultSet.getString("kal"));
	        }
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return "실패";
	}
	
	// 공지사항
	public List<NoticeBoard> getNoticeBoard() {
	    List<NoticeBoard> noticeBoardList = new ArrayList<>();

	    try {
	        String query = "SELECT `id`, `name`, `detail` FROM `noticeBoard`";
	        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String name = resultSet.getString("name");
	            String detail = resultSet.getString("detail");
	            NoticeBoard announcement = new NoticeBoard(id, name, detail);
	            noticeBoardList.add(announcement);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return noticeBoardList;
	}

	public List<QnABoard> getQnABoard() {
		List<QnABoard> QnABoardList = new ArrayList<>();

	    try {
	    	String query = "SELECT `id`, `userQuestionName`, `userQuestionText`, `adminAnswerName`, `adminAnswerText` FROM `QnA`";
	        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String userQuestionName = resultSet.getString("userQuestionName");
	            String userQuestionText = resultSet.getString("userQuestionText");
	            String adminAnswerName = resultSet.getString("adminAnswerName");
	            String adminAnswerText = resultSet.getString("adminAnswerText");
	            QnABoard announcement = new QnABoard(id, userQuestionName, userQuestionText, adminAnswerName, adminAnswerText);
	            QnABoardList.add(announcement);
	            
	            // 로그 출력
	            System.out.println("Loaded QnABoard: " + announcement.toString());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return QnABoardList;
	}
}

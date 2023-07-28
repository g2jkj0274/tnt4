package tnt4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tnt4.container.Container;
import tnt4.controller.Session;
import tnt4.db.DBConnection;
import tnt4.dto.Exercise;
import tnt4.dto.Food;
import tnt4.dto.Member;
import tnt4.dto.NoticeBoard;
import tnt4.dto.QnABoard;
import tnt4.service.MemberService;

public class OperationDao extends Dao {
	private DBConnection dbConnection;
	private Statement statement;
	private ResultSet resultSet;
	private Connection connection;
	private Exercise exercise;
	private Food food;
	private static Session session;

	private MemberDao memberDao;
	private MemberService memberService;
	private Member member;

	public OperationDao() {
		dbConnection = Container.getDBConnection();
		session = Container.getSession();
		memberService = Container.memberService;
	}

	// 운동 리스트 가져옴 (selectPlace : 헴스장/홈트, selectExercise : 유산소/무산소)
	public List<String> getExerciseList(String selectPlace, String selectExercise, String loginId) {
		List<String> exerciseList = new ArrayList<>();

		memberService = Container.memberService;
		member = memberService.getMemberByLoginId(loginId);
		System.out.println(member.bmiId);

		try {
			String query = "SELECT `name`,`id`,`like`,`link` FROM `exercise` WHERE location = ? AND kind = ? AND bmiId = ?";
			// Statement 클래스보다 기능이 향상된 클래스
			// 코드의 안정성과 가독성이 높다.
			// 인자 값으로 전달이 가능하다.
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, selectPlace);
			preparedStatement.setString(2, selectExercise);
			preparedStatement.setLong(3, member.bmiId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String name = resultSet.getString("name");
				exerciseList.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exerciseList;
	}

	public List<String> getExerciseList2(String selectPlace, String selectExercise, String loginId) {
		List<String> exerciseList = new ArrayList<>();
		memberService = Container.memberService;
		member = memberService.getMemberByLoginId(loginId);
		System.out.println(member.bmiId);
		try {
			String query = "SELECT `name`,`id`,`like`,`link` FROM `exercise` WHERE location = ? AND kind = ? ORDER BY `like` DESC";
			// Statement 클래스보다 기능이 향상된 클래스
			// 코드의 안정성과 가독성이 높다.
			// 인자 값으로 전달이 가능하다.
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, selectPlace);
			preparedStatement.setString(2, selectExercise);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String name = resultSet.getString("name");
				String name2 = resultSet.getString("id");
				String name3 = resultSet.getString("like");
				String name4 = resultSet.getString("link");
				exerciseList.add(name2);
				exerciseList.add(name);
				exerciseList.add(name3);
				exerciseList.add(name4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exerciseList;
	}

	// 식단 리스트
	public List<String> getFoodList(int num, String loginId) {
		List<String> foodList = new ArrayList<>();

		if (num == 1) { // num 1 은 다이어트 식단
			num = 3; // bmiId 가 3인 사람 (과체중)
		} else if (num == 2) {// num 2는 벌크업 식단
			num = 1; // bmiId 가 1인 사람 (저체중)
		}
		try {
			String query = "SELECT `id`,`name`,`like`,`kal`,`pro` FROM `food` WHERE bmiId = ? ORDER BY `like` DESC";

			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setLong(1, num);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String name1 = resultSet.getString("id");
				String name2 = resultSet.getString("name");
				String name3 = resultSet.getString("like");
				String name4 = resultSet.getString("kal");
				String name5 = resultSet.getString("pro");
				foodList.add(name1);
				foodList.add(name2);
				foodList.add(name3);
				foodList.add(name4);
				foodList.add(name5);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foodList;
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
				QnABoard announcement = new QnABoard(id, userQuestionName, userQuestionText, adminAnswerName,
						adminAnswerText);
				QnABoardList.add(announcement);

				// 로그 출력
				System.out.println("Loaded QnABoard: " + announcement.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return QnABoardList;
	}

	public void upLikeExercise(int id) {
		exercise = getExerciseById(id); // 운동의 추천수가 필요해서 불러와야함
		Member loginedMember = session.getLoginedMember();

		StringBuilder sb = new StringBuilder();

		sb.append(String.format("UPDATE `exercise` "));
		sb.append(String.format("SET `like` = '%d' ", exercise.like + 1));
		sb.append(String.format("WHERE `id` = '%d' ", id));

		dbConnection.insert(sb.toString());
	}

	// Exercise 를 갖고 오는 함수 추가
	public Exercise getExerciseById(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `exercise` "));
		sb.append(String.format("WHERE id = '%d' ", id));
		Map<String, Object> row = dbConnection.selectRow(sb.toString());
		if (row.isEmpty()) {
			return null;
		}
		return new Exercise(row);
	}

	public void upLikeFood(int id) {
		food = getFoodById(id);

		StringBuilder sb = new StringBuilder();

		sb.append(String.format("UPDATE `food` "));
		sb.append(String.format("SET `like` = '%d' ", food.like + 1));
		sb.append(String.format("WHERE `id` = '%d' ", id));

		dbConnection.insert(sb.toString());
	}

	public Food getFoodById(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `food` "));
		sb.append(String.format("WHERE id = '%d' ", id));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());

		if (row.isEmpty()) {
			return null;
		}
		return new Food(row);
	}
}

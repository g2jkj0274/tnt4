package tnt4.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tnt4.container.Container;
import tnt4.db.DBConnection;
import tnt4.dto.Food;
import tnt4.dto.Member;
import tnt4.dto.NoticeBoard;
import tnt4.dto.QnABoard;

public class AdminDao {
	private DBConnection dbConnection;

	public AdminDao() {
		dbConnection = Container.getDBConnection();
	}

	// 관리자 - 운동 리스트 반환
	public List<String> getAdminExerciseList(String selectPlace, String selectExercise) {
		List<String> exerciseList = new ArrayList<>();

		try {
			String query = "SELECT `id`, `name` FROM `exercise` WHERE location = ? AND kind = ? ";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, selectPlace);
			preparedStatement.setString(2, selectExercise);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String exerciseInfo = "No." + id + " / " + name;
				exerciseList.add(exerciseInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exerciseList;
	}

	// 관리자 - 식단 리스트 반환
	public List<Food> getAdminFoodList() {
		List<Food> foodList = new ArrayList<>();

		try {
			String query = "SELECT `id`, `name` FROM `food`";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Food announcement = new Food(id, name);
				foodList.add(announcement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foodList;
	}

	// 관리자 - 공지사항 리스트 반환
	public List<NoticeBoard> getAdminNoticeList() {
		List<NoticeBoard> noticeList = new ArrayList<>();

		try {
			String query = "SELECT `id`, `name` FROM `noticeBoard`";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				NoticeBoard announcement = new NoticeBoard(id, name);
				noticeList.add(announcement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeList;
	}

	// 관리자 - QnA 리스트 반환
	public List<QnABoard> getAdminQnAList() {
		List<QnABoard> noticeList = new ArrayList<>();

		try {
			String query = "SELECT `id`, `userQuestionName` FROM `qna`";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("userQuestionName");
				QnABoard announcement = new QnABoard(id, name);
				noticeList.add(announcement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeList;
	}

	// 관리자 - 멤버 리스트 반환
	public List<Member> getAdminMemberList() {
		List<Member> memberList = new ArrayList<>();

		try {
			String query = "SELECT `id`, `name` FROM `member`";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Member member = new Member(id, name);
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return memberList;
	}

	// 운동 리스트에 아이템 추가
	public void writeAdminExercise(String writePlace, String writeExercise, String writeName, String writeLink,
			int writeBmiId, int writeLike) {
		try {
			String query = "INSERT INTO `exercise` (`location`, `kind`, `name`, `link`, `bmiId`, `like`) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, writePlace);
			preparedStatement.setString(2, writeExercise);
			preparedStatement.setString(3, writeName);
			preparedStatement.setString(4, writeLink);
			preparedStatement.setInt(5, writeBmiId);
			preparedStatement.setInt(6, writeLike);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 식단 리스트에 아이템 추가
	public void writeAdminFood(String writeFoodName, int writeFoodKal, int writeFoodPro, int writeFoodBmiId, int writeFoodLike) {
	    try {
	        String query = "INSERT INTO `food` (`name`, `kal`, `pro`, `bmiId`, `like`) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
	        preparedStatement.setString(1, writeFoodName);
	        preparedStatement.setInt(2, writeFoodKal);
	        preparedStatement.setInt(3, writeFoodPro);
	        preparedStatement.setInt(4, writeFoodBmiId);
	        preparedStatement.setInt(5, writeFoodLike);

	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	// 공지사항 쓰기
	public void writeAdminNotice(String writeNoticeName, String writeNoticeDetail) {
	    try {
	        String query = "INSERT INTO `noticeBoard` (`name`, `detail`, `updateDate`) VALUES (?, ?, NOW())";
	        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
	        preparedStatement.setString(1, writeNoticeName);
	        preparedStatement.setString(2, writeNoticeDetail);

	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void modifyAdminExercise(int itemId, String modifyName, String modifyLocation, String modifyKind,
			String modifyLink) {
		try {
			String query = "UPDATE `exercise` SET `name` = ?, `location` = ?, `kind` = ?, `link` = ? WHERE `id` = ?";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, modifyName);
			preparedStatement.setString(2, modifyLocation);
			preparedStatement.setString(3, modifyKind);
			preparedStatement.setString(4, modifyLink);
			preparedStatement.setInt(5, itemId);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean deleteAdminSelectItem(String selectList, int itemId) {
		try {
			String tableName;
			switch (selectList) {
			case "exercise":
				tableName = "exercise";
				break;
			case "food":
				tableName = "food";
				break;
			case "notice":
				tableName = "noticeBoard";
				break;
			case "QnA":
				tableName = "qna";
				break;
			case "member":
				tableName = "member";
				break;
			default:
				return false;
			}

			String query = "DELETE FROM `" + tableName + "` WHERE `id` = ?";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, itemId);

			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
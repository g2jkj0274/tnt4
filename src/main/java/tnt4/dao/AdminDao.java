package tnt4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tnt4.container.Container;
import tnt4.db.DBConnection;
import tnt4.dto.Admin;
import tnt4.dto.Exercise;
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
    public List<Exercise> getAdminExerciseList() {
    	List<Exercise> exerciseList = new ArrayList<>();

        try {
            String query = "SELECT `id`, `name` FROM `exercise`";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Exercise announcement = new Exercise(id, name);
                exerciseList.add(announcement);
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
}
package tnt4.container;

import java.util.Scanner;

import tnt4.controller.Session;
import tnt4.dao.ArticleDao;
import tnt4.dao.MemberDao;
import tnt4.db.DBConnection;
import tnt4.service.ArticleService;
import tnt4.service.MemberService;

public class Container {
	public static Scanner sc;
	public static Session session;
	public static DBConnection dbConnection;
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ArticleService articleService;
	public static MemberService memberService;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		articleService = new ArticleService();
		memberService = new MemberService();
	}

	public static Scanner getScanner() {
		if ( sc == null ) {
			sc = new Scanner(System.in);
		}

		return sc;
	}

	public static DBConnection getDBConnection() {
		if ( dbConnection == null ) {
			dbConnection = new DBConnection();
		}
		
		return dbConnection;
	}

	public static Session getSession() {
		if ( session == null ) {
			session = new Session();
		}
		
		return session;
	}
}
package tnt4.service;

import java.util.List;
import tnt4.container.Container;
import tnt4.dao.ArticleDao;
import tnt4.dto.Article;
import tnt4.dto.Board;
public class ArticleService {
	private ArticleDao articleDao;
	public ArticleService() {
		articleDao = Container.articleDao;
	}
	public List<Article> getForPrintArticles(String boardCode, String searchkeyword) {
		return articleDao.getForPrintArticles(boardCode, searchkeyword);
	}
	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}
	public int write(int memberId, int boardId, String title, String body) {
		Article article = new Article(memberId, boardId, title, body);
		return articleDao.write(article);
	}
	public List<Article> getForPrintArticles() {
		return articleDao.getForPrintArticles(null);
	}
	public List<Article> getArticles() {
		return articleDao.getArticles();
	}
	public Board getBoard(int id) {
		return articleDao.getBoard(id);
	}
	public Article getForPrintArticle(int id) {
		return articleDao.getForPrintArticle(id);
	}
	public void modify(int id, String title, String body) {
		articleDao.modify(id, title, body);
	}
	public void delete(int id) {
		articleDao.delete(id);
	}
}


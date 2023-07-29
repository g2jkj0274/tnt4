package tnt4.dto;

public class NoticeBoard {
	private int id;
    private String name;
    private String detail;

    public NoticeBoard(int id, String name, String detail) {
        this.id = id;
        this.name = name;
        this.detail = detail;
    }
    
    public NoticeBoard(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
	public String getName() {
		return name;
	}
    
    public String getDetail() {
        return detail;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    public String toString() {
        return "No." + id + " / 제목 : " + name + "\n" + detail;
    }

	public int getId() {
		return id;
	}
}

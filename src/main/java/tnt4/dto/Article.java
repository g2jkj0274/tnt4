package tnt4.dto;

import lombok.Data;
@Data
public class Article extends Dto {
	public Stirng name;
	public Stirng location;
	public String kind;
	public String link;

	public Exercise(Stirng name, Stirng location, String kind, String link) {
		this.name = name;
		this.location = location;
		this.kind = kind;
		this.link = link;
	}
}
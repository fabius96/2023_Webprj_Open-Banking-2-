package vo;

import java.sql.Timestamp;

public class PostDTO {

	private int id;
	private String writer;
	private String title;
	private String content;
	private Timestamp write_Date;
	private int views;
	private int category;
	private int levelNum;
	private int blank;

	public PostDTO(String writer, String title, String content) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.category = 0;
	}

	public PostDTO(int id, String writer, String title, String content, Timestamp writeDate, int views, int category,
			int levelNum, int blank) {
		super();
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.write_Date = writeDate;
		this.views = views;
		this.category = category;
		this.levelNum = levelNum;
		this.blank = blank;
	}

	public PostDTO(String writer, String title, String content, int category) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.category = category;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getWriteDate() {
		return write_Date;
	}

	public void setWriteDate(Timestamp writeDate) {
		this.write_Date = writeDate;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getLevelNum() {
		return levelNum;
	}

	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}

	public int getBlank() {
		return blank;
	}

	public void setBlank(int blank) {
		this.blank = blank;
	}
}

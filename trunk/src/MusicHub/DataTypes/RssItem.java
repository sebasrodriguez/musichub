package MusicHub.DataTypes;

import java.io.Serializable;
import java.util.Date;

public class RssItem implements Serializable {

	private static final long serialVersionUID = -2190540458350771464L;
	private String title;
	private Date date;
	private String intro;
	private String content;
	private int votes;
	private String imageUrl;
	private String itemUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	public String getItemUrl(){
		return this.itemUrl;
	}
	public void setItemUrl(String itemUrl){
		this.itemUrl = itemUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}

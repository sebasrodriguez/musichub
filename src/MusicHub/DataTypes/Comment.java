package MusicHub.DataTypes;

import java.io.Serializable;

public class Comment implements Serializable {

	private static final long serialVersionUID = -8142398701252295800L;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}

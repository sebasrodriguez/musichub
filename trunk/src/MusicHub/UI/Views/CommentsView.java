package MusicHub.UI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import org.havi.ui.HDefaultTextLayoutManager;
import org.havi.ui.HState;
import org.havi.ui.HText;
import org.havi.ui.HVisible;

import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssItem;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.ViewManager;
import MusicHub.UI.Contracts.IKeyboardReceiver;
import MusicHub.UI.Controls.Keyboard;
import MusicHub.UI.Controls.RoundRectBox;

public class CommentsView extends BasicContainer implements IKeyboardReceiver {

	private static final long serialVersionUID = 1L;
	private HText title;
	private HText comment;
	private Keyboard keyboard;
	private boolean isFinished;
	private RssItem rssItem;
	private String tempComment;
	private ContentView contentView;

	public CommentsView(RssItem rssItem, ContentView contentView) {
		super("../assets/keyboard-view-bg.png");
		
		
		this.rssItem = rssItem;
		this.contentView = contentView;

		isFinished = false;
		
		title = new HText("Comentando: " + rssItem.getTitle(), new Font("Tiresias", Font.BOLD, 20),
				Color.WHITE, Color.WHITE, new HDefaultTextLayoutManager());
		title.setBounds(150, 80, 1000, 20);
		title.setVisible(true);
		title.setHorizontalAlignment(HVisible.HALIGN_LEFT);
		title.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);

		comment = new HText("", new Font("Tiresias", Font.PLAIN, 20), Color.BLACK, Color.WHITE,
				new HDefaultTextLayoutManager());
		comment.setBounds(235, 115, 545, 170);
		comment.setVisible(true);
		comment.setVerticalAlignment(HVisible.VALIGN_TOP);
		comment.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		RoundRectBox box = new RoundRectBox(210, 100, 600, 200, Color.DARK_GRAY);
		keyboard = new Keyboard(this, 130, 150);

		this.add(title);
		this.add(comment);
		this.add(box);
		this.add(keyboard);

		this.popToFront(box);
		this.popToFront(title);
		this.popToFront(comment);
		this.popToFront(keyboard);

		this.keyboard.addKeyListener(keyboard);
	}

	@Override
	public void paint(Graphics g) {
		this.keyboard.requestFocus();
		super.paint(g);
	}

	@Override
	public void keyboardText(String text) {
		if (text.length() < 300) {
			tempComment = text;
			char[] chars = text.toCharArray();
			StringBuilder finalComment = new StringBuilder();

			for (int i = 0; i < chars.length; i++) {
				if ((i % 47) == 0) {
					finalComment.append("\n");
				}
				finalComment.append(chars[i]);
			}

			this.comment.setTextContent(finalComment.toString(), HState.ALL_STATES);
		}
	}

	@Override
	public void unamangedKeyboardKey(int keyCode) {
		switch (keyCode) {
		case ControlKeyConstants.GREEN:
			if (!isFinished) {
				ServiceLocator.getRssManager().addComment(this.rssItem, this.tempComment);
				comment.setVerticalAlignment(HVisible.VALIGN_CENTER);
				comment.setHorizontalAlignment(HVisible.HALIGN_CENTER);
				comment.setTextContent(
						"Comentario enviado con exito\n\n Presione VERDE para continuar",
						HState.ALL_STATES);
				isFinished = true;
			}
			else {
				Object[] args = { this.contentView };
				ViewManager.getInstance().changeView("ContentView-CommentsView", args);
			}
			break;
		case ControlKeyConstants.RED:
			if (!isFinished) {
				Object[] args = { this.contentView };
				ViewManager.getInstance().changeView("ContentView-CommentsView", args);
			}
			break;
		case ControlKeyConstants.EXIT:
			ViewManager.getInstance().exitApplication();
			break;
		}
	}
}
package MusicHub.UI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import org.havi.ui.HDefaultTextLayoutManager;
import org.havi.ui.HState;
import org.havi.ui.HText;
import org.havi.ui.HVisible;

import MusicHub.Application.ServiceLocator;
import MusicHub.DataTypes.RssFeed;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.ControlKeyConstants;
import MusicHub.UI.ViewManager;
import MusicHub.UI.Contracts.IKeyboardReceiver;
import MusicHub.UI.Controls.Keyboard;
import MusicHub.UI.Controls.RoundRectBox;

public class AddChannelView extends BasicContainer implements IKeyboardReceiver {

	private static final long serialVersionUID = 1L;
	private HText title;
	private HText url;
	private Keyboard keyboard;
	private RoundRectBox messageBox;
	private HText messageText;

	public AddChannelView() {
		super("../assets/add-channel-view-bg.png");

		HText viewTitle = new HText("Agregar canal nuevo");
		viewTitle.setFont(new Font("tiresias", Font.BOLD, 18));
		viewTitle.setForeground(Color.WHITE);
		viewTitle.setBounds(0, 47, this.getWidth(), 50);
		viewTitle.setBackgroundMode(HVisible.NO_BACKGROUND_FILL);

		title = new HText("Url: ", new Font("Tiresias", Font.BOLD, 20), Color.BLACK, Color.WHITE,
				new HDefaultTextLayoutManager());
		title.setBounds(230, 135, 100, 20);
		title.setVisible(true);
		title.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		url = new HText("", new Font("Tiresias", Font.PLAIN, 20), Color.BLACK, Color.WHITE,
				new HDefaultTextLayoutManager());
		url.setBounds(280, 135, 500, 20);
		url.setVisible(true);
		url.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		RoundRectBox box = new RoundRectBox(220, 120, 600, 50, Color.DARK_GRAY);
		keyboard = new Keyboard(this, 130, 130);

		this.add(viewTitle);
		this.add(title);
		this.add(url);
		this.add(box);
		this.add(keyboard);

		this.popToFront(viewTitle);
		this.popToFront(box);
		this.popToFront(title);
		this.popToFront(url);
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
		this.url.setTextContent(text, HState.NORMAL_STATE);
	}

	@Override
	public void unamangedKeyboardKey(int keyCode) {
		switch (keyCode) {
		case ControlKeyConstants.GREEN:
			RssFeed feed = ServiceLocator.getRssManager().addFeed(this.url.getTextContent(HState.NORMAL_STATE));
			if (feed != null) {
				this.showMessage("Canal agregado", Color.GREEN);
				this.url.setTextContent("", HState.NORMAL_STATE);
			}
			else {
				this.showMessage("Url incorrecta", Color.RED);
			}
			break;
		case ControlKeyConstants.RED:
			ViewManager.getInstance().changeView("ChannelsView", null);
			break;
		}
	}

	private void showMessage(String message, Color color) {
		if (messageBox != null && messageText != null) {
			this.remove(messageBox);
			this.remove(messageText);
		}
		messageBox = new RoundRectBox(260, 190, 500, 40, color);
		messageText = new HText(message, new Font("Tiresias", Font.BOLD, 17), Color.BLACK, Color.WHITE,
				new HDefaultTextLayoutManager());
		messageText.setBounds(265, 200, 480, 20);
		messageText.setVisible(true);
		messageText.setHorizontalAlignment(HVisible.HALIGN_CENTER);

		this.add(messageBox);
		this.add(messageText);

		this.popToFront(messageBox);
		this.popToFront(messageText);

		this.repaint();
	}
}

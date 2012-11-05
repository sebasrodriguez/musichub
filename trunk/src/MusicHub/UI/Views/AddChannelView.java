package MusicHub.UI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import org.havi.ui.HDefaultTextLayoutManager;
import org.havi.ui.HText;
import org.havi.ui.HVisible;
import MusicHub.UI.BasicContainer;
import MusicHub.UI.Contracts.IKeyboardReceiver;
import MusicHub.UI.Controls.Keyboard;

public class AddChannelView extends BasicContainer implements IKeyboardReceiver {

	private static final long serialVersionUID = 1L;
	private HText title;
	private HText url;
	private Keyboard keyboard;

	public AddChannelView() {
		title = new HText("Url: ", new Font("Tiresias", Font.BOLD, 20), Color.BLACK, Color.WHITE,
				new HDefaultTextLayoutManager());
		title.setBounds(105, 55, 100, 20);
		title.setVisible(true);
		title.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		url = new HText("http://www.elpais.com.uy", new Font("Tiresias", Font.PLAIN, 20),
				Color.BLACK, Color.WHITE, new HDefaultTextLayoutManager());
		url.setBounds(150, 55, 500, 20);		
		url.setVisible(true);
		url.setHorizontalAlignment(HVisible.HALIGN_LEFT);

		keyboard = new Keyboard(this, 10, 70);
		
		this.add(title);
		this.add(url);
		this.add(keyboard);

		this.popToFront(title);
		this.popToFront(url);
		this.popToFront(keyboard);
		
		this.keyboard.requestFocus();
		this.keyboard.addKeyListener(keyboard);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRoundRect(100, 100, 300, 50, 15, 15);
		super.paint(g);
	}

	@Override
	public void keyboardText(String text) {
	}

	@Override
	public void unamangedKeyboardKey(int keyCode) {
	}
}

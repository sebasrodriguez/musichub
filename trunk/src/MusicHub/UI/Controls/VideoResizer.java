package MusicHub.UI.Controls;

import java.awt.Rectangle;
import javax.media.Player;
import javax.tv.media.AWTVideoSize;
import javax.tv.media.AWTVideoSizeControl;
import javax.tv.service.selection.ServiceContentHandler;
import javax.tv.service.selection.ServiceContextFactory;
import javax.tv.xlet.XletContext;

public class VideoResizer {

	AWTVideoSize originalSize;
	AWTVideoSizeControl videoControl;
	XletContext context;
	private static VideoResizer instance = null;
	
	public static VideoResizer getInstance(XletContext ctx){
		if(instance == null){
			instance = new VideoResizer(ctx);
		}
		return instance;
	}

	private VideoResizer(XletContext ctx) {
		this.context = ctx;
		ServiceContextFactory serviceContextFactory = null;
		javax.tv.service.selection.ServiceContext serviceContext = null;
		ServiceContentHandler[] serviceContentHandler;
		Player player;
		AWTVideoSizeControl awtVideoSizeControl;

		try {
			serviceContextFactory = ServiceContextFactory.getInstance();
			serviceContext = serviceContextFactory.getServiceContext(this.context);
		}
		catch (Exception ex) {
			System.out.println("Error loading service context factory");
		}
		if (serviceContext != null) {
			serviceContentHandler = serviceContext.getServiceContentHandlers();
			player = null;
			if (serviceContentHandler.length > 0) {
				player = (Player) serviceContentHandler[0];
			}
			if (player != null) {
				awtVideoSizeControl = (AWTVideoSizeControl) player
						.getControl("javax.tv.media.AWTVideoSizeControl");
				this.originalSize = awtVideoSizeControl.getDefaultSize();
				this.videoControl = awtVideoSizeControl;
			}
		}
	}

	public void changeVideoSize(Rectangle start, Rectangle end) {
		if (this.videoControl != null) {
			this.videoControl.setSize(new AWTVideoSize(start, end));
		}
	}

	public boolean returnToOriginalSize(){
		return this.videoControl.setSize(this.originalSize);
	}
}

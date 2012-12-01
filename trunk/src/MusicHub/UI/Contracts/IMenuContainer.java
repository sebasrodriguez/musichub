package MusicHub.UI.Contracts;

import MusicHub.UI.Controls.UOptionItem;

public interface IMenuContainer {

	void selectedOption(UOptionItem selectedOption);

	void stepedOnOption(UOptionItem option);

	void unmanagedMenuKey(int keyCode);
}

package view;

import application.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LobbyViewController {
	
	GameController gameController;
	public GameController setMainController;

	public void setMainController(GameController gameController) {
		this.gameController = gameController;
	}
	
	@FXML
	public void btnQuestionPressed(ActionEvent event) {
		gameController.lobbyNotifyDone();
		//gameController.showQuestionView(null);
		//gameController.startSpielrunde();
		//gameController.spielrundeStarten();
	}
}

package view;

import application.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AnswerViewController {
	
	GameController gameController;
	
	@FXML
	public void weiterToNextQuestion(ActionEvent event) {
		if (gameController.getRundenCounter() > 2) {
			gameController.showEndScene();
		} else {
			gameController.answerNotifyDone();
		}
		
	}
	
	public void setMainController(GameController mainController) {
		this.gameController = mainController;
	}
}


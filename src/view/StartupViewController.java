package view;

import application.GameController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class StartupViewController{

	//application getter setter
	GameController gameController;
	
	@FXML
	public void btnLobbyPressed(ActionEvent event) {
		gameController.showLobbyView();
		//gameController.spielrundeStarten();
	}
	
	public void setMainController(GameController gameController) {
		this.gameController = gameController;
	}
	
}

package view;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import application.GameController;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class QuestionViewController implements Initializable {

	GameController gameController;
	@FXML
	private Label lblRestzeit;
	private Label lblFrage;
	private Label lblAntwort1;
	private Label lblAntwort2;
	private Label lblAntwort3;
	private IntegerProperty restzeit;
	private int btnClickCounter = 0;
	private static int TIMEOUT = 10;
	private Timer timer;
	long tStart;
	
	public IntegerProperty getRestzeit() {
		if (restzeit == null)
			restzeit = new SimpleIntegerProperty();
		return restzeit;
	}
	
	public void setMainController(GameController mainController) {
		this.gameController = mainController;
	}
	
	//setQuestion - Methode
	//public void setQuestion() {}
	
	/**
	 * Zugriff auf Spieler oder Buzzer?
	 */
	

	/**
	 * wird der Button zum dritten mal gedrückt, wird die Restzeit auf 0 gesetzt
	 * @param event
	 */
	@FXML
	public void btnPressed(ActionEvent event) {
		btnClickCounter++;
		if (btnClickCounter==3) {
			getRestzeit().set(0);
		}
	}

	/**
	 * initialize Scene and start countdown timer
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getRestzeit().setValue(TIMEOUT);
		tStart = new Date().getTime(); //setzte Startzeit		
		timer = new Timer();
		timer.scheduleAtFixedRate(tTask, 0, 1000); //aktiviere zyklische Wiederholung
	}
	
	/**
	 * verantwortlich für das saubere Herunterzählen der Zeit
	 */
	TimerTask tTask = new TimerTask() {
		@Override
		public void run() {
			long deltaT = (new Date().getTime()-tStart)/1000;
			getRestzeit().setValue((int)TIMEOUT-deltaT);
			Platform.runLater(updateRestzeitLabel); // 
			if (getRestzeit().intValue()==0) {
				timer.cancel();
			}
		}
	};
	
	
	private Runnable updateRestzeitLabel = new Runnable() {			
		@Override
		public void run() {
			lblRestzeit.setText(String.valueOf(getRestzeit().intValue()));
		}
	};
	
}
	

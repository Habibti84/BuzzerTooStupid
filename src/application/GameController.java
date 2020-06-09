package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.AnswerViewController;
import view.EndViewController;
import view.LobbyViewController;
import view.QuestionViewController;
import view.StartupViewController;

public class GameController extends Application {
	
	Stage myStage = null;
	private StartupViewController startupController;
	private int rundenCounter;
	private List<Frage> zurueckGelesen;
	

	public static void main(String[] args) {
		
		GameController gamecontroller = new GameController();
		
		EinAuslesenFragen eaF = new EinAuslesenFragen("src/res/fragen.ser");
		
		ArrayList<Antwort> antworten1 = new ArrayList<Antwort>();
		ArrayList<Antwort> antworten2 = new ArrayList<Antwort>();
		ArrayList<Antwort> antworten3 = new ArrayList<Antwort>();
		
		ArrayList<Frage> fragen = new ArrayList<Frage>();
		
		
		Antwort antwort1_2 = new Antwort("Relationship Authorisation Manager", false);
		antworten1.add(antwort1_2);
		Antwort antwort1_3 = new Antwort("Remote Access Module", false);
		antworten1.add(antwort1_3);
		Antwort antwort1_1 = new Antwort("Random Access Memory", true);
		antworten1.add(antwort1_1);
		Frage fragen1 = new Frage("Was heisst RAM?", antworten1);
		fragen.add(fragen1);
		
		Antwort antwort2_2 = new Antwort("Operating System", true);
		antworten2.add(antwort2_2);
		Antwort antwort2_3 = new Antwort("Office Simulation", false);
		antworten2.add(antwort2_3);
		Antwort antwort2_1 = new Antwort("Open Software", false);
		antworten2.add(antwort2_1);
		Frage fragen2 = new Frage("Für was steht OS?", antworten2);
		fragen.add(fragen2);
		
		Antwort antwort3_2 = new Antwort("USB-Stick", false);
		antworten3.add(antwort3_2);
		Antwort antwort3_3 = new Antwort("Datenbank", true);
		antworten3.add(antwort3_3);
		Antwort antwort3_1 = new Antwort("Externe Festplatte", false);
		antworten3.add(antwort3_1);
		Frage fragen3 = new Frage("Wo werden grosse Mengen an Daten gespeichert?", antworten3);
		fragen.add(fragen3);
		
		eaF.writeInFile(fragen);
		
		gamecontroller.zurueckGelesen = eaF.leseFragen("src/res/fragen.ser");
		
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
				myStage = primaryStage;
				myStage.setTitle("Buzzer Game");
				showStartupView();
			
		} catch (Exception e) {
			e.printStackTrace();
			Platform.exit();
		}
		
	}

	public void showStartupView() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/StartupView.fxml"));
		try {
			Scene startupScene = new Scene(loader.load());
			startupController = loader.getController();
			startupController.setMainController(this);
			
			myStage.setScene(startupScene);
			myStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			Platform.exit();
		}
	}
	
	
	public void showLobbyView() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LobbyView.fxml"));
		try {
			Scene lobbyScene = new Scene(loader.load());
			LobbyViewController lobbyController = loader.getController();
			lobbyController.setMainController(this);
			
			myStage.setScene(lobbyScene);
			myStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
			Platform.exit();
		}
	}
	
	public void showQuestionView(Frage question) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/QuestionView.fxml"));
		try {
			Scene questionScene = new Scene(loader.load());
			 QuestionViewController questionController = loader.getController();
			questionController.setMainController(this);
			//questionController.setQuestion(question);
			
			questionController.getRestzeit().addListener(showAnswerSceneListener);

			
			myStage.setScene(questionScene);
			myStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			Platform.exit();
		}
	}
	
	private void showAnswerScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AnswerView.fxml"));
		try {			
			Scene answerScene = new Scene(loader.load());
			AnswerViewController answerController = loader.getController();
			answerController.setMainController(this);
			
			myStage.setScene(answerScene);
			myStage.show();
			
			setRundenCounter(getRundenCounter() + 1);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			Platform.exit();
		}
	}
	
	public void showEndScene() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/EndView.fxml"));
		try {
			Scene endScene = new Scene(loader.load());
			EndViewController endController = loader.getController();
			endController.setMainController(this);
			
			myStage.setScene(endScene);
			myStage.show();
		} catch (Exception e) {
			e.printStackTrace();
			Platform.exit();
		}
	}
	
	
	
	

	public void answerNotifyDone() {
		showQuestionView(zurueckGelesen.get(0));
	}
	
	public void lobbyNotifyDone() {
		Spielrunde spielrunde = new Spielrunde(zurueckGelesen);
		showQuestionView(zurueckGelesen.get(0));
	}
	
	private ChangeListener<Number> showAnswerSceneListener = (o, a, newValue) -> {
		if (newValue.intValue() <= 0) {			
			Platform.runLater(() -> showAnswerScene());
			
		}
	};
	
	
	public int getRundenCounter() {
		return rundenCounter;
	}

	public void setRundenCounter(int rundenCounter) {
		this.rundenCounter = rundenCounter;
	}

}

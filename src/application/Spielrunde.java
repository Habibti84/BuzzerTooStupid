package application;

import java.util.ArrayList;
import java.util.List;

public class Spielrunde {
	
	
	//private List<Fragerunde> fragerunden = new ArrayList<Fragerunde>();
	List<Frage> alleFragen;
	List<Frage> chosenQuestions;
	
	public Spielrunde(List<Frage> alleFragen) {
		this.alleFragen = alleFragen;
		chosenQuestions = get10Questions(alleFragen);
	}
	
	
	
	public ArrayList<Frage> get10Questions(List<Frage> f) {
		
		ArrayList<Frage> ausgewaehlteFragen = new ArrayList<Frage>();
		ArrayList<Integer> zufallszahlen = new ArrayList<Integer>();
		while(ausgewaehlteFragen.size() <= 10) {
			System.out.println("AusgewaehlteFragen Gr�sse: " + ausgewaehlteFragen.size());
		int zufallszahl = (int) (Math.random() * f.size());		
		for (int i = 0; i < zufallszahlen.size(); i++) {
			if(zufallszahlen.get(i) != zufallszahl) {
				zufallszahlen.add(zufallszahl);
				ausgewaehlteFragen.add(f.get(zufallszahl));
			}
		}			
		}
		return ausgewaehlteFragen;
		
	}
	
//	public void start(Frage f, List<Spieler> spieler) {
//		//für jede Frage aus der Liste neue Fragerunde erstellen und ausführen
//		Fragerunde fr = new Fragerunde(f, spieler);
//		fragerunden.add(fr);
//		//fr.start();
//	}
//
//	public List<Fragerunde> getFr() {
//		return fragerunden;
//	}
//
//	public void setFr(List<Fragerunde> fr) {
//		this.fragerunden = fr;
//	}

}

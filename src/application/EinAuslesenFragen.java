package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Kümmert sich um ein- und auslesen aller Fragen
 * @author jacmo
 * 
 */
public class EinAuslesenFragen {	
	
	 String file;
	 	 
	 public EinAuslesenFragen(String file) {
		 this.file = file;
	 }
	 
	 
	 /**
	  * List von Fragen abspeichern
	  * @param fragen: Liste mit Fragen
	  */
	 public void writeInFile(List<Frage> fragen) {
		 try {			 
	         FileOutputStream fileOut =
	         new FileOutputStream(file);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);	         
	         out.writeObject(fragen);
	         out.close();
	         fileOut.close();
	         System.out.println("Serialized data is saved in src/res/fragen.ser");
	         System.out.println("");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	 }	   
	 
	 /**
	  * Liest Fragen aus der .ser-Datei
	  * @param file: Speicherort des Files ACHTUNG: Pfad vom "src" Ordner aus
	  * @return: Liste mit allen Fragen, die im File gespeichert sind
	  */
	 public List<Frage> leseFragen(String file) {
		 List<Frage> fragen = null;
		 ObjectInputStream in = null;
		 try {
			 in = new ObjectInputStream(new FileInputStream(file));
			 fragen = (List<Frage>) in.readObject();			 
			 in.close();
		 }
		 catch(Exception i) {
			 i.printStackTrace();
		 }
		 finally {
			 try {
				 in.close();
			 }
			 catch(Exception i) {
				 i.printStackTrace();
			 }
		 }
		return fragen;
	 
	}
}



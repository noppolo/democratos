package tipiDati;

import java.util.ArrayList;

//--- Lista di interessi di un utente 
public class Interessi {
	ArrayList<String> tipologieDiInteressi;


	public void setDefault() {
		// TODO Gestire concetto di interessi di default
		this.tipologieDiInteressi.add("ambiente");
		this.tipologieDiInteressi.add("economia");
		this.tipologieDiInteressi.add("salute");
		
	}
	
}

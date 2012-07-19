package tipiDati;

import java.io.Serializable;

public class Utente implements Serializable{
	
	public Utente(String string) {
		// TODO Auto-generated constructor stub
		// TODO Fare il check se l'utente esiste?
		descrizione=string;
		// TODO settare l'id all'utente (se esiste altrimenti crearlo nuovo)
		id=-1;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String descrizione;
	// TODO aggiungere gestione degli interessi
	 //public Interessi interessi=null;
	
	
}

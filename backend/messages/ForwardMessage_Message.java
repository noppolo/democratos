package messages;

import java.io.Serializable;

import tipiDati.ID_GestoreDiAssemblea;

/*** 
 * Quando un gestore di livello "k" effettua una "scrittura" ...
 * se la proposta e' di livello "x>k" allora propaga il messaggio anche al padre.
 * il padre lo mette nella sua "messageQueued" e lo inoltra ai figli.
 * 
 ***/	

/*
 *  Questo messaggio e' inviato dal figlio al padre (cosi li registra nella sua coda) 
 */
public class ForwardMessage_Message extends Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ID_GestoreDiAssemblea sender;
	public Integer nCommand;
	public Message[] m;
	
}

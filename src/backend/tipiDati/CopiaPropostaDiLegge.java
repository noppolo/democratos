package tipiDati;

import java.io.Serializable;
import java.util.ArrayList;

import messages.Message;

import governance.GestoreDiAssemblea;

//--- Le copie di proposte di legge NON sono serializzabili perche' sono "distribuite" tra piu'
//--- gestori di assemblea.
public class CopiaPropostaDiLegge {
	//---- Ritorna un id unico per le proposte di legge
	private static int maxOfId=0;
	private static int getNewId() {
		// TODO Auto-generated method stub
		return maxOfId++;
	}
	
	//--- Identificatore univoco ;
	private int id;
	
	//--- Fino a che livello e' conosciuta la proposta
	private int levelOfOwner=0;
	
	//--- Chi e' il GestoreDiAssemblea proprietario di questa proposta ???
	private GestoreDiAssemblea ownerOf=null;
	
	//--- Chi e' il gestore della assemblea a cui questa istanza si riferisce
	private Votazione statoDellaVotazione=null; //--- Lo stato della votazione a cui e' giunta quella "copia" della proposta di legge

	private Utente autore=null; //--- Chi sta creando la proposta di legge

	//--- Testo della proposta di legge creata.
	private String testoProposta=null; 
	 
	private Boolean positiva=true;/* indica se la proposta di legge sale di livello se vincono i "si" o 
	i "no" (i "non so'" ovviamente non possono far salire una proposta).*/	

	//---- Crea una nuova proposta di legge e l'autore ha dato una (nuova) motivazione al suo voto.
	public CopiaPropostaDiLegge(Utente t_autore, String t_testoProposta,
			Boolean t_positiva, VOTO votazione,String testo_motivazione ) {
		id=CopiaPropostaDiLegge.getNewId();
		// TODO Auto-generated constructor stub
		levelOfOwner=0;
		autore=t_autore;
		testoProposta=t_testoProposta;
		positiva=t_positiva;
		
		//--- Aggiunge il testo della motivazione al rispettivo array
		EspressioneDiVoto esp=new EspressioneDiVoto(votazione,testo_motivazione);

		//--- 
		boolean raggiuntaMaggioranza= statoDellaVotazione.addEspressioneDiVoto(esp);
		if (raggiuntaMaggioranza) {
			//--- TODO E' successo qualcosa di strano.			
		}
				
	}
	
	//--- Metodo di ricerca basato sul testo della proposta e autore
	public static CopiaPropostaDiLegge search(String testoProposta, Utente utente) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<CopiaPropostaDiLegge> searchInteresting(Interessi weight){
		// TODO Auto-generated method stub
		return null;		
	}

	public EspressioneDiVoto getMotivazione_Si(int i) {
		// TODO Auto-generated method stub
		return statoDellaVotazione.getMotivazione_Si(i);
	}

	public void addEspressioneDiVoto(EspressioneDiVoto espr) {
		// TODO Auto-generated method stub
		boolean raggiuntaMaggioranza=statoDellaVotazione.addEspressioneDiVoto(espr);
		
		if (raggiuntaMaggioranza){
			//--- TODO La proposta deve diventare di livello superiore.
			this.levelOfOwner++;
			
			//--- Avverte il padre che e' diventato owner di questa PropostaDiLegge()
			
		}
		
	}

	
}


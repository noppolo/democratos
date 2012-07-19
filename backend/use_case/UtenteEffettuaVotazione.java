package use_case;


import tipiDati.CopiaPropostaDiLegge;
import tipiDati.EspressioneDiVoto;
import tipiDati.Utente;
import tipiDati.VOTO;
import tipiDati.Votazione;

//--- Questo messaggio viene lanciato verso il "Gestore dell'assemblea" per dire che un'utente ha espresso 
//--- un parere di voto su una copiadipropostadilegge
public class UtenteEffettuaVotazione {

	public CopiaPropostaDiLegge inputOutput=null;

	public UtenteEffettuaVotazione(CopiaPropostaDiLegge propostaDiLegge,
		Utente t_votante, EspressioneDiVoto espr) {
		// TODO Auto-generated constructor stub
		
		//---- Deve aggiungere alla CopiaDiPropostaDiLegge l'espressione di voto.
		propostaDiLegge.addEspressioneDiVoto(espr);
		
		
	}
	//---
	private static void test1(){
		UtenteEffettuaVotazione test=null;
		
		CopiaPropostaDiLegge propostaDiLegge=CopiaPropostaDiLegge.search(
				"vogliamo incrementare l'uso del nucleare.",
				new Utente("nrossi")
				);
		
		Utente t_votante=new Utente("odoacre");		//--- Chi sta effettuando la votazione
		
		//--- Eventuale motivazione a cui l'utente fa' riferimento nel suo voto.
		
		//--- Vota si senza fare riferimento a nessuna espressione di voto
		//EspressioneDiVoto espr=new EspressioneDiVoto(VOTO.SI, null);

		//--- Vota "Si" facendo riferimento alla prima espressione di voto nella lista
		EspressioneDiVoto espr=propostaDiLegge.getMotivazione_Si(0);
		
		test=new UtenteEffettuaVotazione(propostaDiLegge,t_votante,espr);
		
		
	}
	public static void main(String[] args){
		UtenteCreaPropostaDiLegge.test1();
	}

}

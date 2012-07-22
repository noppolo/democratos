package use_case;

import tipiDati.CopiaPropostaDiLegge;
import tipiDati.Utente;

public class UtenteVisualizzaPropostaDiLegge {
	public UtenteVisualizzaPropostaDiLegge(Utente lettore) {
		// TODO Auto-generated constructor stub
		
		//--- Al gestore dell'assemblea collegato chiede 
		
		
	}
	private static void test1(){
		UtenteVisualizzaPropostaDiLegge test=null;
				
		Utente lettore=new Utente("nrossi");
		
		test=new UtenteVisualizzaPropostaDiLegge(
				lettore
				);
		
		CopiaPropostaDiLegge[] p=test.getProposteDaVisualizzare();
		


	}
	private CopiaPropostaDiLegge[] getProposteDaVisualizzare() {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args){
		UtenteVisualizzaPropostaDiLegge.test1();
	}
}

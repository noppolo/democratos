package use_case;


import tipiDati.CopiaPropostaDiLegge;
import tipiDati.Utente;
import tipiDati.VOTO;

public class UtenteCreaPropostaDiLegge {
		
	private CopiaPropostaDiLegge output=null; //--- Output dello use case 

	public UtenteCreaPropostaDiLegge(Utente t_autore, String t_testoProposta,
			Boolean t_positiva, VOTO votazione,String t_motivazione ) {

		// TODO Auto-generated constructor stub		
		output=new CopiaPropostaDiLegge(t_autore,t_testoProposta,t_positiva,votazione,t_motivazione);
		
	}

	public static void test1(){
		UtenteCreaPropostaDiLegge test;
	
		Utente t_autore=new Utente("nrossi");
		String t_testoProposta="vogliamo incrementare l'uso del nucleare.";
		Boolean t_positiva=true;
		
		String t_motivazione="per andare piu' veloce in mcchina.";
		
		//---- 
		test=new UtenteCreaPropostaDiLegge(
				t_autore,t_testoProposta,t_positiva,VOTO.SI,t_motivazione
				);
		
		System.out.println(test);
	}

	public static void main(String[] args){
		UtenteCreaPropostaDiLegge.test1();
	}
}

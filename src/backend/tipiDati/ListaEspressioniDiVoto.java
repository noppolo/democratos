package tipiDati;

import java.util.ArrayList;

public class ListaEspressioniDiVoto extends ArrayList<EspressioneDiVoto> {
	/**
	 * 
	 */
	Votazione vot;
	ArrayList<EspressioneDiVoto> lista=null;
	
	private static final long serialVersionUID = 1L;
	
	public boolean add(EspressioneDiVoto esp){
		if (lista==null) {
			lista=new ArrayList<>();
		}
		//--- Cerca l'espressione di voto nella lista
		for (EspressioneDiVoto E : lista) {
			if (E.motivazione.contentEquals(esp.motivazione)) {
				if (E.cosaVotato==esp.cosaVotato){
					E.quanti++;
					
					//---- TODO Sarebbe da rifare il quick sort della lista.					
					return true;
				}
			}
		}
		//--- se non l'ha trovata l'aggiunge
		lista.add(esp);
		return true;
		
	}

}

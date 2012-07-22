package tipiDati;

import java.io.Serializable;


public class Votazione implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int votanti=0;
	private int favorevoli=0;
	private int contrari=0;
	private int astenuti=0;
	
	private ListaEspressioniDiVoto motivazione_Si=new ListaEspressioniDiVoto(); //--- Lista di motivazione per i "si" 
	private ListaEspressioniDiVoto motivazione_No=new ListaEspressioniDiVoto(); //--- Lista di motivazione per i "no" 
	private ListaEspressioniDiVoto motivazione_Astensione=new ListaEspressioniDiVoto();//--- Lista di motivazioni per gli "astenuti"

	public void addVotanti(int toSum){
		votanti+=toSum;
	}
	public boolean addEspressioneDiVoto(EspressioneDiVoto espr){
		//---- Aggiungere il voto		
		switch (espr.cosaVotato) {
		case SI:
			favorevoli++;
			motivazione_Si.add(espr);
			break;
		case NO:
			contrari++;
			motivazione_No.add(espr);
			break;
		case ASTENUTO:
			astenuti++;
			motivazione_Astensione.add(espr);
			break;	
		}
		
		//---- Controllare se la proposta sale di livello
		if ((votanti-astenuti) < (2*favorevoli) ){
			return true;		
		} else {
			return false;
		}
		
	}
	public EspressioneDiVoto getMotivazione_Si(int i) {
		// TODO Auto-generated method stub
		return motivazione_Si.get(i);
	}
	
}

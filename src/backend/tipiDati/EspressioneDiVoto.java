package tipiDati;

public class EspressioneDiVoto {
	
	public EspressioneDiVoto(VOTO votazione, String testo_motivazione) {
		// TODO Auto-generated constructor stub
		quanti=1;
		cosaVotato=votazione;
		motivazione=testo_motivazione;
	}
	
	//--- Quanti "condividono" questa espressione di voto
	public int quanti=0;

	//--- Cosa voto (Si/No/Astengo)
	public VOTO cosaVotato;
		
	//--- perche' lo voto ...un'espressione di voto include anche la motivazione.
	public String motivazione;
}

package governance;

import java.util.ArrayList;

import messages.Message;

import tipiDati.CopiaPropostaDiLegge;
public class GestoreDiAssemblea {
	private String name;
	private String ip_alpha;
	private String ip_beta;
	private String ip_gamma;
	
	//----

	/*** 
	 * Questa e' una lista di messaggi che sono in coda.
	 * Quando un gestore di livello "k" effettua una "scrittura" ...
	 * se la proposta e' di livello "x>k" allora propaga il messaggio anche al padre.
	 * il padre lo mette nella sua "messageQueued" e lo inoltra ai figli da cui non e' arrivato.
	 * Ogni tanto il padre scoda questa coda
	 ***/
	public ArrayList<Message> InputMessageQueued=null;
	//new ArrayList<>();
	
	Configurazione conf;
	ArrayList<CopiaPropostaDiLegge> listaProposte=null;

	/*--- Handler dei comandi */
	Handler_Sync hSync;
		
	/**
	 * @param args
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return " ["+name+"]@["+ip_alpha+"]";
	}
	
	public GestoreDiAssemblea(String nome,String alpha_address,String beta_address,String gamma_address){
		setName(nome);
		setIp_alpha(alpha_address);
		setIp_beta(beta_address);
		setIp_gamma(gamma_address);
	}
	
	public GestoreDiAssemblea(String pathOfConfigurazione) {
		/*--- Costruttore da URI */
		// TODO Auto-generated constructor stub
			
		// carica il file di configurazione
		this.conf=new Configurazione(pathOfConfigurazione);
		
		//this.id = conf.whoIAm();
		//this.nChild=conf.getNChild();
	
		
		// carica dallo "storage permanente" le proposte che deve gestire
		listaProposte=loadProposteDiLegge();
		
		// carica i gestori dei comandi in ingresso.
		loadCommandHandler(conf.getFolderOfHandlerMessage());
		
		System.out.println(conf.timeBetweenReport);
		/*
		
		// Entra in main loop
		while (true) {			
			// genera report 
			generateReport();
			try {
				Thread.sleep(conf.timeBetweenReport);
			} catch (InterruptedException e) {
				//--- TODO-> Che facciamo?
				e.printStackTrace();
			}
		}
		*/
	}

	private void generateReport() {
		// TODO Auto-generated method stub
		System.out.println(" Hello world by  ["+conf.whoAmI()+"]");		
	}

	private ArrayList<CopiaPropostaDiLegge> loadProposteDiLegge() {
		// TODO Auto-generated method stub
		return null;
	}

	/*--- Carica i gestori dei messaggi da una directory specificato */
	private void loadCommandHandler(String path) {
		// TODO Auto-generated method stub		
	}

	public static void main(String[]args) {
		System.out.println("Testing di classe GestoreDiAssemblea");
		GestoreDiAssemblea c=new GestoreDiAssemblea("input/test1.xml");		
	}
	public String getIp_alpha() {
		return ip_alpha;
	}
	public void setIp_alpha(String ip_alpha) {
		this.ip_alpha = ip_alpha;
	}
	public String getIp_beta() {
		return ip_beta;
	}
	public void setIp_beta(String ip_beta) {
		this.ip_beta = ip_beta;
	}
	public String getIp_gamma() {
		return ip_gamma;
	}
	public void setIp_gamma(String ip_gamma) {
		this.ip_gamma = ip_gamma;
	}

}

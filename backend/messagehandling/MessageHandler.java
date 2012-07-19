package messagehandling;

import governance.GestoreDiAssemblea;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public abstract class MessageHandler implements Runnable{
	protected Integer portToListen=1024;
	protected Header header;
	protected Thread th;

	private ServerSocket providerSocket;
	private Socket connection = null;
	
	protected MessageOutputStream out;
	protected MessageInputStream in;
	
	protected String message;
	private String prefix;
	//private ArrayList<Thread> listOfChild=new ArrayList<>();
	protected GestoreDiAssemblea caller;
	
	/*--- */
	public MessageHandler(Header h,Integer port, GestoreDiAssemblea c){
		header=h;
		portToListen=port;
		caller=c;
		
		th=new Thread();
		prefix="Handler of ["+header+"]:";
		th.setName(prefix);
		
		//1. creating a server socket
		try {
			providerSocket = new ServerSocket(portToListen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*--- Crea il thread per il startMessageHandling e fallo partire */
		Thread thHandler=new Thread(this);
		thHandler.start();
	}
	
	public void run() {
		// TODO Auto-generated method stub
		try {
			startForkHandling();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private final void startForkHandling() throws IOException {			
		while (true) {
			//2. Wait for connection
			System.out.println(prefix+"Waiting for connection");
			connection = providerSocket.accept();
			System.out.println(prefix+"Connection received from " + connection.getInetAddress().getHostName());
		
			//3. get Input and Output streams
			out = new MessageOutputStream(connection.getOutputStream());
			out.flush();
			in = new MessageInputStream(connection.getInputStream());

			//			sendMessage("Connection successful");
			//4. The two parts communicate via the input and output streams
			Thread tmpTh=new Thread(new Runnable() {
				public void run() {
					/*--- Decidere cosa passargli */
					try {
						callbackFunc(in,out);
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			);
					
			/*---- Aggiungiamo alla lista di thread il thread tmpTh -*/
			//listOfChild.add(tmpTh);
			
			/*---- Lo facciamo partire */
			System.out.println(prefix+"starting thread ...");			
			tmpTh.start();
			System.out.println(prefix+"...thread started.");
		}
	};
	
	/*---- Questa funzione sara' implementata dalla classe "figlio" e verra richiamata dal processo */
	
	public abstract  void callbackFunc(MessageInputStream in,
			MessageOutputStream out) throws ClassNotFoundException, IOException;	
	
		
}

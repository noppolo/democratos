package governance;
import java.io.IOException;
import java.net.Socket;
import messagehandling.Header;
import messagehandling.MessageHandler;
import messagehandling.MessageInputStream;
import messagehandling.MessageOutputStream;
import messages.SyncMessage;

public class Handler_Sync extends MessageHandler {

	public Handler_Sync(GestoreDiAssemblea caller) {
		super(new Header("SYNC"), 1024,caller);
	}

	public static void main(String[] args) {
		// ---- Creiamo il gestione di messaggi
		Handler_Sync hS=  new Handler_Sync(null);
		
		//---- Lo testiamo
		try {
			/*--- Inviamo un messaggio di prova */
			for (int i = 0; i < 1; i++) {
				// 1. creating a socket to connect to the server
				Socket requestSocket;
				requestSocket = new Socket("localhost", 1024);

				System.out.println("TESTING :Connected to localhost in port 1024");
				// 2. get Input and Output streams
				MessageOutputStream outClient;
				outClient = new MessageOutputStream(
						requestSocket.getOutputStream());
				outClient.flush();

				// 3: Communicating with the server
				SyncMessage testMsg = new SyncMessage();
				testMsg.header = new Header("SYNC");
				System.out.println("TESTING :Mettiamo "+testMsg.header);
				testMsg.body = new String("HELLO WORLD" + i);

				// ----
				outClient.writeObject(testMsg);
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hS=null;
	}

	@Override
	public void callbackFunc(MessageInputStream in, MessageOutputStream out)
			throws ClassNotFoundException, IOException {


		SyncMessage sM = null;
		sM = (SyncMessage) in.readMessage();

		// --- Controlliamo che l'header ci piace
		System.out.println("Header:" + sM.header);
		System.out.println("Body:" + sM.body);

		if (this.header.isEqual(sM.header )){
		
		} else {
			System.out.println("Sulla porta " + this.portToListen
					+ " e' arrivato l'header [" +sM.header+"] invece di ["+ this.header + "]");
		}
	}

}

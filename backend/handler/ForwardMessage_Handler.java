package handler;

import governance.GestoreDiAssemblea;

import java.io.IOException;
import java.util.ArrayList;

import tipiDati.CopiaPropostaDiLegge;

import messagehandling.Header;
import messagehandling.MessageHandler;
import messagehandling.MessageInputStream;
import messagehandling.MessageOutputStream;
import messages.ForwardMessage_Message;
import messages.Message;

public class ForwardMessage_Handler extends MessageHandler {
	
		public ForwardMessage_Handler(Header h, Integer port, GestoreDiAssemblea c) {
		super(h, port, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void callbackFunc(MessageInputStream in, MessageOutputStream out)
			throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		GestoreDiAssemblea whoCallMe=this.caller;
		
		//--- Leggiamo il messaggio
		ForwardMessage_Message messageReceived=(ForwardMessage_Message) in.readObject();

		//--- Il messaggio ci sta' dicendo :
		//---- sono il figlio: "sender" invia questi comandi a tutti i tuoi child tranne che a me.
		//-- 
		for (int k=0;k<messageReceived.nCommand;k++) {			
			//----
//			Message messageToSend=messageReceived.m[k]

			//		whoCallMe.messageQueued.add();
			
			
		}
		
		//----
	}
	
}

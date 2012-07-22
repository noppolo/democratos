package messagehandling;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import messages.SyncMessage;

public class MessageInputStream extends ObjectInputStream{

	public MessageInputStream(InputStream in) throws IOException {
		super(in);
		// TODO Auto-generated constructor stub
	}

	public SyncMessage readMessage() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return (SyncMessage) super.readObject();
	}

}

package shared;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandler {
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	public SocketHandler(Socket socket) throws IOException {
		super();
		this.output = new ObjectOutputStream(socket.getOutputStream());
		this.input = new ObjectInputStream(socket.getInputStream());
	}
	
	public void sendMessage(Object object) throws IOException {
		this.output.writeObject(object);
		this.output.flush();
	}
	
	public Object getMessage() throws ClassNotFoundException, IOException {
		return this.input.readObject();
	}
}

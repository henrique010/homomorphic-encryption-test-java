package client;

import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		Socket client = ClientSocketTest.initializeClient();
		String message = "Client Request";
		
		if(client.isConnected()) {
			System.out.println("Client initialiazed");
			
			ClientSocketTest.sendMessage(message);
		}
	}

}

package client;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;

import shared.TransferMethod;
import shared.SocketHandler;

public class Main {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 3333);
		ClientSocketTest client = new ClientSocketTest();
		
		try {
			client.invokeServerForSumNumbers(socket);
			
			socket.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Communication  with server failed");
		}
	}

}

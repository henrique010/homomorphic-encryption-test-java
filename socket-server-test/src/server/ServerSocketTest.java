package server;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

import shared.Message;
import shared.SocketHandler;

public class ServerSocketTest {

	public static void main(String[] args) throws ClassNotFoundException {
		try {
			// cria servidor
			ServerSocket server = new ServerSocket(3333);
			
			System.out.println("Server running on port 3333");
			
			Socket socket = server.accept();
			System.out.println("Client IP: "+socket.getInetAddress());
			
			// recebe a request do cliente e realiza a operação de soma
			SocketHandler socketHandler = new SocketHandler(socket);
			
			Message requestData = (Message) socketHandler.getMessage();
			
			BigInteger resultEncrypted = requestData.getNumberOne()
					.multiply(requestData.getNumberTwo()).mod(requestData.getPublicKeyNSquared());
			
			socketHandler.sendMessage(resultEncrypted);
			
			socket.close();
			server.close();
		} catch (IOException e) {
			System.err.print("Server error: "+e.getMessage());
		}
	}

}

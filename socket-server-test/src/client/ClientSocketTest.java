package client;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;

import encryption.KeyPair;
import encryption.KeyPairBuilder;
import encryption.PublicKey;
import shared.Message;
import shared.SocketHandler;

public class ClientSocketTest {
	private static Socket socket;
	
	public static Socket initializeClient() {
		try {
			socket = new Socket("127.0.0.1", 3333);
			
			return socket;
			
		} catch (Exception e) {
			throw new Error("Client initialize error: "+ e.getMessage());
		}
	}
	
	public static void sendMessage(String message) throws ClassNotFoundException {
		try {
			KeyPairBuilder keyGen = new KeyPairBuilder();
			KeyPair keyPair = keyGen.generateKeyPair();
			
			PublicKey publicKey = keyPair.getPublicKey();
			
			BigInteger plainA = BigInteger.valueOf(4);
			BigInteger plainB = BigInteger.valueOf(10);

			BigInteger encryptedA = publicKey.encrypt(plainA);
			BigInteger encryptedB = publicKey.encrypt(plainB);

			Message sendData = new Message(encryptedA, encryptedB, publicKey.getnSquared());
			
			SocketHandler socketHandler = new SocketHandler(socket);
			socketHandler.sendMessage(sendData);
			
			BigInteger serverResponse = (BigInteger) socketHandler.getMessage();
			
			BigInteger additionResultDecrypted = keyPair.decrypt(serverResponse);
			
			System.out.println("Server Decrypted Result: "+additionResultDecrypted);
			
			socket.close();
		
		} catch (IOException e) {
			System.out.println("Send message error: "+e.getMessage());
		}
	}
}

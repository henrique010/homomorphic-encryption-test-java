package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;

import encryption.KeyPair;
import encryption.KeyPairBuilder;
import encryption.PublicKey;

public class ClientSocketTest {
	private static Socket client;
	
	public static Socket initializeClient() {
		try {
			client = new Socket("127.0.0.1", 3333);
			
			return client;
			
		} catch (Exception e) {
			throw new Error("Client initialize error: "+ e.getMessage());
		}
	}
	
	public static void sendMessage(String message) {
		PrintWriter output;
		try {
			
			//envia dados para o servidor
			output = new PrintWriter(client.getOutputStream());
			
			KeyPairBuilder keyGen = new KeyPairBuilder();
			KeyPair keyPair = keyGen.generateKeyPair();
			
			PublicKey publicKey = keyPair.getPublicKey();
			
			BigInteger plainA = BigInteger.valueOf(1150);
			BigInteger plainB = BigInteger.valueOf(2225);

			BigInteger encryptedA = publicKey.encrypt(plainA);
			BigInteger encryptedB = publicKey.encrypt(plainB);

			output.println(encryptedA+"-"+encryptedB+"-"+publicKey.getnSquared());
			output.flush();
			
			// recebe a resposta do servidor
			InputStreamReader input = new InputStreamReader(client.getInputStream());
			
			BufferedReader buffer = new BufferedReader(input);
			
			String serverMessage = buffer.readLine();
			
			System.out.println("Server Encrypted Result: "+serverMessage);
			
			BigInteger additionResult = keyPair.decrypt(new BigInteger(serverMessage));
			
			System.out.println("Server Decrypted Result: "+additionResult);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Send message error: "+e.getMessage());
		}
	}
}

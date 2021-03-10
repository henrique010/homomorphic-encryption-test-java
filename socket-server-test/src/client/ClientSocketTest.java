package client;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;

import encryption.KeyPair;
import encryption.KeyPairBuilder;
import encryption.PublicKey;
import shared.TransferMethod;
import shared.Param;
import shared.SocketHandler;

public class ClientSocketTest {
	private KeyPair keyPair;
	private PublicKey publicKey; 
	
	public ClientSocketTest() {
		this.keyPair = new KeyPairBuilder().generateKeyPair();
		this.publicKey = keyPair.getPublicKey();
	}

	public BigInteger sumEncryptedNumbers(BigInteger numberOne, BigInteger numberTwo, BigInteger nSquared) {	
		BigInteger resultEncrypted = numberOne
				.multiply(numberTwo).mod(nSquared);
		
		return resultEncrypted;
	}
	
	public void invokeServerForSumNumbers(Socket socket) throws ClassNotFoundException {
		try {
			BigInteger encryptedNumberOne = this.publicKey.encrypt(BigInteger.valueOf(4));
			BigInteger encryptedNumberTwo = this.publicKey.encrypt(BigInteger.valueOf(10));
			
			TransferMethod sendData = new TransferMethod("sumEncryptedNumbers");
			sendData.setParam(new Param("numberOne", encryptedNumberOne));
			sendData.setParam(new Param("numberTwo", encryptedNumberTwo));
			sendData.setParam(new Param("nSquared", this.publicKey.getnSquared()));
			
			SocketHandler socketHandler = new SocketHandler(socket);
			socketHandler.sendMessage(sendData);
			
			BigInteger serverResponse = (BigInteger) socketHandler.getMessage();
			BigInteger additionResultDecrypted = keyPair.decrypt(serverResponse);
			
			System.out.println("Server Decrypted Result: "+additionResultDecrypted);	
		} catch (IOException e) {
			System.out.println("Send message error: "+e.getMessage());
		}
	}
}

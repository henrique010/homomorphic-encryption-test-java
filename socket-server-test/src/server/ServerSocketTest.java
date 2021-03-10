package server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

import client.ClientSocketTest;
import shared.TransferMethod;
import shared.SocketHandler;

public class ServerSocketTest {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
			ServerSocket server = new ServerSocket(3333);
			
			System.out.println("Server running on port 3333");
			
			Socket socket = server.accept();
			System.out.println("Client IP: "+socket.getInetAddress());
			
			SocketHandler socketHandler = new SocketHandler(socket);
			
			TransferMethod requestData = (TransferMethod) socketHandler.getMessage();
			
			ClientSocketTest client = new ClientSocketTest();
			Class<?> clientClass = client.getClass();
			
			Method sumEncryptedNumbers = clientClass.getDeclaredMethod(
					requestData.getMethodName(),
					BigInteger.class,
					BigInteger.class,
					BigInteger.class
			);
			
			Object resultEncrypted = sumEncryptedNumbers.invoke(
					client, 
					requestData.getParam(0).getValue(), 
					requestData.getParam(1).getValue(),
					requestData.getParam(2).getValue()
			);
			
			socketHandler.sendMessage(resultEncrypted);
			
			socket.close();
			server.close();
		} catch (IOException e) {
			System.err.print("Server error: "+e.getMessage());
		}
	}

}

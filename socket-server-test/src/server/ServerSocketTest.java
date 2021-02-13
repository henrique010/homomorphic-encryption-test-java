package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {

	public static void main(String[] args) {
		try {
			// cria servidor
			ServerSocket server = new ServerSocket(3333);
			
			System.out.println("Server running on port 3333");
			
			Socket client = server.accept();
			System.out.println("Client IP: "+client.getInetAddress());
			
			
			// recebe a request do cliente e realiza a operação de soma
			InputStreamReader input = new InputStreamReader(client.getInputStream());
			BufferedReader buffer = new BufferedReader(input);
			
			String message = buffer.readLine();
			
			String keys[] = message.split("-");
			
			BigInteger number1 = new BigInteger(keys[0]);
			BigInteger number2 = new BigInteger(keys[1]);
			BigInteger nSquaredOfPublicKey = new BigInteger(keys[2]);
			
			System.out.println("Number 1 Encrypted: "+number1);
			System.out.println("Number 2 Encrypted: "+number2);
			System.out.println("nSquaredOfPublicKey: "+nSquaredOfPublicKey);
			
			BigInteger result = number1.multiply(number2).mod(nSquaredOfPublicKey);
			
			System.out.println("Result to client encrypted: "+result);
			
			// envia a resposta para o cliente
			PrintWriter output = new PrintWriter(client.getOutputStream());
			
			output.println(result);
			output.flush();
			
			client.close();
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.print("Server error: "+e.getMessage());
		}
	}

}

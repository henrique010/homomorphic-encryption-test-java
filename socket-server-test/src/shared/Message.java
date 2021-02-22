package shared;

import java.io.Serializable;
import java.math.BigInteger;

public class Message implements Serializable{
	private static final long serialVersionUID = -4545754341010570580L;
	
	private BigInteger numberOne;
	private BigInteger numberTwo;
	private BigInteger publicKeyNSquared;
	
	public Message(BigInteger numberOne, BigInteger numberTwo, BigInteger publicKeyNSquared) {
		super();
		this.numberOne = numberOne;
		this.numberTwo = numberTwo;
		this.publicKeyNSquared = publicKeyNSquared;
	}

	public BigInteger getNumberOne() {
		return numberOne;
	}

	public void setNumberOne(BigInteger numberOne) {
		this.numberOne = numberOne;
	}

	public BigInteger getNumberTwo() {
		return numberTwo;
	}

	public void setNumberTwo(BigInteger numberTwo) {
		this.numberTwo = numberTwo;
	}

	public BigInteger getPublicKeyNSquared() {
		return publicKeyNSquared;
	}

	public void setPublicKeyNSquared(BigInteger publicKeyNSquared) {
		this.publicKeyNSquared = publicKeyNSquared;
	}
}

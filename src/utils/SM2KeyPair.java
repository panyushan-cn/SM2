package utils;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECPoint;


public class SM2KeyPair {
	/** ¹«Ô¿ */
	private ECPoint publicKey;
	/** Ë½Ô¿ */
	private BigInteger privateKey;

	SM2KeyPair(ECPoint publicKey, BigInteger privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public ECPoint getPublicKey() {
		return publicKey;
	}

	public BigInteger getPrivateKey() {
		return privateKey;
	}
}

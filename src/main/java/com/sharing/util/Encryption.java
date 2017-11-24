package com.sharing.util;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
@SuppressWarnings("restriction")
public class Encryption {

	private static Encryption INSTANCE_VARIABLE;
	private String ENCRYPTION_STRING = "^&*SAMRIDDHA!@";
	// MZygpewJsCpRrfOr
	private byte[] key = ENCRYPTION_STRING.getBytes();
	private static final String ALGORITHM = "DES";
	
	private Encryption() {
		// ENCRYPTION_STRING=
	}

	public static Encryption getInstance() {
		if (INSTANCE_VARIABLE == null) {
			INSTANCE_VARIABLE = new Encryption();
		}
		return INSTANCE_VARIABLE;
	}

	public String encrypt(String valueToEncrypt) throws Exception {
		byte[] plainText = valueToEncrypt.getBytes(StandardCharsets.UTF_8);
		byte[] cipherText = encrypt(plainText);
		BASE64Encoder base64encoder=new BASE64Encoder();
		String encodedValue=base64encoder.encode(cipherText);
		System.out.println("incoming String : "+valueToEncrypt+" encrypted value="+encodedValue);
		return encodedValue;
	}

	public String decrypt(String valueToDecrypt) throws Exception {
		BASE64Decoder base64Decoder=new BASE64Decoder();
		byte[] encryptedText=base64Decoder.decodeBuffer(valueToDecrypt);
		byte[] decryptedCipherText = decrypt(encryptedText);
		String decodedValue=new String(decryptedCipherText,"UTF-8");
		System.out.println("Encrypted String : "+valueToDecrypt+" Decrypted Value : "+decodedValue);
		return decodedValue;
	}

	/**
	 * Encrypts the given plain text
	 *
	 * @param plainText
	 *            The plain text to encrypt
	 */
	private byte[] encrypt(byte[] plainText) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, getKey());
		return cipher.doFinal(plainText);
	}

	/**
	 
	 * Decrypts the given byte array
	 *
	 * @param cipherText
	 *            The data to decrypt
	 */
	private byte[] decrypt(byte[] cipherText) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, getKey());
		return cipher.doFinal(cipherText);
	}
	
	private SecretKey getKey() throws InvalidKeySpecException, NoSuchAlgorithmException{
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
		SecretKeyFactory keyFactory=SecretKeyFactory.getInstance(ALGORITHM);
		return keyFactory.generateSecret(secretKeySpec);
	}
	
	public static void main(String[] args){
		try {
			Encryption encryptor=Encryption.getInstance();
			System.out.println(encryptor.encrypt("pay_slip_oct_2016.pdf"));
			System.out.println(encryptor.decrypt(encryptor.encrypt("pay_slip_oct_2016.pdf")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

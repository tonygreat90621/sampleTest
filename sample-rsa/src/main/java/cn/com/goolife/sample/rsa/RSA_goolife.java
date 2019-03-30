package cn.com.goolife.sample.rsa;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

import javax.crypto.Cipher;

import cn.com.goolife.sample.base64.Base64_goolife;

public class RSA_goolife {

	public static void main(String[] args) throws Exception {
		genKeyPair();
		System.out.println("PRIVATE_KEY:"+getPrivateKey());
		System.out.println("PUBLIC_KEY:"+getPublicKey());
	}
	
	/**
	 * 密钥,密钥暂存的位置
	 */
	private static Map<Integer, String> keyPair = new HashMap<Integer, String>();
	
	/**
	 * 私钥标识
	 */
	private static  Integer PRIVATE_KEY = 1;
	
	/**
	 * 公钥标识
	 */
	private static  Integer PUBLIC_KEY = 0;
	
	/**
	 * 加密算法
	 */
	private static String ALGORITHM = "RSA";
	
	/**
	 * 钥匙工厂
	 */
	private static KeyFactory keyFactory;

	/**
	 * 加密算法
	 */
	private static Cipher cipher;
	
	/**
	 * 数据初始化,生成加密算法,钥匙工厂,生成钥匙对
	 * @throws Exception
	 */
	public static void init() throws Exception {
		keyFactory = KeyFactory.getInstance(ALGORITHM);
		cipher = Cipher.getInstance(ALGORITHM);
		genKeyPair();
	}
	
	/**
	 * 获取私钥
	 * 
	 * @return String
	 */
	public static String getPrivateKey() {
		return keyPair.get(PRIVATE_KEY);
	}
	
	/**
	 * 获取公钥
	 * 
	 * @return String
	 */
	public static String getPublicKey() {
		return keyPair.get(PUBLIC_KEY);
	}
	
	/**
	 * 生成RSA钥匙对,如果生成过钥匙对,刷新钥匙对
	 * @throws NoSuchAlgorithmException 
	 */
	private static void genKeyPair() throws NoSuchAlgorithmException{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		//钥匙对初始化,size可选1024/2048
		keyPairGenerator.initialize(1024, new SecureRandom(new String("tony").getBytes()));
		KeyPair keyPair = keyPairGenerator.genKeyPair();
		//获取私钥
		PrivateKey privateKey = keyPair.getPrivate();
		//获取公钥
		PublicKey publicKey = keyPair.getPublic();
		//存入私钥
		RSA_goolife.keyPair.put(PRIVATE_KEY, Base64_goolife.encode(privateKey.getEncoded()));
		//存入公钥
		RSA_goolife.keyPair.put(PUBLIC_KEY, Base64_goolife.encode(publicKey.getEncoded()));
	}
	
	/**
	 * 加密
	 * 
	 * @param src
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String src, String publicKey) throws Exception {
		//Base64还原公钥
		byte[] decode = Base64_goolife.decode(publicKey);
		// RSA加密密钥 PKCS8EncodedKeySpec
		PublicKey publicKey2 = keyFactory.generatePublic(new X509EncodedKeySpec(decode));
		// 初始化加密算法
		cipher.init(Cipher.ENCRYPT_MODE, publicKey2);
		// 数据加密
		byte[] bytes = cipher.doFinal(src.getBytes());
		// base64 加密获得机密报文
		String ciphertext = Base64_goolife.encode(bytes);
		return ciphertext;
	}

	/**
	 * 数据解密
	 * @param ciphertext
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String ciphertext, String privateKey) throws Exception {
		//Base64还原私钥
		byte[] decode = Base64_goolife.decode(privateKey);
		// RSA加密私钥
		PrivateKey privateKey2 = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decode));
		// 初始化加密算法
		cipher.init(Cipher.DECRYPT_MODE, privateKey2);
		// 解密
		byte[] bytes = cipher.doFinal(Base64_goolife.decode(ciphertext));
		// Base64 解密
		String plaintext = new String(bytes);
		return plaintext;
	}
	
}

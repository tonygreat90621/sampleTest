package cn.com.goolife.sample.rsa.sampletest;

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

public class RSAEncryption {

	public static void main(String[] args) throws Exception {
		init();
		String src = "我恨你";
		// 公钥加密
		String ciphertext = encrypt(src, getPublicKey());
		System.out.println("密文:\n\t"+ciphertext);
		// 私钥解密
		String plaintext = decrypt(ciphertext, getPrivateKey());
		System.out.println("还原后的明文:\n\t"+plaintext);
	}

	/**
	 * 钥匙对存储器
	 */
	private static Map<Integer, String> keyMap = new HashMap<Integer, String>();

	/**
	 * 钥匙工厂
	 */
	private static KeyFactory keyFactory;

	/**
	 * 加密算法
	 */
	private static Cipher cipher;

	//加密算法RSA
	private static String ALGORITHM = "RSA";

	/**
	 * 私钥标识
	 */
	private static Integer PRIVATE_KEY = 1;

	/**
	 * 公钥标识
	 */
	private static Integer PUBLIC_KEY = 0;

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
	 * 生成钥匙对
	 * @throws NoSuchAlgorithmException
	 */
	private static void genKeyPair() throws NoSuchAlgorithmException {
		//构造密钥生成器
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		//密钥对生成器初始化
		keyPairGenerator.initialize(1024, new SecureRandom("tony".getBytes()));
        //生成密钥对
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		//获取私钥
		PrivateKey privateKey = keyPair.getPrivate();
		//Base64加密密钥生成私钥字符串
		String privateKeyString = new String(Base64.getEncoder().encode(privateKey.getEncoded()));
        //存入私钥
		keyMap.put(PRIVATE_KEY, privateKeyString);
        //获取公钥
		PublicKey publicKey = keyPair.getPublic();
        //Base64加密公钥生成公钥字符串
		String publicKeyString = new String(Base64.getEncoder().encode(publicKey.getEncoded()));
        //存入公钥
		keyMap.put(PUBLIC_KEY, publicKeyString);

	}

	/**
	 * 获取公钥
	 * @return
	 */
	public static String getPublicKey() {
		return keyMap.get(PUBLIC_KEY);
	}
	
	/**
	 * 获取私钥
	 * @return
	 */
	public static String getPrivateKey() {
		return keyMap.get(PRIVATE_KEY);
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

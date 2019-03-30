package cn.com.goolife.sample.rsa.sampletest;

import cn.com.goolife.sample.rsa.RSA_goolife;

public class RsaTestSample {

	public static void main(String[] args) throws Exception{
		System.out.println("This is a sample of rsa.");
		//初始化
		RSA_goolife.init();
		//获取私钥
		String privateKey = RSA_goolife.getPrivateKey();
		
		System.out.println("privateKey:\n\t"+privateKey);
		//获取公钥
		String publicKey = RSA_goolife.getPublicKey();
		
		System.out.println("publicKey:\n\t"+publicKey);
		//声明需要加密的字符
		String plaintext = "去死吧";
		
		//加密
		String ciphertext = RSA_goolife.encrypt(plaintext, publicKey);
		
		System.out.println("ciphertext:\n\t"+ciphertext);
		//解密
		String dPlaintext = RSA_goolife.decrypt(ciphertext, privateKey);
		
		System.out.println("dPlaintext:\n\t"+dPlaintext);
	}
}

package cn.com.goolife.sample.enadecrypt.symmetric;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import cn.com.goolife.sample.eadnrypt.SymmetricAlgorithm;
import cn.com.goolife.sample.eadnrypt.SymmetricEnaDecrypt;

public class CommonSymmedtricEnaDecrypt implements SymmetricEnaDecrypt{

	/**
	 * <b>单例</b>
	 */
	private static CommonSymmedtricEnaDecrypt commonSymmedtricEnaDecrypt;
	
	private CommonSymmedtricEnaDecrypt() {
		
	}
	
	@Override
	public String genKey(SymmetricAlgorithm algorithm) throws Exception {
		KeyGenerator generator = KeyGenerator.getInstance(algorithm.name());
//		generator.init(1024);
		byte[] bytes = generator.generateKey().getEncoded();
		System.out.println("密钥长度:"+bytes.length);
		return Base64_goolife.encode(bytes);
	}

	@Override
	public String encrypt(SymmetricAlgorithm algorithm, String key, String plaintext) throws Exception {
		Cipher cipher = Cipher.getInstance(algorithm.name());
		cipher.init(Cipher.ENCRYPT_MODE, formatKey(algorithm, key));
		byte[] bytes = cipher.doFinal(plaintext.getBytes());
		return Base64_goolife.encode(bytes);
	}

	@Override
	public String decrypt(SymmetricAlgorithm algorithm, String key, String ciphertext) throws Exception {
		Cipher cipher = Cipher.getInstance(algorithm.name());
		cipher.init(Cipher.DECRYPT_MODE, formatKey(algorithm, key));
		byte[] bytes = cipher.doFinal(Base64_goolife.decode(ciphertext));
		return new String(bytes);
	}

	protected Key formatKey(SymmetricAlgorithm algorithm,String key) throws Exception{
		SecretKeySpec keySpec = new SecretKeySpec(Base64_goolife.decode(key), algorithm.name());
		return keySpec;
	}
	
	public static CommonSymmedtricEnaDecrypt newInstance() {
		if(commonSymmedtricEnaDecrypt == null) {
			commonSymmedtricEnaDecrypt = new CommonSymmedtricEnaDecrypt();
		}
		return commonSymmedtricEnaDecrypt;
	}
	
	public static void main(String[] args) throws Exception {
		SymmetricEnaDecrypt SymmedtricEnaDecrypt = newInstance();
		String key = SymmedtricEnaDecrypt.genKey(SymmetricAlgorithm.RC2);
		System.out.println("密钥:"+key);
		
		String p= "哇哈哈";
		System.out.println("原始报文:");
		System.out.println(p);
		String m = SymmedtricEnaDecrypt.encrypt(SymmetricAlgorithm.RC2, key, p);
		System.out.println("密文:");
		System.out.println(m);
		String pp = SymmedtricEnaDecrypt.decrypt(SymmetricAlgorithm.RC2, key, m);
		System.out.println("解密:");
		System.out.println(pp);
		//g9uRl4HYrZSN83wt68lLpg==
	}
}

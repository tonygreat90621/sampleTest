package cn.com.goolife.sample.enadecrypt.symmetric;

public class Base64_goolife {

	/**
	 * 引用Java的Base64进行加密
	 * 
	 * @param bytes
	 * @return
	 */
	public static String encode(byte[] bytes) {
		return java.util.Base64.getEncoder().encodeToString(bytes);
	}

	/**
	 * 引用Java的Base64进行加密
	 * 
	 * @param plaintext
	 * @return
	 */
	public static String encode(String plaintext) {
		return encode(plaintext.getBytes());
	}

	/**
	 * 用Java的Base64进行解密
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] decode(byte[] bytes) {
		return java.util.Base64.getDecoder().decode(bytes);
	}
	
	/**
	 * 用Java的Base64进行解密
	 * 
	 * @param ciphertext
	 * @return
	 */
	public static byte[] decode(String ciphertext) {
		return java.util.Base64.getDecoder().decode(ciphertext);
	}
	
	public static void main(String[] args) throws Exception {
		String plaintext = "我恨你";
		
		String ciphertext = encode(plaintext.getBytes());
		System.out.println(ciphertext);
		
		System.out.println(new String(decode(ciphertext)));
	}
}

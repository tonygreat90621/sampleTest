package cn.com.goolife.sample.enadecrypt.symmetric;

import cn.com.goolife.sample.eadnrypt.SymmetricAlgorithm;

public class CommonSedConstructor {

	private CommonSedConstructor() {
		
	}
	/**
	 * <b>算法</b>
	 */
	private SymmetricAlgorithm algorithm;
	
	/**
	 * <b>设置加密算法</b>
	 * @param algorithm
	 */
	public void setSymmetricAlgorithm(SymmetricAlgorithm algorithm) {
		this.algorithm = algorithm;
	}
	/**
	 * <b>生成钥匙</b>
	 * @return
	 * @throws Exception
	 */
	public String genKey() throws Exception{
		return CommonSymmedtricEnaDecrypt.newInstance()
				.genKey(algorithm);
	}
	/**
	 * <b>加密</b>
	 * @param key
	 * @param plaintext
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String key,String plaintext) throws Exception{
		return CommonSymmedtricEnaDecrypt.newInstance()
				.encrypt(algorithm, key, plaintext);
	}
	/**
	 * <b>解密</b>
	 * @param key
	 * @param ciphertext
	 * @return
	 * @throws Exception
	 */
	public String decrypt(String key,String ciphertext) throws Exception{
		return CommonSymmedtricEnaDecrypt.newInstance()
				.decrypt(algorithm, key, ciphertext);
	}
	
	/**
	 * <b>静态工厂,创建加密操作类</<b><br>
	 * <b>根据算法创建相应的操作类</b>
	 * @param algorithm
	 *        <b>加密算法</b>
	 * @return
	 */
	public static CommonSedConstructor newInstance(SymmetricAlgorithm algorithm) {
		CommonSedConstructor constructor = new CommonSedConstructor();
		constructor.algorithm = algorithm;
		return constructor;
	}
}

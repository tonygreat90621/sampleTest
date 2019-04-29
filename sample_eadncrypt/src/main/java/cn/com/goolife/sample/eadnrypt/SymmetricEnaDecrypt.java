package cn.com.goolife.sample.eadnrypt;

/**
 * <b>对称加密</b>
 * @author tony
 *
 */
public interface SymmetricEnaDecrypt {
	
	/**
	 * <b>密钥生成.当密钥长度未指定时,密钥长度为指定算法的最小容许的密钥长度</b>
	 * @param algorithm
	 * @return
	 * @throws Exception
	 */
	public String genKey(SymmetricAlgorithm algorithm) throws Exception;
	
	/**
	 * <b>数据加密</b>
	 * @param algorithm
	 *        <b>加密算法,即使用哪种方式加密</b>
	 * @param key
	 *        <b>密钥,用于数据的加密 </b>
	 * @param plaintext
	 *        <b>需要加密的数据</b>
	 * @return <code> String </code>
	 *         <b>返回加密之后的密文</b>
	 * @throws Exception
	 */
	public String encrypt(SymmetricAlgorithm algorithm,String key,String plaintext) throws Exception;
	
	/**
	 * <b>数据解密</b>
	 * @param algorithm
	 *        <b>解密算法,即用何种方式解密</b>
	 * @param key
	 *        <b>密钥,用于数据的解密</b>
	 * @param ciphertext
	 *        <b>密文,需要解密的数据</b>
	 * @return <code>String</code>
	 *         <b>返回解密之后的明文</b>
	 * @throws Exception
	 */
	public String decrypt(SymmetricAlgorithm algorithm,String key,String ciphertext) throws Exception;
}

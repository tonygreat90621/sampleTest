package cn.com.goolife.sample.eadnrypt;

/**
 * <b>对称算法,此处列举了已实现的对称算法</b>
 * @author tony
 * 2019/04
 */
public enum SymmetricAlgorithm {

	/**密钥长度可支持128,192,256*/
	AES,
	/**RC4,密钥支持长度为40~1024*/
	ARCFOUR,
	/**密钥长度为8的倍数,长度范围为32~448*/
	Blowfish,
	/**密钥长度仅支持56位(其对应的JavaJDK中的位数)*/
	DES,
	/**密钥长度支持112,168(JavaJDK中对应的位数)*/
	DESede,
	HmacMD5,
	HmacSHA1,
	HmacSHA224,
	HmacSHA256,
	HmacSHA384,
	HmacSHA512,
	/**RC2,密钥支持长度为40~1024*/
	RC2
}

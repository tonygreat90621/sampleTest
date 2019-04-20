package cn.com.goolife.sample.enadecrypt.symmetric;

import cn.com.goolife.sample.eadnrypt.SymmetricAlgorithm;

public class Test {

	public static void main(String[] args) throws Exception{
		CommonSedConstructor constructor = CommonSedConstructor.newInstance(SymmetricAlgorithm.AES);
		String key = constructor.genKey();
		System.out.println("密钥：");
		System.out.println(key);
		String p = "老夫掐指一算.嗯.你来了";
		System.out.println("原始报文：");
		System.out.println(p);
		String c = constructor.encrypt(key, p);
		System.out.println("密文：");
		System.out.println(c);
		String pp = constructor.decrypt(key, c);
		System.out.println("解密后：");
		System.out.println(pp);
	}
}

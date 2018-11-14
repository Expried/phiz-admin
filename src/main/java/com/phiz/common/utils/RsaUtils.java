package com.phiz.common.utils;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * @ClassName: RsaUtils
 * @Description: TODO RSA加密解密工具
 * @author: hayder
 * @date: 2017年10月20日 下午2:04:41
 */
public class RsaUtils {
	/**
	 * 编码
	 */
	private static final String UTF8 = "UTF-8";
	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";
	/**
	 * 签名算法
	 */
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	/**
	 * 获取公钥的key
	 */
	public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDiRmI8VFd7DmPNd9yHWJ5+HaKhCxlHA7kMx6zIIIuP3ifzrSY4iYNJnPyuKH4LvQmt6yS3xyATMEXihSBXSUWAe12zMsmNyh84gLSjgjpHEgglmNe8wgcocFpCtBvDkpozv7wLte1JvojJNkzXs/sLBykwGqkit40MtgeSbqIJdwIDAQAB";
	// public static final String PUBLIC_KEY =
	// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDN9CTEu2hZrVTXx3Hln2bYqobM2JSFzrUmSKsLuf6RomG4DS4q/NaBSZkXuhVhC/dKqkLFOG4fe2hkFP8DUn9qYOVbrTz+tu5YGuudaoT1phymdFPi5huVFuy9GWySTht3+pMPYcbQtd8hcOqaknSRstqRCixgiT3r4tPwKJfPxQIDAQAB";

	/**
	 * 获取私钥的key
	 */
	public static final String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOJGYjxUV3sOY8133IdYnn4doqELGUcDuQzHrMggi4/eJ/OtJjiJg0mc/K4ofgu9Ca3rJLfHIBMwReKFIFdJRYB7XbMyyY3KHziAtKOCOkcSCCWY17zCByhwWkK0G8OSmjO/vAu17Um+iMk2TNez+wsHKTAaqSK3jQy2B5Juogl3AgMBAAECgYB3y72spRafntG18XR+76sRqAz26BJc55qsKnbOiXR6GarbNtuaaStp6MRaTDPXcSDTxD9vUOwpXJBTShoCPFefnCDElln+Ov+ePz5nl1Oe/Oi9nnSFt21dan3iPn5pG5ELtCCHIfnSHSKvr07KcVO4GjhnFCdnJ8tmixVpJBiYSQJBAPrHKpdddPvkGGwo6Z1vN5Cx0wymxABcwvrrXL4Dl8+pp7Por/fgDtAHdTFEYz2mDm0MMJw8s30Mch/prBCNP9sCQQDm/JkJkuqeTbWGoSVq6W0/inAYgC6vEI4ztNKzlxmxSjoB1XlXAFM8Uvj5JK+EIqRyvVwwXdDp9VED46891U2VAkEAn/uJJ1E+vScxkWa0SnRWHV/kRPDqn1SOKucVnj7KjXcs/nAWOmrc6EepsslvCjgygczCAhoTY1YaRFOdKl4B+wJBAKs/4wYiEifDXS2vE1qsLP7K2EiR4AqSr5rOGNhk0fuLGG57ojZKW2uB6GxuMmI4fMtEcLC7HUQXSDUl/KpPFfECQQDya0jogAu6YjY1+1EnRx2TU2RjXjVZ95nUjyTX7xwwu3Vbxpf3Twk7I9EnyFB2WEm+u/aP1bqv1su7E3v1R8dW";
	// public static final String PRIVATE_KEY =
	// "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM30JMS7aFmtVNfHceWfZtiqhszYlIXOtSZIqwu5/pGiYbgNLir81oFJmRe6FWEL90qqQsU4bh97aGQU/wNSf2pg5VutPP627lga651qhPWmHKZ0U+LmG5UW7L0ZbJJOG3f6kw9hxtC13yFw6pqSdJGy2pEKLGCJPevi0/Aol8/FAgMBAAECgYB0VZMrWVzRQvpoxG0OsqKqgnCQZ0pF5wQmMugDQLWOV/Xwu+k3OQiFkSgb4e3Pmq2+DDyZII371H6cAcwmeUQktEQJwQ2Vcx4voqQOszfSETHycO9bkdCOMwkDXsB7/qRg3UV2jmld1j1qRyhuXOiO7y0STCq3Sn1b3vQXBqhkRQJBAOwD7If16VqLcl0k/k+svnVKUGhK7OiCYHfpyQmsnHV5noQOihS9W7MTZCO7sddwPFV3Mc7YGjGPso2R4HrKDdsCQQDfZJO6LxcugyU/D4/8aA2i2x8D7LcNe3ET/1HrJBGm7Abw2uwpNX4PFvKJ+KlOrmsDARgsuU4dLAvssOhmwJrfAkBy/O5ux3br8GjFzNJCi0034/8M8QQ/u9W4VqTmG70W1yxN9q97r05QDzCI4FejOUGL+kjAGDWa1AIQS3RtHqhlAkBgmCrQjOAnOon2dJI7//o1ey40Ej0s1T2V+ga/+D6xwMlXiDIpt3mkqLWDN0RuQzj+eZCsdgvyzULyu6QiGhJdAkEArltKh4qLYMJp5szmJyfdK0XcB3FnVXIj3Mw5QC3nytmEFMgbSRmzH8vxBJPiY+dL7cj2dLvWWLcQYzESTwBUiw==";

	/**
	 * 密钥长度
	 */
	private static final int KEY_MAX_LENGTH = 2046;

	/**
	 * <p>
	 * 生成密钥对(公钥和私钥)
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(KEY_MAX_LENGTH);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/**
	 * <p>
	 * 获取私钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64Utils.encode(key.getEncoded());
	}

	/**
	 * <p>
	 * 获取公钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64Utils.encode(key.getEncoded());
	}

	/**
	 * 私钥加密
	 * 
	 * @param key
	 *            私钥
	 * @param data
	 *            明文
	 * @return <b>String</b> 密文<b><br/>
	 * 		null</b> 加密失败
	 */
	public static String encryptByPrivateKey(String key, byte[] data) {
		try {
			RSAPrivateKey privateKey = getPrivateKey(key);
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] rsa = cipher.doFinal(data);
			return Base64Utils.byteArrayToBase64(rsa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 私钥加密
	 * 
	 * @param key
	 *            私钥
	 * @param data
	 *            明文
	 * @return <b>String</b> 密文<b><br/>
	 * 		null</b> 加密失败
	 */
	public static String encryptByPrivateKey(String key, String data) {
		try {
			byte[] bdata = StringUtil.strToByte(data);
			RSAPrivateKey privateKey = getPrivateKey(key);
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] rsa = cipher.doFinal(bdata);
			return Base64Utils.byteArrayToBase64(rsa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 公钥加密
	 * 
	 * @param key
	 *            公钥
	 * @param data
	 *            明文
	 * @return <b>String</b> 密文<b><br/>
	 * 		null</b> 加密失败
	 */
	public static String encryptByPublicKey(String key, byte[] data) {
		try {
			RSAPublicKey publicKey = getPublicKey(key);
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] rsa = cipher.doFinal(data);
			return Base64Utils.byteArrayToBase64(rsa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 公钥加密
	 * 
	 * @param key
	 *            公钥
	 * @param data
	 *            明文
	 * @return <b>String</b> 密文<b><br/>
	 * 		null</b> 加密失败
	 */
	public static String encryptByPublicKey(String key, String data) {
		try {
			byte[] bdata = StringUtil.strToByte(data);
			RSAPublicKey publicKey = getPublicKey(key);
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] rsa = cipher.doFinal(bdata);
			return Base64Utils.byteArrayToBase64(rsa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 私钥解密
	 * 
	 * @param key
	 *            私钥
	 * @param data
	 *            密文
	 * @return <b>String</b> 明文<b><br/>
	 * 		null</b> 解密失败
	 */
	public static String decryptByPrivateKey(String key, byte[] data) {
		try {
			RSAPrivateKey privateKey = getPrivateKey(key);
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] rsa = cipher.doFinal(data);
			return new String(rsa, UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 私钥解密
	 * 
	 * @param key
	 *            私钥
	 * @param data
	 *            密文
	 * @return <b>String</b> 明文<b><br/>
	 * 		null</b> 解密失败
	 */
	public static String decryptByPrivateKey(String key, String data) {
		try {
			byte[] bdata = Base64Utils.base64ToByteArray(data);
			RSAPrivateKey privateKey = getPrivateKey(key);
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] rsa = cipher.doFinal(bdata);
			return new String(rsa, UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 公钥解密
	 * 
	 * @param key
	 *            公钥
	 * @param data
	 *            密文
	 * @return <b>String</b> 明文<b><br/>
	 * 		null</b> 解密失败
	 */
	public static String decryptByPublicKey(String key, byte[] data) {
		try {
			RSAPublicKey publicKey = getPublicKey(key);
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] rsa = cipher.doFinal(data);
			return new String(rsa, UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 公钥解密
	 * 
	 * @param key
	 *            公钥
	 * @param data
	 *            密文
	 * @return <b>String</b> 明文<b><br/>
	 * 		null</b> 解密失败
	 */
	public static String decryptByPublicKey(String key, String data) {
		try {
			byte[] bdata = Base64Utils.base64ToByteArray(data);
			RSAPublicKey publicKey = getPublicKey(key);
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] rsa = cipher.doFinal(bdata);
			return new String(rsa, UTF8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * 用私钥对信息生成数字签名
	 * </p>
	 * 
	 * @param data
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64Utils.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateK);
		signature.update(data);
		return Base64Utils.encode(signature.sign());
	}

	/**
	 * <p>
	 * 校验数字签名
	 * </p>
	 * 
	 * @param data
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @param sign
	 *            数字签名
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		byte[] keyBytes = Base64Utils.decode(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(Base64Utils.decode(sign));
	}

	/**
	 * JS密钥对
	 * 
	 * @param size
	 *            密钥长度
	 * @return 密钥对
	 */
	public static Map<String, String> genJsKey(int size) {
		Map<String, String> keyMap = new HashMap<String, String>();
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			keygen.initialize(size, new SecureRandom());
			KeyPair keyPair = keygen.generateKeyPair();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			BigInteger n = privateKey.getModulus();
			BigInteger e = publicKey.getPublicExponent();
			BigInteger d = privateKey.getPrivateExponent();
			String p = Base64Utils.byteArrayToBase64(privateKey.getEncoded());
			keyMap.put("n", n.toString(16));
			keyMap.put("e", e.toString(16));
			keyMap.put("d", d.toString(16));
			keyMap.put("p", p);
		} catch (Exception e) {
		}
		return keyMap;
	}

	/**
	 * JS密文解密
	 * 
	 * @param key
	 *            私钥
	 * @param data
	 *            密文
	 * @return <b>String</b> 明文<b><br/>
	 * 		null</b> 解密失败
	 */
	public static String decryptJsData(String key, String data) {
		try {
			byte[] bdata = hexToByte(StringUtil.strToByte(data));
			RSAPrivateKey privateKey = getPrivateKey(key);
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] rsa = cipher.doFinal(bdata);
			return new String(rsa, UTF8);
		} catch (Exception e) {
		}
		return null;
	}

	private static RSAPrivateKey getPrivateKey(String key) {
		try {
			byte[] keyBytes = Base64Utils.base64ToByteArray(key);
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			return (RSAPrivateKey) factory.generatePrivate(spec);
		} catch (Exception e) {
		}
		return null;
	}

	private static RSAPublicKey getPublicKey(String key) {
		try {
			byte[] keyBytes = Base64Utils.base64ToByteArray(key);
			X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			return (RSAPublicKey) factory.generatePublic(spec);
		} catch (Exception e) {
		}
		return null;
	}

	private static byte[] hexToByte(byte[] hex) {
		if (hex.length % 2 != 0) {
			return null;
		}
		byte[] b = new byte[hex.length / 2];
		for (int i = 0; i < hex.length; i += 2) {
			String str = new String(hex, i, 2);
			b[i / 2] = (byte) Integer.parseInt(str, 16);
		}
		return b;
	}

}

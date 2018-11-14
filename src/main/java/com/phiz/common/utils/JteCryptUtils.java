package com.phiz.common.utils;

import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.CharEncoding;
public class JteCryptUtils {

	/**
	 * 加密
	 *
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static String encrypt(String content, String password) throws Exception {
		try {
			byte[] raw = password.getBytes(Charset.forName(CharEncoding.UTF_8));
			if (raw.length != 16) {
				throw new IllegalArgumentException("Invalid key size. " + password + ", 密钥 token 长度不是 16 位");
			}
			javax.crypto.spec.SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			javax.crypto.Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new javax.crypto.spec.IvParameterSpec(new byte[16])); // zero
																												// IV
			byte[] final1 = cipher.doFinal(content.getBytes(Charset.forName(CharEncoding.UTF_8)));
			return new String(org.apache.commons.codec.binary.Base64.encodeBase64(final1));
		} catch (Exception e) {
			
			throw e;
		}
	}
	
	
	

	/**
	 * 解密
	 *
	 * @param content
	 *            需要解密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static String decrypt(String content, String password) {
		try {
			byte[] raw = password.getBytes(Charset.forName(CharEncoding.UTF_8));
			if (raw.length != 16) {
				throw new IllegalArgumentException("Invalid key size. " + password + ", 密钥 token 长度不是 16 位");
			}
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));
			byte[] toDecrypt = org.apache.commons.codec.binary.Base64.decodeBase64(content.getBytes());
			byte[] original = cipher.doFinal(toDecrypt);
			return new String(original, Charset.forName(CharEncoding.UTF_8));
		} catch (IllegalArgumentException e) {
		
			throw e;
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(decrypt("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", "9859832798641329"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 }


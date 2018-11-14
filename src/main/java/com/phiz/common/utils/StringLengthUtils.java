package com.phiz.common.utils;

public class StringLengthUtils {
	
	public static String decompressionString(String tempString) {
		char[] tempBytes = tempString.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tempBytes.length; i++) {
			char c = tempBytes[i];
			char firstCharacter = (char) (c >>> 8);
			char secondCharacter = (char) ((byte) c);
			sb.append(firstCharacter);
			if (secondCharacter != 0){
				sb.append(secondCharacter);
			}
		}
		return sb.toString();
	}

	/**
	 * 对需要进行压缩的字符串进行压缩，返回一个相对较小的字符串
	 * 
	 * @param tempString
	 * @return
	 */
	public static String compactString(String tempString) {
		StringBuffer sb = new StringBuffer();
		byte[] tempBytes = tempString.getBytes();
		for (int i = 0; i < tempBytes.length; i += 2) {
			char firstCharacter = (char) tempBytes[i];
			char secondCharacter = 0;
			if (i + 1 < tempBytes.length){
				secondCharacter = (char) tempBytes[i + 1];
			}
			firstCharacter <<= 8;
			sb.append((char) (firstCharacter + secondCharacter));
		}
		return sb.toString();
	}


}


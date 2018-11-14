package com.phiz.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {


	// 这个的数据量比较大,效果比较明显,并且数据量越大,效果越明显
	public static String data = "哈哈是发送到发送到发送到水电费是否是";

	public static void main(String[] args) throws Exception {

		String temp = compress(data);
		System.out.println(temp);
		// 判断解压缩前后的数据是否相同
		System.out.println(unCompress(temp).equals(data));

	}

	/**
	 * 对字符串数据进行压缩
	 * 
	 * @param data
	 *            元数据
	 * @return String
	 */
	public  static String compress(String data) throws Exception {
		if (null == data || "".equals(data) || data.length() == 0) {
			return data;
		}

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();// 带有缓存功能的字节输出流
		GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);


		gzipOutputStream.write(data.getBytes());
		gzipOutputStream.close();

		String compressData = byteArrayOutputStream.toString("UTF-8");


		byteArrayOutputStream.close();

		 System.out.println("压缩后的数据:" + compressData);
		return compressData;
	}

	/**
	 * 对压缩后的数据,进行解压缩
	 * 
	 * @param data
	 * @return String
	 */
	public static String unCompress(String data) throws Exception {

		String retStr = "";

		if (null == data || "".equals(data) || data.length() == 0) {
			return data;
		}

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.getBytes("ISO-8859-1"));
		GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);

		byte[] b = new byte[256];

		int length = -1;

		while (-1 != (length = gzipInputStream.read(b))) {
			byteArrayOutputStream.write(b, 0, length);
		}

		retStr = byteArrayOutputStream.toString("UTF-8");
		byteArrayOutputStream.close();
		byteArrayInputStream.close();
		gzipInputStream.close();
		return retStr;
	}

	/**
	 * 将得到的返回结果进行处理，先BASE64解密，再Gzip解压
	 * 
	 * @param responseContent
	 * @return
	 * @throws IOException
	 */
	public static String getResponse(String responseContent) throws IOException {
		return new String(
			GzipUtil.unGZip(org.apache.commons.codec.binary.Base64.decodeBase64(responseContent.getBytes("GBK"))),
				"UTF-8");
	}
	
	// gzip解压
		public static byte[] unGZip(byte[] data) {
			byte[] b = null;
			try {
				ByteArrayInputStream bis = new ByteArrayInputStream(data);
				GZIPInputStream gzip = new GZIPInputStream(bis);
				byte[] buf = new byte[1024];
				int num = -1;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while ((num = gzip.read(buf, 0, buf.length)) != -1) {
					baos.write(buf, 0, num);
				}
				b = baos.toByteArray();
				baos.flush();
				baos.close();
				gzip.close();
				bis.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return b;
		}

}

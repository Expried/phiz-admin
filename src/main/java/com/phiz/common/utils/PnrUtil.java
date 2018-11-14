package com.phiz.common.utils;
/** 
 * JAVA获得0-9,A-Z范围的随机数 
 *  
 * @param length 随机数长度 
 * @return String 
 */  
import scala.util.Random;
/**
 * 获得随机6位Pnr编码
 * <p>PnrUtil</p>
 * @author yanlong.xin
 *
 * @date 2018年7月27日 下午1:41:07
 */
public class PnrUtil {
	 public static String getRandomCode(int length) {  
	        char[] chr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',      
	                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
	                'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };  
	        Random random = new Random();  
	        StringBuffer buffer = new StringBuffer();  
	        for (int i = 0; i < length; i++) {  
	            buffer.append(chr[random.nextInt(30)]);  
	        }  
	        return buffer.toString();  
	    }  
	public static void main(String[] args) {
		System.out.println(PnrUtil.getRandomCode(6));
	}
}

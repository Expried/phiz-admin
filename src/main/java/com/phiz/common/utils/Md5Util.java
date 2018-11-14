package com.phiz.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wjjunjjun.wang on 2017/7/25.
 */
public class Md5Util {

    private static final Logger logger = LoggerFactory.getLogger(Md5Util.class);

    public static String encode(String data)  {
        String md5Result = null;
        if(null == data){
            return md5Result;
        }

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer sb = new StringBuffer("");
            for(int offset = 0; offset < b.length; offset ++){
                i = b[offset];
                if(i< 0){
                    i += 256;
                }
                if(i < 16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            md5Result = sb.toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return md5Result;
    }
    
    
    /**
	 * @Description md5 32位加密大写
	 * 时间:	2017年9月1日 上午9:51:46
	 * @param msg
	 * @return
	 */
	public static String MD5toUC(String msg){
		String result = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update((msg).getBytes("UTF-8"));  
			byte b[] = md5.digest();  
			  
			int i;  
			StringBuffer buf = new StringBuffer("");  
			  
			for(int offset=0; offset<b.length; offset++){  
			    i = b[offset];  
			    if(i<0){  
			        i+=256;  
			    }  
			    if(i<16){  
			        buf.append("0");  
			    }  
			    buf.append(Integer.toHexString(i));  
			}  
			  
			result = buf.toString().toUpperCase(); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result; 
	}
   public static void main(String[] args) {
	   System.out.println(Md5Util.encode("szhuazheng"+"pwd@27!40?44*"));
}
}

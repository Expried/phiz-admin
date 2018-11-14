package com.phiz.common.utils;

import java.io.UnsupportedEncodingException;
/**
 * url转码、解码
 *
 * @author lifq 
 * @date 2015-3-17 下午04:09:35
 */
public class UrlUtil {
    private final static String ENCODE = "GBK"; 
    /**
     * URL 解码
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午04:09:51
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * URL 转码
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午04:10:28
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 
     * @return void
     * @author lifq
     * @date 2015-3-17 下午04:09:16
     */
    public static void main(String[] args) {
        String str = "routing={adultTax=null, mainAirlineCode=null, childTax=null, trips=[{segments=[{AirlineCode=SL, departureTime=07/12/2018 20:30, duration=420, arrivalAirportCode=CNX, stop=1, arrivalTime=07/12/2018 13:30, codeShare=, BookingClass=null, aircraftCode=320, cabin=, departureAirportCode=DMK, flightNumber=SL516}]}], adultBasePrice=null, lastTicketDate=null, childBasePrice=null, key={'plat':'huazheng','pricetype':0,'sourceCode':'huazheng'}}";
        System.out.println(getURLEncoderString(str));
        System.out.println(getURLDecoderString(str));
        
    }

}
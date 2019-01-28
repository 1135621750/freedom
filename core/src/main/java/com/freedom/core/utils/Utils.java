package com.freedom.core.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	/**
	 *  获取IP地址
	 * 
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}

	/**
     * 请求ip获取
     * @param request 请求
     * @return ip
     */
    public static String getClientIp(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("Proxy-Client-IP");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("WL-Proxy-Client-IP");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getRemoteAddr();
        return ip;
    }
    
    /**
     * 判断ip是否为内网
     * @param addr textToNumericFormatV4 方法返回值
     * @return true内网，flase 外网
     */
    public static boolean internalIp(byte[] addr) {
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        //10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        //172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        //192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    return true;
                }
            case SECTION_5:
                switch (b1) {
                    case SECTION_6:
                        return true;
                }
            default:
                return false;
        }
    }
    
    /**
     * 转换md5
     * @param val 转换字符串
     * @return md5
     */
    public static String getMD5(String val)
    {
        byte hash[];
        try
        {
            hash = MessageDigest.getInstance("MD5").digest(val.getBytes("UTF-8"));
        }
        catch(NoSuchAlgorithmException e)
        {
        	//MD5应该得到支持
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        }
        catch(UnsupportedEncodingException e)
        {	
        	//UTF-8应该得到支持
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);

        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        
        return hex.toString();
    }
	/**
	 * 拆分集合
	 *
	 * @param <T>
	 * @param sourceList
	 *            要拆分的集合
	 * @param count
	 *            每个集合的元素个数
	 * @return 返回拆分后的各个集合
	 */
	public static <T> List<List<T>> split(List<T> sourceList, int count) {

		if (sourceList == null || count < 1)
			return null;

		List<List<T>> resultList = new ArrayList<List<T>>();

		int size = sourceList.size();
		if (size <= count) { // 数据量不足count指定的大小
			resultList.add(sourceList);
		} else {
			int pre = size / count;
			int last = size % count;

			// 前面pre个集合，每个大小都是count个元素
			for (int i = 0; i < pre; i++) {
				List<T> itemList = new ArrayList<T>();
				for (int j = 0; j < count; j++) {
					itemList.add(sourceList.get(i * count + j));
				}
				resultList.add(itemList);
			}

			// last的进行处理
			if (last > 0) {
				List<T> itemList = new ArrayList<T>();
				for (int i = 0; i < last; i++) {
					itemList.add(sourceList.get(pre * count + i));
				}
				resultList.add(itemList);
			}
		}

		return resultList;
	}

	/**
	 * 正则验证
	 * 
	 * @param regex
	 *            正则
	 * @param str
	 *            验证字符
	 * @return true 匹配正则，flase不匹配
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public static void main(String args[]) throws Exception {
//		List resList = Arrays.asList(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
//				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
//				"30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
//				"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63",
//				"64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
//				"81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97",
//				"98", "99", "100", "101", "102" });
//		List ret = split(resList, 50);
//		for (int i = 0; i < ret.size(); i++)
//			System.out.println(ret.get(i));
//		
//		String md5 = getMD5("123");
//		System.err.println(md5);
//
//		boolean match = match("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}", "1212@qq.com");
//		System.out.println(match);
	}
}

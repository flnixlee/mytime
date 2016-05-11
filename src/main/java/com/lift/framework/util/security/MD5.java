package com.lift.framework.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述：<p>加密字符串</p>
 * @author WuNan2(alva.wu8@gmail.com)
 * @copyright aorsoft
 * @version 0.1 2011-4-7
 */
public final class MD5 {
	private MD5() {
	}

	/**
	 * 获取input对应的MD5代码
	 * @param input
	 * @return
	 */
	public static final String getMD5(String input) {
		try {
			byte[] inputByte = input.getBytes();
			MessageDigest md;

			md = MessageDigest.getInstance("md5");

			md.update(inputByte);
			byte[] digest = md.digest();
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < digest.length; i++) {
				int val = ((int) digest[i]) & 0xff;
				if (val < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(val));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public static final String getMD516(String input){
		String md5 = getMD5(input);
		return md5.substring(8,24);
	}
	public static final String getMD532(String input){
		return getMD5(input);
	}
//	public static void main(String[] args) {
//		//e8a88bb6f4d420a8517965d25cd54a14
//		//3c518eeb674c71b30297f072fde7eba5
//		//b181464d27dcd75a848c5782d64a4bbe
//		//4e7168f55f9ebd6a118b9b882d461efa
//		///9fa08fd0c4ed720bcb8c914f7c349a30
//		//1ff0314908c1724e7b616fe620bc9eee
//		//%7B%22partner_name%22%3A%22superfix%22%2C%22user_id%22%3A%22858576520%22%2C%22bill_no%22%3A%221012471%22%2C%22bill_id%22%3A%221012471%22%2C%22org_code%22%3A%22%22%2C%22order_type%22%3A%221%22%2C%22service_status%22%3A%221%22%2C%22terminal_brand%22%3A%22%E8%8B%B9%E6%9E%9C%22%2C%22terminal_type%22%3A%22iPhone+5+%28%E7%99%BD%29%22%2C%22customer_name%22%3A%22senli%22%2C%22customer_address%22%3A%22%E5%B9%BF%E4%B8%9C%E6%B7%B1%E5%9C%B3%E5%B8%82%E5%8D%97%E5%B1%B1%E5%8C%BA%E5%8D%97%E6%B5%B7%E5%A4%A7%E9%81%93%E5%B7%A5%E4%B8%9A5%E8%B7%AF%E4%B8%87%E7%BB%B4%E5%A4%A7%E6%A5%BC301%22%2C%22customer_mobile%22%3A%2213530387581%22%2C%22contact_time%22%3A%2209%3A00%7E13%3A00%22%2C%22service_apply_date%22%3A%222015-04-07%22%2C%22service_expect_date%22%3A%222015-04-07%22%2C%22fault_id%22%3A%22%22%2C%22fault%22%3A%22%E5%B1%8F%E5%B9%95%E6%95%85%E9%9A%9C%22%2C%22expect_fee%22%3A%22%22%2C%22service_memo%22%3A%22%E6%B5%8B%E8%AF%95%E6%8E%A8%E9%80%81%E7%AD%BE%E5%90%8D%22%2C%22reserve1%22%3A%22%22%2C%22reserve2%22%3A%22%22%2C%22reserve3%22%3A%22%22%2C%22reserve4%22%3A%22%22%2C%22reserve5%22%3A%22%22%7D
//		//%7B%22partner_name%22%3A%22superfix%22%2C%22user_id%22%3A%22858576520%22%2C%22bill_no%22%3A%221012471%22%2C%22bill_id%22%3A%221012471%22%2C%22org_code%22%3A%22%22%2C%22order_type%22%3A%221%22%2C%22service_status%22%3A%221%22%2C%22terminal_brand%22%3A%22%E8%8B%B9%E6%9E%9C%22%2C%22terminal_type%22%3A%22iPhone+5+%28%E7%99%BD%29%22%2C%22customer_name%22%3A%22senli%22%2C%22customer_address%22%3A%22%E5%B9%BF%E4%B8%9C%E6%B7%B1%E5%9C%B3%E5%B8%82%E5%8D%97%E5%B1%B1%E5%8C%BA%E5%8D%97%E6%B5%B7%E5%A4%A7%E9%81%93%E5%B7%A5%E4%B8%9A5%E8%B7%AF%E4%B8%87%E7%BB%B4%E5%A4%A7%E6%A5%BC301%22%2C%22customer_mobile%22%3A%2213530387581%22%2C%22contact_time%22%3A%2209%3A00%7E13%3A00%22%2C%22service_apply_date%22%3A%222015-04-07%22%2C%22service_expect_date%22%3A%222015-04-07%22%2C%22fault_id%22%3A%22%22%2C%22fault%22%3A%22%E5%B1%8F%E5%B9%95%E6%95%85%E9%9A%9C%22%2C%22expect_fee%22%3A%22%22%2C%22service_memo%22%3A%22%E6%B5%8B%E8%AF%95%E6%8E%A8%E9%80%81%E7%AD%BE%E5%90%8D%22%2C%22reserve1%22%3A%22%22%2C%22reserve2%22%3A%22%22%2C%22reserve3%22%3A%22%22%2C%22reserve4%22%3A%22%22%2C%22reserve5%22%3A%22%22%7D
//		//String ss= "%7B%22partner_name%22%3A%22superfix%22%2C%22user_id%22%3A%22858576520%22%2C%22bill_no%22%3A%221012536%22%2C%22bill_id%22%3A%221012536%22%2C%22org_code%22%3A%22%22%2C%22order_type%22%3A1%2C%22service_status%22%3A1%2C%22terminal_brand%22%3A%22%5Cu82f9%5Cu679c%22%2C%22terminal_type%22%3A%22iPhone+5+%28%5Cu767d%29%22%2C%22customer_name%22%3A%22senli%22%2C%22customer_address%22%3A%22%5Cu5e7f%5Cu4e1c%5Cu6df1%5Cu5733%5Cu5e02%5Cu5357%5Cu5c71%5Cu533a%5Cu5357%5Cu6d77%5Cu5927%5Cu9053%5Cu5de5%5Cu4e1a5%5Cu8def%5Cu4e07%5Cu7ef4%5Cu5927%5Cu697c301%22%2C%22customer_mobile%22%3A%2213530387581%22%2C%22contact_time%22%3A%2209%3A00%7E13%3A00%22%2C%22service_apply_date%22%3A%222015-04-07%22%2C%22service_expect_date%22%3A%222015-04-07%22%2C%22fault_id%22%3A%22%22%2C%22fault%22%3A%22%5Cu7535%5Cu6c60%5Cu6545%5Cu969c%22%2C%22expect_fee%22%3A%22%22%2C%22service_memo%22%3A%22ces%22%2C%22reserve1%22%3A%22%22%2C%22reserve2%22%3A%22%22%2C%22reserve3%22%3A%22%22%2C%22reserve4%22%3A%22%22%2C%22reserve5%22%3A%22%22%7D";
//		
//		String ss11= "{\"partner_name\":\"superfix\",\"user_id\":\"858576520\",\"bill_no\":\"1012511\",\"bill_id\":\"1012511\",\"org_code\":\"\",\"order_type\":1,\"service_status\":1,\"terminal_brand\":\"\u82f9\u679c\",\"terminal_type\":\"iPhone 5 (\u767d)\",\"customer_name\":\"senli\",\"customer_address\":\"\u5e7f\u4e1c\u6df1\u5733\u5e02\u5357\u5c71\u533a\u5357\u6d77\u5927\u9053\u5de5\u4e1a5\u8def\u4e07\u7ef4\u5927\u697c301\",\"customer_mobile\":\"13530387581\",\"contact_time\":\"09:00~17:30\",\"service_apply_date\":\"2015-04-07\",\"service_expect_date\":\"2015-04-07\",\"fault_id\":\"\",\"fault\":\"\u7535\u6c60\u6545\u969c\",\"expect_fee\":\"\",\"service_memo\":\"ces\",\"reserve1\":\"\",\"reserve2\":\"\",\"reserve3\":\"\",\"reserve4\":\"\",\"reserve5\":\"\"}";
//		
//		String ss22= "{\"partner_name\":\"superfix\",\"user_id\":\"858576520\",\"bill_no\":\"1012511\",\"bill_id\":\"1012511\",\"org_code\":\"\",\"order_type\":1,\"service_status\":1,\"terminal_brand\":\"苹果\",\"terminal_type\":\"iPhone 5 (白)\",\"customer_name\":\"senli\",\"customer_address\":\"广东深圳市南山区南海大道工业5路万维大楼301\",\"customer_mobile\":\"13530387581\",\"contact_time\":\"09:00~17:30\",\"service_apply_date\":\"2015-04-07\",\"service_expect_date\":\"2015-04-07\",\"fault_id\":\"\",\"fault\":\"电池故障\",\"expect_fee\":\"\",\"service_memo\":\"ces\",\"reserve1\":\"\",\"reserve2\":\"\",\"reserve3\":\"\",\"reserve4\":\"\",\"reserve5\":\"\"}";
//		//c81094e7a31e196d2a891efbc3e8bcbb
//		
//		//System.out.println(ss22);
//		try {
////			System.out.println(ss11);
////			System.out.println(ss22);
////			String content1 = java.net.URLEncoder.encode(ss11,"UTF-8");
////			String content2 = java.net.URLEncoder.encode(ss22,"UTF-8");
////			System.out.println(content1);
////			System.out.println(content2);
////			
////			System.out.println(getMD532(content1+"4e7168f55f9ebd6a118b9b882d461efa"));
////			System.out.println(getMD532(content1));
//			System.out.println(getMD532("superfix"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
		
//	}
}

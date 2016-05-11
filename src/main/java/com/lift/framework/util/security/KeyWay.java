package com.lift.framework.util.security;


/**
 * 描述：<p>加密字符串</p>
 * @author WuNan2(alva.wu8@gmail.com)
 * @copyright aorsoft
 * @version 0.1 2011-4-7
 */
public final class KeyWay {
	private KeyWay() {
	}

	/**
	 * 服务端
	 * @param retCode
	 * @return
	 */
	public static final String getServiceSideKey(String retCode) {
		if("superfix".equals(retCode)){
			return "4e7168f55f9ebd6a118b9b882d461efa";
		}
		return "";
	}
	/**
	 * 客服端
	 * @param retCode
	 * @return
	 */
	public static final String getClientKey(String retCode) {
		if("superfix".equals(retCode)){
			return "4e7168f55f9ebd6a118b9b882d461efa";
		}
		return "";
	}
	
	
	public static void main(String[] args) {
		
		
	}
}

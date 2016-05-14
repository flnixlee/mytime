package com.lift.ejb.test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.tools.ant.types.resources.selectors.Compare;

import com.lift.ejb.common.WeChatTask;
import com.lift.ejb.util.GlobalConstants;
import com.lift.ejb.util.HttpUtils;

import net.sf.json.JSONObject;

public class TokenTest {
	public static void main(String[] args) throws Exception {
		Map<String, String> params = new LinkedHashMap<String, String>();
		// 获取token执行体
		params.put("grant_type", "client_credential");
		params.put("appid", "wx99f429756da58bdb");
		params.put("secret", "069415d32a6f1c94834a6731e5dd3f7e");
		
		String jstoken = HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/token", params);
		String access_token = JSONObject.fromObject(jstoken).getString("access_token"); // 获取到token并赋值保存
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "token为==============================" + access_token);

	}
}

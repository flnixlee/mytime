package com.lift.framework.util;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XMLUtil {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Object toObject(String xml, Class clazz) {
	ObjectMapper xmlmapper = new XmlMapper();
	Object obj = null;
	try {
	    xmlmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    obj = xmlmapper.readValue(xml, clazz);
	} catch (JsonParseException e) {
	    e.printStackTrace();
	} catch (JsonMappingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return obj;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<?> toObjects(String xml, Class clazz) throws InstantiationException, IllegalAccessException {
	ArrayList result = new ArrayList();
	ArrayList ls = (ArrayList) toObject(xml, ArrayList.class);
	if (null != ls) {
	    Field[] fields = clazz.getDeclaredFields();
	    for (Object o : ls) {
		Object obj = clazz.newInstance();
		Map map = (Map) o;
		for (Field f : fields) {
		    if (null != map.get(f.getName())) {
			f.setAccessible(true);
			f.set(obj, map.get(f.getName())); // 这边会报错，需要处理类型
		    }
		}
		result.add(obj);
	    }
	}
	return result;
    }

    public static String toXML(Object obj) {
	XmlMapper xml = new XmlMapper();

	try {
	    if (obj instanceof List || obj instanceof Map) {
		return xml.writeValueAsString(obj);
	    } else {
		StringWriter sw = new StringWriter();
		xml.writeValue(sw, obj);
		return sw.toString();
	    }
	} catch (JsonGenerationException e) {
	    e.printStackTrace();
	} catch (JsonMappingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * 
     * @param in
     *            请求数据内容
     * @param charset
     *            请求格式
     * @param url
     *            请求地址
     * @param contextType
     *            请求头内容
     * @param newTimeoutInMilliseconds
     *            请求超时时间
     * @return map 封装了响应数据的map
     * @return map code 响应代码
     * @return map bodyB byte[] 格式的响应数据body
     * @return map bodyS String 格式的响应数据body
     * @author Evans
     */
    @SuppressWarnings("deprecation")
    public static Map<String, Object> send(String url, String in, String charset, String contextType, int newTimeoutInMilliseconds) {

	if (StringUtils.isEmpty(charset)) {
	    charset = "UTF-8";
	}
	if (StringUtils.isEmpty(contextType)) {
	    contextType = "text/xml; charset=" + charset;
	}
	if (newTimeoutInMilliseconds <= 0) {
	    newTimeoutInMilliseconds = 3000;
	}

	PostMethod post = new PostMethod(url);// 请求地址

	// 指定请求内容的类型
	post.setRequestHeader("Content-type", contextType);
	post.setRequestHeader("Content-type", "text/xml; charset=utf-8");
	if (in.contains("ECC_ServiceOrder")) {
	    in = in.replace("ECC_ServiceOrder", "ServiceOrder");
	}
	post.setRequestBody(in);// 添加xml字符串
	HttpClient httpclient = new HttpClient();// 创建 HttpClient 的实例
	httpclient.setConnectionTimeout(newTimeoutInMilliseconds);
	int result;
	Map<String, Object> res = new HashMap<String, Object>();
	try {
	    result = httpclient.executeMethod(post);
	    res.put("code", result);
	    System.out.println("Response status code: " + result);// 返回200为成功

	    if (result == 200) {
		byte[] responseBody = post.getResponseBody();
		String responseString = post.getResponseBodyAsString();
		System.out.println("Response body: ");
		System.out.println(responseString);// 返回的内容
		res.put("bodyB", responseBody);
		res.put("resS", responseString);
	    }
	    post.releaseConnection();// 释放连接
	} catch (ConnectTimeoutException e1) {
	    System.out.println("ECC接口调用失败,连接超时,请检查链接\n" + url);
	    res.put("code", -1);
	    return res;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return res;
    }

    /**
     * 二次封装的调用远程接口方法
     * 
     * @param url
     *            接口地址
     * @param in
     *            接口传入的数据
     * @return
     */
    public static Map<String, Object> sendXML(String url, String in) {
	return send(url, in, null, null, 5000);
    }
}

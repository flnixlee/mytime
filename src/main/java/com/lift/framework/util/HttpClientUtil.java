package com.lift.framework.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import com.alibaba.druid.util.HttpClientUtils;
import com.alibaba.druid.util.StringUtils;

public class HttpClientUtil extends HttpClientUtils {

    private static final Logger logger = Logger.getLogger(HttpClientUtil.class);

    public static String post(String serverUrl, String data, int timeout) throws Exception {
	BufferedReader reader;
	OutputStreamWriter wr;
	StringBuilder responseBuilder = null;
	reader = null;
	wr = null;
	URL url = new URL(serverUrl);
	URLConnection conn = url.openConnection();
	conn.setDoOutput(true);
	conn.setConnectTimeout(timeout);
	wr = new OutputStreamWriter(conn.getOutputStream());
	wr.write(data);
	wr.flush();

	reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	responseBuilder = new StringBuilder();
	for (String line = null; (line = reader.readLine()) != null;)
	    responseBuilder.append((new StringBuilder()).append(line).append("\n").toString());
	String res = responseBuilder.toString();
	logger.debug(responseBuilder.toString());

	if (wr != null)
	    try {
		wr.close();
	    } catch (IOException e2) {
		logger.error("close error", e2);
	    }
	if (reader != null)
	    try {
		reader.close();
	    } catch (IOException e3) {
		logger.error("close error", e3);
	    }
	if (reader != null)
	    try {
		reader.close();
	    } catch (IOException e5) {
		logger.error("close error", e5);
	    }
	return res;
    }

    public static boolean isNotEmpty(String value) {
	return !StringUtils.isEmpty(value) && !StringUtils.isEmpty(value.trim());
    }

    public static boolean isNotEmpty(String... values) {
	for (String string : values) {
	    if (isNotEmpty(string)) {
		return false;
	    }
	}
	return true;
    }

    public static void main(String[] args) {

    }

    /**
     * 从url获取结果
     * 
     * @param path
     *            地址
     * @param params
     *            参数
     * @param encoding
     *            编码个设计 gbk utf-8
     * @return
     */
    public static String getHttpRs(String path, String params, String encoding) {
	URL url = null;
	HttpURLConnection connection = null;
	try {
	    url = new URL(path);
	    connection = (HttpURLConnection) url.openConnection();// 新建连接实例
	    connection.setConnectTimeout(10000);// 设置连接超时时间，单位毫�?
	    connection.setReadTimeout(10000);// 设置读取数据超时时间，单位毫�?
	    connection.setDoInput(true);// 是否打开输出�? true|false
	    connection.setDoOutput(true);// 是否打开输入流true|false
	    connection.setRequestMethod("POST");// 提交方法POST|GET
	    connection.setUseCaches(false);// 是否缓存true|false
	    connection.connect();// 打开连接端口
	    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
	    out.writeBytes(params);
	    out.flush();
	    out.close();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
	    StringBuffer buffer = new StringBuffer();
	    String line = "";
	    while ((line = reader.readLine()) != null) {
		buffer.append(line);
	    }
	    reader.close();
	    return buffer.toString();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (null != connection) {
		connection.disconnect();// 关闭连接
	    }
	}
	return null;
    }

    /**
     * 从url获取结果
     * 
     * @param path
     *            地址
     * @param params
     *            参数
     * @param encoding
     *            编码个设计 gbk utf-8
     * @return
     */
    public static String getHttpGet(String path, String params, String encoding) {
	URL url = null;
	HttpURLConnection connection = null;
	try {
	    url = new URL(path);
	    connection = (HttpURLConnection) url.openConnection();// 新建连接实例
	    connection.setConnectTimeout(10000);// 设置连接超时时间，单位毫�?
	    connection.setReadTimeout(10000);// 设置读取数据超时时间，单位毫�?
	    connection.setDoInput(true);// 是否打开输出�? true|false
	    connection.setDoOutput(true);// 是否打开输入流true|false
	    connection.setRequestMethod("GET");// 提交方法POST|GET
	    connection.setUseCaches(false);// 是否缓存true|false
	    connection.connect();// 打开连接端口
	    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
	    out.writeBytes(params);
	    out.flush();
	    out.close();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
	    StringBuffer buffer = new StringBuffer();
	    String line = "";
	    while ((line = reader.readLine()) != null) {
		buffer.append(line);
	    }
	    reader.close();
	    return buffer.toString();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if (null != connection) {
		connection.disconnect();// 关闭连接
	    }
	}
	return null;
    }
}

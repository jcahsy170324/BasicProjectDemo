package com.lg.bsp.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @ClassName MyHttpUtil
 * @Description Http请求工具类
 * @Author jincheng
 * @Date 2022/1/10 15:33
 * @Version 1.0
 **/
public class MyHttpUtil {

    /**
     * Get接口
     *
     * @param requestUrl
     * @return
     */
    public static String sendGet(String requestUrl) {
        String response = null;
        HttpURLConnection httpUrlConn = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(requestUrl);

            //打开http连接
            httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();

            //获得输入
            inputStream = httpUrlConn.getInputStream();
            response = IOUtils.toString(inputStream,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
            httpUrlConn.disconnect();
        }
        return response;
    }

    /**
     * Get接口获取String返回值
     *
     * @param requestUrl
     * @param params
     * @return
     */
    public static String httpGet(String requestUrl, Map params) {
        String result;
        HttpURLConnection httpUrlConn = null;
        InputStream inputStream = null;
        try {
            URL url = params == null ? new URL(requestUrl):new URL(requestUrl + "?" + urlencode(params));
            //打开http连接
            httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();
            //获得输入
            inputStream = httpUrlConn.getInputStream();
            result = IOUtils.toString(inputStream,"utf-8");
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
            httpUrlConn.disconnect();
        }
        return result;
    }

    /**
     * Url参数转化（将map里的参数变成像 showapi_appid=###&showapi_sign=###&的样子）
     *
     * @param params
     * @return
     */
    private static String urlencode(Map<String, String> params) {

        StringBuilder sb = new StringBuilder();
        for (Map.Entry param : params.entrySet()) {
            try {
                sb.append(param.getKey()).append("=").append(URLEncoder.encode(param.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                sb = null;
            }
        }
        return sb.toString();
    }

    /**
     * Get发送返回Json
     *
     * @param url
     * @param params
     * @return
     */
    public static JSONObject getApiJson(String url, Map<String, String> params) {

        JSONObject results;
        try {
            String retStr = httpGet(url, params);
            results = (JSONObject) JSONObject.parse(retStr);
        }
        catch (Exception e) {
            results = null;
        }
        return results;
    }

    /**
     * Get接口获取String返回值，带token验证
     *
     * @param requestUrl
     * @param params
     * @return
     */
    public static String httpGet_YunIdentityCheck(String requestUrl, Map<String, String> params, String token) {

        String response = null;
        InputStream inputStream = null;
        HttpURLConnection httpUrlConn = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = params == null ? new URL(requestUrl):new URL(requestUrl + "?" + urlencode(params));

            //打开http连接
            httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.setRequestProperty("Authorization", "X-Token=" + token);
            httpUrlConn.connect();

            //获得输入
            inputStream = httpUrlConn.getInputStream();
            response = IOUtils.toString(inputStream,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
            httpUrlConn.disconnect();
        }
        return response;
    }

    /**
     * Post接口获取String返回值
     *
     * @param urlStr
     * @param params
     * @return
     */
    public static String httpPost(String urlStr, Map<String, Object> params){

        String result = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        HttpURLConnection connection = null;
        try {
            URL connect = new URL(urlStr);

            //打开http连接
            connection = (HttpURLConnection)connect.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            //post不能使用缓存
            connection.setUseCaches(false);
            //连接超时时长
            connection.setConnectTimeout(2000);
            //传输数据超时时长
            connection.setReadTimeout(3000);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            outputStream = connection.getOutputStream();
            inputStream = connection.getInputStream();
            //拼接Post 请求的参数
            String paramsStr = "";
            for(String param : params.keySet()){
                paramsStr += "&" + param + "=" + params.get(param);
            }
            if(!paramsStr.isEmpty()){
                paramsStr = paramsStr.substring(1);
            }
            IOUtils.write(paramsStr,outputStream,"utf-8");
            result = IOUtils.toString(inputStream,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
            connection.disconnect();
        }
        return result;
    }

    /**
     * Post发送返回Json
     *
     * @param url
     * @param params
     * @return
     */
    public static JSONObject postApiJson(String url, Map<String, Object> params) {

        JSONObject results;
        try {
            String retStr = httpPost(url, params);
            results = (JSONObject) JSONObject.parse(retStr);
        }
        catch (Exception e) {
            results = null;
        }
        return results;
    }

    /**
     * Post接口Json参数
     *
     * @param url
     * @param param
     * @return
     */
    public static String sendPost(String url, String param){

        String response;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        HttpURLConnection conn = null;
        try {
            //创建URL
            URL httpUrl = new URL(url);
            //建立连接
            conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("connection", "keep-alive");
            //设置不要缓存
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(3000);
            conn.connect();
            //POST请求
            outputStream = conn.getOutputStream();
            IOUtils.write(param,outputStream,"UTF-8");

            //读取响应
            inputStream = conn.getInputStream();
            response = IOUtils.toString(inputStream,"utf-8");

        } catch (Exception e) {
            return "error:" + e.toString();
        }
        finally{
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
            conn.disconnect();
        }
       return response;
    }

    /**
     * Post接口Json参数，带token验证
     *
     * @param url
     * @param param
     * @return
     */
    public static String sendPost_YunIdentityCheck(String url, String param, String token){

        String response = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        HttpURLConnection conn = null;
        try {
            //创建URL
            URL httpUrl = new URL(url);
            //建立连接
            conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Authorization", "X-Token=" + token);
            //设置不要缓存
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();

            //POST请求
            outputStream = conn.getOutputStream();
            IOUtils.write(param,outputStream,"UTF-8");

            //读取响应
            inputStream = conn.getInputStream();
            response = IOUtils.toString(inputStream,"uft-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
            conn.disconnect();
        }
        return response;
    }

    /**
     * Delete接口Json参数
     *
     * @param url
     * @param param
     * @return
     */
    public static String sendDelete(String url, String param){

        String response;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        HttpURLConnection conn = null;
        try {
            //创建URL
            URL httpUrl = new URL(url);
            //建立连接
            conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            //设置不要缓存
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();

            //POST请求
            outputStream = conn.getOutputStream();
            IOUtils.write(param,outputStream,"utf-8");

            //读取响应
            inputStream = conn.getInputStream();
            response = IOUtils.toString(inputStream,"utf-8");
        } catch (Exception e) {
            return "error:" + e.toString();
        }
        finally{
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
            conn.disconnect();
        }
        return response;
    }

    /**
     * Put接口Json参数
     *
     * @param url
     * @param param
     * @return
     */
    public static String sendPut(String url,String param){

        String response = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        HttpURLConnection conn = null;
        try {
            //创建URL
            URL httpUrl = new URL(url);
            //建立连接
            conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("connection", "keep-alive");
            //设置不要缓存
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //PUT请求
            outputStream = conn.getOutputStream();
            IOUtils.write(param,outputStream,"utf-8");
            //读取响应
            inputStream = conn.getInputStream();
            response = IOUtils.toString(inputStream,"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
            conn.disconnect();
        }
        return  response;
    }
}

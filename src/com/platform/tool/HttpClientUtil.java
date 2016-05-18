package com.platform.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.platform.base.SysException;

/**
 * httpclient工具类
 * version 4.3.5
 * @author 刘焕超
 * 2014-11-3 下午1:31:24
 */
public class HttpClientUtil {

	private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	public static String UTF8 = "UTF-8";

	public static String httpPost(String url, HashMap<String, String> params, String encoding) throws Exception {

		log.debug("收到HTTP POST请求");

		String result = "";
		// 创建默认的httpClient实例.    
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost    
		HttpPost httppost = new HttpPost(url);

		//参数
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if (params != null) {
			log.debug("发送post参数");
			Set<String> keys = params.keySet();

			for (String key : keys) {
				log.debug("param:" + key);
				formparams.add(new BasicNameValuePair(key, params.get(key)));
			}

		}

		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, encoding);
			httppost.setEntity(uefEntity);

			log.debug("executing request " + httppost.getURI());

			CloseableHttpResponse response = httpclient.execute(httppost);

			try {
				log.debug("返回HTTP状态:" + response.getStatusLine());

				Header[] headers = response.getAllHeaders();

				log.debug("返回HTTP头");
				log.debug("--------------------------------------");
				for (Header header : headers) {
					log.debug(header.getName() + "-->" + header.getValue());
				}
				log.debug("--------------------------------------");

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, encoding);
					log.debug("--------------------------------------");
					log.debug("Response content: " + result);
					log.debug("--------------------------------------");
				}

			} finally {
				response.close();
			}
		} catch (IOException e) {
			throw e;
		} finally {
			// 关闭连接,释放资源    
			try {
				httpclient.close();
			} catch (IOException e) {
			}
		}

		return result;
	}

	public static String httpGet(String url, String encoding) throws Exception {

		log.debug("收到HTTP GET请求");

		String result = "";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.    
			HttpGet httpget = new HttpGet(url);
			log.debug("executing request " + httpget.getURI());
			// 执行get请求.    
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {

				log.debug("返回HTTP状态:" + response.getStatusLine());

				Header[] headers = response.getAllHeaders();

				log.debug("返回HTTP头");
				log.debug("--------------------------------------");
				for (Header header : headers) {
					log.debug(header.getName() + "-->" + header.getValue());
				}
				log.debug("--------------------------------------"); // 获取响应实体    
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, encoding);
					// 打印响应内容    
					log.debug("Response content: " + result);
				}
				log.debug("------------------------------------");
			} finally {
				response.close();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// 关闭连接,释放资源    
			try {
				httpclient.close();
			} catch (IOException e) {
			}
		}
		return result;

	}

	public static String httpsSSL(String url, String encoding, String pathCA, String password) throws SysException {

		log.debug("收到HTTPS SSL请求");

		String result = "";
		CloseableHttpClient httpclient = null;
		try {

			KeyStore trustStore = null;

			if (pathCA != null && "".equals(pathCA)) {

				log.debug("开始加载自己的CA");
				trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				FileInputStream instream = new FileInputStream(new File(pathCA));
				try {
					// 加载keyStore
					trustStore.load(instream, password.toCharArray());

				} catch (CertificateException e) {

					log.debug("加载自己CA失败:" + e.getMessage());

				} finally {
					try {
						instream.close();
					} catch (Exception ignore) {
					}
				}

			}

			// 相信自己的CA和所有自签名的证书  
			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
					.build();

			//			SSLContext sslcontext = new SSLContextBuilder().loadTrustMaterial(trustStore, new TrustStrategy() {
			//				//信任所有
			//				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			//					return true;
			//				}
			//			}).build();

			// 只允许使用TLSv1协议  
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			// 创建http请求(get方式)  
			HttpGet httpget = new HttpGet(url);

			log.debug("executing request" + httpget.getRequestLine());

			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				log.debug("返回HTTP状态:" + response.getStatusLine());

				Header[] headers = response.getAllHeaders();

				log.debug("返回HTTP头");
				log.debug("--------------------------------------");
				for (Header header : headers) {
					log.debug(header.getName() + "-->" + header.getValue());
				}
				log.debug("--------------------------------------"); // 获取响应实体    
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, encoding);
					// 打印响应内容    
					log.debug("Response content: " + result);
				}
				log.debug("------------------------------------");

			} finally {
				response.close();
			}
		} catch (Exception e) {
			log.error("请求hppts失败:" + e.getMessage(), e);
			throw new SysException("请求https失败:" + e.getMessage());
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param url URL
	 * @param content	内容
	 * @param encoding	编码
	 * @param pathCA	CA路径
	 * @param password	CA密码
	 * @return
	 * @throws SysException 
	 */
	public static String httpsSSLPOST(String url, String content, String encoding, String pathCA, String password)
			throws SysException {

		log.debug("收到HTTPS SSL请求");

		String result = "";
		CloseableHttpClient httpclient = null;
		try {

			KeyStore trustStore = null;

			if (pathCA != null && "".equals(pathCA)) {

				log.debug("开始加载自己的CA");
				trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				FileInputStream instream = new FileInputStream(new File(pathCA));
				try {
					// 加载keyStore
					trustStore.load(instream, password.toCharArray());

				} catch (CertificateException e) {

					log.debug("加载自己CA失败:" + e.getMessage());

				} finally {
					try {
						instream.close();
					} catch (Exception ignore) {
					}
				}

			}

			// 相信自己的CA和所有自签名的证书  
			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
					.build();

			//			SSLContext sslcontext = new SSLContextBuilder().loadTrustMaterial(trustStore, new TrustStrategy() {
			//				//信任所有
			//				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			//					return true;
			//				}
			//			}).build();

			// 只允许使用TLSv1协议  
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			// 创建http请求(post方式)  
			HttpPost httppost = new HttpPost(url);

			CloseableHttpResponse response = null;

			try {
				if (!HelpFunctions.isEmpty(content)) {
					StringEntity entity1 = new StringEntity(content, encoding);
					httppost.setEntity(entity1);
				}

				log.debug("executing request " + httppost.getURI());

				response = httpclient.execute(httppost);

				log.debug("返回HTTP状态:" + response.getStatusLine());

				Header[] headers = response.getAllHeaders();

				log.debug("返回HTTP头");
				log.debug("--------------------------------------");
				for (Header header : headers) {
					log.debug(header.getName() + "-->" + header.getValue());
				}
				log.debug("--------------------------------------"); // 获取响应实体    
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, encoding);
					// 打印响应内容    
					log.debug("Response content: " + result);
				}
				log.debug("------------------------------------");

			} finally {
				response.close();
			}
		} catch (Exception e) {
			log.error("请求hppts失败:" + e.getMessage(), e);
			throw new SysException("请求hppts失败:" + e.getMessage());
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		String requestContent = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.drs.xdja.com/\"><soapenv:Header/><soapenv:Body><ser:query><parameters><userId/><localTable>t_sysdata_struct</localTable><localFields>c_local_tablename,c_comment</localFields><condition><![CDATA[]]></condition><dsid>*</dsid><pageNumber>0</pageNumber><pageSize>1000</pageSize><uuInfo/></parameters></ser:query></soapenv:Body></soapenv:Envelope>";

		System.out.println(requestContent);
		String url = "http://127.0.0.1:9000/drs/ws/DataRequest?wsdl";
		try {
			HttpClientUtil.httpPost(url, null, UTF8);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

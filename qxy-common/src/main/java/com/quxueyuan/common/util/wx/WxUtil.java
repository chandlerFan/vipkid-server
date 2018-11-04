package com.quxueyuan.common.util.wx;

import com.quxueyuan.common.util.http.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 微信工具类
 * liuwei 2018-10-30
 */
@Slf4j
@Component
public class WxUtil {

	@Value("${wx.appid}")
	public String APPID;

	@Value("${wx.appsecret}")
	public String APPSECRET;

	@Value("${wx.access_token_auth}")
	private String ACCESS_TOKEN_AUTH;

	@Value("${wx.refresh_token}")
	private String REFRESH_TOKEN;

	@Value("${wx.user_info_auth}")
	private String USER_INFO_AUTH;

	@Value("${wx.if_invalid_auth}")
	private String IF_INVALID_AUTH;

	@Value("${wx.get_ticket}")
	private String GET_TICKET;

	@Value("${wx.get_access_token}")
	private String GET_ACCESS_TOKEN;

	/**
	 * 获取微信认证授权信息
	 * @param url
	 * @return
	 */
	public Map<String, String> globalAccessAuth(String url) {
		Map<String, String> mapauth = null;
		try{
			mapauth = getAccessAuth();
			Map<String, String> signMap = sign(mapauth.get("ticket"), url.toString());
			mapauth.putAll(signMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapauth;
	}

	/*
	 * 通过code换取网页授权access_token，从缓存中获取同时获取ticket
	 */
	public Map<String, String> getAccessTokenAuth(String code) throws Exception {
		JSONObject job = getToken(code);
		Map<String, String> authMap = null;
		if (job != null && job.get("access_token") != null) {
			authMap = new HashMap<String, String>();
			authMap.put("access_token", job.getString("access_token"));
			authMap.put("refresh_token", job.getString("refresh_token"));
			authMap.put("openid", job.getString("openid"));

		}
		return authMap;
	}

	/*
	 * 通过code换取网页授权access_token，从缓存中获取同时获取ticket
	 */
	public Map<String, String> getAccessAuth() throws Exception {
		Map<String, Map<String, String>> authTokenMap = Singleton.getInstance().getAuthTokenMap();
		Map<String, String> authMap = authTokenMap.get("apiAuthMap");
		JSONObject job = null;
		long nowDate = System.currentTimeMillis();
		if (authMap != null) {
			String token_time = authMap.get("token_time");
			String access_token = authMap.get("access_token");
			if (access_token != null && (nowDate - Long.parseLong(token_time)) < 7200 * 1000) {
				// 未过期
			} else {
				// 已过期，重新获取
				job = getApiToken();
			}
		} else {
			authMap = new HashMap<String, String>();
			job = getApiToken();
		}
		if (job != null) {
			authMap.put("token_time", nowDate + "");
			authMap.put("access_token", job.getString("access_token"));
			authMap.put("appId", APPID);
			// 添加ticket
			JSONObject ticketJod = getTicket(job.getString("access_token"));
			authMap.put("ticket", ticketJod.getString("ticket"));
			authTokenMap.remove("apiAuthMap");
			authTokenMap.put("apiAuthMap", authMap);
		}
		return authMap;
	}

	/*
	 * 通过code换取网页授权access_token
	 */
	public JSONObject getToken(String code) throws Exception {
		String url = ACCESS_TOKEN_AUTH.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
		String res = HttpClientUtil.sendGet(url);
		JSONObject json = new JSONObject(res);
		return json;
	}

	/*
	 * api获取access_token
	 */
	public JSONObject getApiToken() throws Exception {
		String url = GET_ACCESS_TOKEN.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		String res = HttpClientUtil.sendGet(url);
		JSONObject json = new JSONObject(res);
		return json;
	}

	/*
	 * 通过access_token换取网页授权ticket
	 */
	public JSONObject getTicket(String access_token) throws Exception {
		String url = GET_TICKET.replace("ACCESS_TOKEN", access_token);
		String res = HttpClientUtil.sendGet(url);
		JSONObject json = new JSONObject(res);
		return json;
	}

	/*
	 * 刷新access_token
	 */
	public String refreshToken(String refresh_token) throws Exception {
		String url = REFRESH_TOKEN.replace("APPID", APPID).replace(
				"REFRESH_TOKEN", refresh_token);
		String res = HttpClientUtil.sendGet(url);
		JSONObject json = new JSONObject(res);
		String access_token = json.getString("access_token");
		return access_token;
	}

	/*
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 */
	public JSONObject getUserinfoAuth(String access_token, String openid)
			throws Exception {
		String url = USER_INFO_AUTH.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
		String res = HttpClientUtil.sendGet(url);
		JSONObject json = new JSONObject(res);
		return json;
	}

	/*
	 * 验证授权是否有效
	 */
	public boolean ifInvalidAuth(String access_token, String openid) throws Exception {
		String url = IF_INVALID_AUTH.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
		String res = HttpClientUtil.sendGet(url);
		JSONObject json = new JSONObject(res);
		String errcode = json.getString("errcode");
		if (Integer.parseInt(errcode) == 0) {
			return true;
		}
		return false;
	}

	public Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		return ret;
	}

	private String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

}

package com.quxueyuan.common.util.wx;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author liuwei
 * @date 2016-1-6
 * @description 单例 缓存accessToken\token_time,jsapi_ticket\ticket_time
 */
public class Singleton {
    //缓存accessToken 的Map  ,map中包含 一个accessToken 和 缓存的时间戳
    //当然也可以分开成两个属性咯
	/**
	 * accessToken和jsapi_ticket 缓存时间 7200秒  7200*1000毫秒 (2小时)
	 * 
	 */
    private Map<String, String> map = new HashMap<String, String>();
    private Map<String, Map<String, String>> authTokenMap = new HashMap<String, Map<String, String>>();
    
	private Map<Integer, Integer> prize_map = null;
 
    private Singleton() {
    }
 
    private static Singleton single = null;
 
    // 静态工厂方法
    public static Singleton getInstance() {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }
 
    public Map<String, String> getMap() {
        return map;
    }
 
    public void setMap(Map<String, String> map) {
        this.map = map;
    }
 
    public Map<Integer, Integer> getPrize_map() {
		return prize_map;
	}

	public void setPrize_map(Map<Integer, Integer> prize_map) {
		this.prize_map = prize_map;
	}
	
    public static Singleton getSingle() {
        return single;
    }
 
    public static void setSingle(Singleton single) {
        Singleton.single = single;
    }
    
    public Map<String, Map<String, String>> getAuthTokenMap() {
		return authTokenMap;
	}

	public void setAuthTokenMap(Map<String, Map<String, String>> authTokenMap) {
		this.authTokenMap = authTokenMap;
	}
 
}


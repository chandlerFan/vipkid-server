package com.quxueyuan.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 简单的工具类
 */
public class Utility {

    // 代理头域名称
    private static final String[] PROXY_REMOTE_IP_ADDRESS = {"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP"};

    private static final String UNKNOWN = "unknown";

    /**
     * return right response , status is 0 , message is success .
     *
     * @param content
     * @return
     */
//    public static Responses getRightResponse(Object content) {
//    	Responses response = new Responses();
//    	Responses.ResponseStatus status = new Responses.ResponseStatus();
//        status.setCode(0);
//        status.setDescription("success");
//
//        response.setStatus(status);
//        response.setResult(content);
//        return response;
//    }

    /**
     * return right response , status is 0 , message of specified .
     *
     * @param message
     * @param content
     * @return
     */
//    public static Responses getRightResponse(String message, Object content) {
//    	Responses response = new Responses();
//    	Responses.ResponseStatus status = new Responses.ResponseStatus();
//        status.setCode(0);
//        status.setDescription(message);
//        response.setStatus(status);
//        response.setResult(content);
//        return response;
//    }

   
    /**
     * return error response , status is errorCode specified , message is correspond to errorCode bundled .
     *
     * @param errorCode
     * @param message
     * @return
     */
//    public static Responses getErrorResponse(int errorCode, String message) {
//    	Responses response = new Responses();
//    	Responses.ResponseStatus status = new Responses.ResponseStatus();
//        status.setCode(errorCode);
//        status.setDescription(message);
//        response.setStatus(status);
//        return response;
//    }

    /**
     * 获取客户端地址
     *
     * @param request
     * @return
     */
    public static String getRemoteIP(HttpServletRequest request) {
        for (int i = 0; i < PROXY_REMOTE_IP_ADDRESS.length; i++) {
            String ip = request.getHeader(PROXY_REMOTE_IP_ADDRESS[i]);
            if (StringUtil.isNotBlank(ip) && !ip.trim().equalsIgnoreCase(UNKNOWN)) {
                return getRemoteIpFromForward(ip.trim());
            }
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取多个地址中的第一个地址
     *
     * @param xforwardIp
     * @return
     */
    private static String getRemoteIpFromForward(String xforwardIp) {
        int commaOffset = xforwardIp.indexOf(',');
        if (commaOffset < 0) {
            return xforwardIp;
        }
        return xforwardIp.substring(0, commaOffset);
    }
}

package com.quxueyuan.bean.vo.res;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @Company: vipkid
 * @Author 刘巍
 * @Date 2018年10月23日 10:05
 * @Description: 返回接口格式
 */
public class TouchApiResponse {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    public static ResponseEntity<TouchResponseModel> success(Object data) {
        return success(data, SUCCESS);
    }

    public static ResponseEntity<TouchResponseModel> success() {
        return success(SUCCESS);
    }

    public static ResponseEntity<TouchResponseModel> success(String msg) {
        return success(SUCCESS, msg);
    }

    public static ResponseEntity<TouchResponseModel> success(Object data, String msg) {
        TouchResponseModel successModel = successModel(data, msg);
        return new ResponseEntity<TouchResponseModel>(successModel, HttpStatus.OK);
    }

    public static ResponseEntity<TouchResponseModel> badRequest(String msg) {
        TouchResponseModel badRequestModel = badRequestModel(msg);
        return new ResponseEntity<TouchResponseModel>(badRequestModel, HttpStatus.OK);
    }

    public static ResponseEntity<TouchResponseModel> failed() {
        return failed(ERROR);
    }

    public static ResponseEntity<TouchResponseModel> failed(String msg) {
        TouchResponseModel em = new TouchResponseModel();
        TouchResponseModel.Status st = new TouchResponseModel.Status();
        st.setCode(1);
        st.setDescription(msg);
        em.setStatus(st);
        em.setResult("失败");
        return new ResponseEntity<TouchResponseModel>(em, HttpStatus.OK);
    }

    public static ResponseEntity<TouchResponseModel> failed(Integer code, String msg) {
        TouchResponseModel em = new TouchResponseModel();
        TouchResponseModel.Status st = new TouchResponseModel.Status();
        st.setCode(code);
        st.setDescription(msg);
        em.setStatus(st);
        em.setResult("失败");
        return new ResponseEntity<TouchResponseModel>(em, HttpStatus.OK);
    }

    private static TouchResponseModel successModel(final Object data) {
        return successModel(data, SUCCESS);
    }

    private static TouchResponseModel successModel(final Object data, final String msg) {
        return successModelBase(data, msg);
    }

    private static TouchResponseModel successModelBase(final Object data, final String msg) {
        TouchResponseModel sm = new TouchResponseModel();
        TouchResponseModel.Status st = new TouchResponseModel.Status();
        st.setCode(0);
        st.setDescription(msg);
        sm.setStatus(st);
        sm.setResult(data);
        return sm;
    }

    private static TouchResponseModel badRequestModel(String msg) {
        TouchResponseModel bm = new TouchResponseModel();
        TouchResponseModel.Status st = new TouchResponseModel.Status();
        st.setCode(1);
        st.setDescription(msg);
        bm.setStatus(st);
        bm.setResult("失败");
        return bm;
    }

}

package com.quxueyuan.common.util.tencent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 音频转文字工具类
 * liuwei 2018-10-30
 */
@Slf4j
@Component
public class Voice2WordsUtil {

	@Value("${tencent.appid}")
	public String TENCENT_APPID;

	@Value("${tencent.secretid}")
	public String TENCENT_SECRETID;

	@Value("${tencent.secretkey}")
	public String TENCENT_SECRETKEY;

	/**
	 * 音频文件转文字  语音识别
	 * @param filePath
	 * @return
	 */
	public String voice2Words(String filePath) {
//		String filepath = "/Users/liuwei/Desktop/test.wav";

		//识别引擎 8k_0 or 16k_0
		String engine_model_type = "8k_0";
		//结果返回方式 0：同步返回 or 1：尾包返回
		String res_type = "0";
		// 识别结果文本编码方式 0:UTF-8,1:GB2312,2:GBK,3:BIG5
		String result_text_format = "0";
		// 语音编码方式 1:wav 4:sp 6:skill
		String voice_format = "1";
		// 语音切片长度 cutlength<200000
		int cutlength = 6400;
		//调用setConfig函数设置相关参数
		int res = RASRsdk.setConfig(TENCENT_SECRETKEY, TENCENT_SECRETID, TENCENT_APPID, engine_model_type, res_type, result_text_format, voice_format, filePath, cutlength);
		if (res < 0) {
			log.info("out");
		}
		//调用sendVoice函数获得音频识别结果
		String resContent = RASRsdk.sendVoice();

		return resContent;

	}
}

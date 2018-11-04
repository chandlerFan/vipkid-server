/** 
* Project Name: 26.02.06.XX 
* File Name: MoneyUtil.java 
* Package Name: com.huifenqi.saas.common 
* Date: 2016年12月21日下午12:40:14 
* Copyright (c) 2016, www.huizhaofang.com All Rights Reserved. 
* 
*/
package com.quxueyuan.common.util;

import java.math.BigDecimal;

/** 
* ClassName: MoneyUtil
* date: 2016年12月21日 下午12:40:14
* Description: 
* 
* @author maoxinwu 
* @version  
* @since JDK 1.8 
*/
public class MoneyUtil {

	// 金额精确小数位数(以元为单位)
	public static final int MONEY_SCALE_IN_YUAN = 2;

	/**
	 * 获取以元表示的金额字符串
	 *
	 * @param moneyInCent
	 * @return
	 */
	public static String getMoneyDescInYuan(Integer moneyInCent) {
		if (moneyInCent == null) {
			return null;
		} else {
			return BigDecimal.valueOf(moneyInCent, MONEY_SCALE_IN_YUAN).toPlainString();
		}
	}

	/**
	 * 获取以元表示的金额字符串
	 *
	 * @param moneyInCent
	 * @return
	 */
	public static String getMoneyDescInYuan(Long moneyInCent) {
		if (moneyInCent == null) {
			return null;
		} else {
			return BigDecimal.valueOf(moneyInCent, MONEY_SCALE_IN_YUAN).toPlainString();
		}
	}

	/**
	 * 获取调整后的以元表示的金额字符串(去掉小数点后多余的0)
	 * 
	 * @param moneyInCent
	 * @return
	 */
	public static String getTrimmedMoneyDescInYuan(Integer moneyInCent) {

		String trimmedMoneyDescInYuan = getMoneyDescInYuan(moneyInCent);
		if (trimmedMoneyDescInYuan != null) {
			if (trimmedMoneyDescInYuan.indexOf(".") > 0) {
				trimmedMoneyDescInYuan = trimmedMoneyDescInYuan.replaceAll("0+?$", "");
				trimmedMoneyDescInYuan = trimmedMoneyDescInYuan.replaceAll("[.]$", "");
			}
		}
		return trimmedMoneyDescInYuan;
	}

	/**
	 * 获取调整后的以元表示的金额字符串(去掉小数点后多余的0)
	 * 
	 * @param moneyInCent
	 * @return
	 */
	public static String getTrimmedMoneyDescInYuan(Long moneyInCent) {

		String trimmedMoneyDescInYuan = getMoneyDescInYuan(moneyInCent);
		if (trimmedMoneyDescInYuan != null) {
			if (trimmedMoneyDescInYuan.indexOf(".") > 0) {
				trimmedMoneyDescInYuan = trimmedMoneyDescInYuan.replaceAll("0+?$", "");
				trimmedMoneyDescInYuan = trimmedMoneyDescInYuan.replaceAll("[.]$", "");
			}
		}
		return trimmedMoneyDescInYuan;
	}

	/**
	 * 获取以分表示的金额数值
	 * 
	 * @param moneyDescInYuan
	 * @return
	 */
	public static long getMoneyInCent(String moneyDescInYuan) {
		BigDecimal amount = new BigDecimal(moneyDescInYuan);
		BigDecimal movePointRight = amount.movePointRight(MONEY_SCALE_IN_YUAN);
		long cent = movePointRight.setScale(0, BigDecimal.ROUND_HALF_EVEN).longValue();
		return cent;
	}

}
